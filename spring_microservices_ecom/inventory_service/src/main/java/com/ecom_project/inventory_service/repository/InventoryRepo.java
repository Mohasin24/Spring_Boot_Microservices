package com.ecom_project.inventory_service.repository;

import com.ecom_project.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long>
{

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}