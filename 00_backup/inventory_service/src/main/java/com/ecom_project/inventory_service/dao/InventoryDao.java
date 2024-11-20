package com.ecom_project.inventory_service.dao;

import com.ecom_project.inventory_service.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryDao
{
    private InventoryRepo inventoryRepo;

    @Autowired
    public InventoryDao(InventoryRepo inventoryRepo){
        this.inventoryRepo=inventoryRepo;
    }

    public boolean checkItemsAvailability(String skuCode){
        return inventoryRepo.findBySkuCode(skuCode).isPresent();
    }
}
