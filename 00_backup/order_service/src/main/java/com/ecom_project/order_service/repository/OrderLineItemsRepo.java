package com.ecom_project.order_service.repository;

import com.ecom_project.order_service.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineItemsRepo extends JpaRepository<OrderLineItems,Long> {
}
