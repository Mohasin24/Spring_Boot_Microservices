package com.ecom_project.inventory_service.repository;

import com.ecom_project.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long>
{
    Optional<Inventory> findBySkuCode(String skuCode);
}
