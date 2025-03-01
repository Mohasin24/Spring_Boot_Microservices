package com.ecom_project.order_service.serviceImpl;

import com.ecom_project.order_service.dao.OrderDao;
import com.ecom_project.order_service.dto.InventoryResponse;
import com.ecom_project.order_service.dto.OrderDto;
import com.ecom_project.order_service.event.OrderPlacedEvent;
import com.ecom_project.order_service.model.Order;
import com.ecom_project.order_service.model.OrderLineItems;
import com.ecom_project.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
    @Autowired
    public OrderServiceImpl(OrderDao orderDao,WebClient.Builder webClientBuilder,KafkaTemplate kafkaTemplate){
        this.orderDao=orderDao;
        this.webClientBuilder =webClientBuilder;
        this.kafkaTemplate=kafkaTemplate;
    }

    @Override
    public OrderDto placeOrder(OrderDto orderDto) throws Exception{
        Order order = Order.builder()
                .orderNumber(orderDto.getOrderNumber())
                .orderLineItems(orderDto.getOrderLineItems())
                .build();

        List<String> skuCodeList = orderDto.getOrderLineItems().stream().map(OrderLineItems::getSkuCode
        ).toList();


        //Check with inventory if the product is available in stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder->
                                uriBuilder.queryParam("skuCode",skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        boolean result = inventoryResponses.length>0 && Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.isInStock);
        System.err.println(result);
        if(result){

            Order savedOrder = orderDao.save(order);

//            Kafka event
            kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(savedOrder.getOrderNumber()));

            OrderDto dto = OrderDto.builder()
                    .id(savedOrder.getId())
                    .orderNumber(savedOrder.getOrderNumber())
                    .orderLineItems(savedOrder.getOrderLineItems())
                    .build();

            System.err.println(dto);
            return dto;
        }else {
            throw new Exception("Product is out of stock!");
        }


    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderDao.retrieveAllOrders();
        List<OrderDto> list = orderList.stream().map(order->mapOrder(order)).toList();
        return list;
    }

    public OrderDto mapOrder(Order order){
        OrderDto orderDto= OrderDto.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderLineItems(order.getOrderLineItems())
                .build();
        return orderDto;
    }
}
