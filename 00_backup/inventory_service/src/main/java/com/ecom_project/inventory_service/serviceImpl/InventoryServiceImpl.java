package com.ecom_project.inventory_service.serviceImpl;

import com.ecom_project.inventory_service.dao.InventoryDao;
import com.ecom_project.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private InventoryDao inventoryDao;

    @Autowired
    public InventoryServiceImpl(InventoryDao inventoryDao){
        this.inventoryDao=inventoryDao;
    }

    @Override
    public boolean isInStock(String skuCode) {
        return inventoryDao.checkItemsAvailability(skuCode);
    }
}
