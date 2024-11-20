package com.ecom_project.order_service.serviceImpl;

import com.ecom_project.order_service.dao.OrderDao;
import com.ecom_project.order_service.dto.OrderDto;
import com.ecom_project.order_service.model.Order;
import com.ecom_project.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    @Autowired
    public OrderServiceImpl(OrderDao orderDao){
        this.orderDao=orderDao;
    }

    @Override
    public OrderDto placeOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .orderNumber(orderDto.getOrderNumber())
                .orderLineItems(orderDto.getOrderLineItems())
                .build();
        Order savedOrder = orderDao.save(order);
        OrderDto dto = OrderDto.builder()
                .id(savedOrder.getId())
                .orderNumber(savedOrder.getOrderNumber())
                .orderLineItems(savedOrder.getOrderLineItems())
                .build();
        return dto;
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
