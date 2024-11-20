package com.ecom_project.order_service.service;

import com.ecom_project.order_service.dto.OrderDto;

import java.util.List;

public interface OrderService
{
    OrderDto placeOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();
}
