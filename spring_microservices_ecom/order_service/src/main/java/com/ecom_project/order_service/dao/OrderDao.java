package com.ecom_project.order_service.dao;

import com.ecom_project.order_service.model.Order;
import com.ecom_project.order_service.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDao
{
    private final OrderRepo orderRepo;
    @Autowired
    public OrderDao(OrderRepo orderRepo){
        this.orderRepo=orderRepo;
    }

    public Order save(Order order){
        return orderRepo.save(order);
    }

    public List<Order> retrieveAllOrders(){
        return orderRepo.findAll();
    }
}
