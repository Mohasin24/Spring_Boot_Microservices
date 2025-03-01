package com.ecom_project.order_service.controller;

import com.ecom_project.order_service.dto.OrderDto;
import com.ecom_project.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController
{
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/place-order")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderDto placeOrder(@RequestBody OrderDto orderDto){
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/all-orders")
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

}
