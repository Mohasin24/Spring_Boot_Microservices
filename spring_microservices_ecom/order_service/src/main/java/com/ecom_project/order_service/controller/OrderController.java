package com.ecom_project.order_service.controller;

import com.ecom_project.order_service.dto.OrderDto;
import com.ecom_project.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public ResponseEntity<?> placeOrder (@RequestBody OrderDto orderDto) throws Exception{

//        try{
//            OrderDto orderDto1 = orderService.placeOrder(orderDto);
//            System.err.println((orderDto1));
//            return orderDto1;
//        }catch (Exception e){
//            log.error(e.getMessage());
//            throw new Exception(e.getMessage());
//        }
        System.err.println("Inside the place order");
        return ResponseEntity.ok(orderService.placeOrder(orderDto));
    }

    @GetMapping("/all-orders")
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    public ResponseEntity<?> fallbackMethod(OrderDto orderDto, Throwable throwable) {
        log.error("Fallback executed due to: {}", throwable.getMessage());
        return ResponseEntity.internalServerError().body("Oops something went wrong"); // Return a default or null object, depending on your requirements.
    }

    @GetMapping("/test")
    public String test(){
        return "Test successful";
    }
}
