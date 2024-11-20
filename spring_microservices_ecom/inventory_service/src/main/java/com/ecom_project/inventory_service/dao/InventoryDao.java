package com.ecom_project.inventory_service.dao;

import com.ecom_project.inventory_service.entity.Inventory;
import com.ecom_project.inventory_service.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryDao
{
    private InventoryRepo inventoryRepo;

    @Autowired
    public InventoryDao(InventoryRepo inventoryRepo){
        this.inventoryRepo=inventoryRepo;
    }

    public List<Inventory> checkItemsAvailability(List<String> skuCode){
        return inventoryRepo.findBySkuCodeIn(skuCode);
    }
}
