package com.ecom_project.order_service.controller;

import com.ecom_project.order_service.dto.OrderDto;
import com.ecom_project.order_service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController
{
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/place-order")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderDto placeOrder (@RequestBody OrderDto orderDto) throws Exception{
        System.out.println("Inside Order controller");
        try{
            OrderDto orderDto1 = orderService.placeOrder(orderDto);
            System.err.println((orderDto1));
            return orderDto1;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/all-orders")
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

}
