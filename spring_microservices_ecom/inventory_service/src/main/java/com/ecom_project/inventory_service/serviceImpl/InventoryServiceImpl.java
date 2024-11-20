package com.ecom_project.inventory_service.serviceImpl;

import com.ecom_project.inventory_service.dao.InventoryDao;
import com.ecom_project.inventory_service.dto.InventoryResponse;
import com.ecom_project.inventory_service.entity.Inventory;
import com.ecom_project.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    private InventoryDao inventoryDao;

    @Autowired
    public InventoryServiceImpl(InventoryDao inventoryDao){
        this.inventoryDao=inventoryDao;
    }

    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        System.err.println(inventoryDao.checkItemsAvailability(skuCode));
        List<InventoryResponse> list = inventoryDao.checkItemsAvailability(skuCode)
                .stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity()>0)
                                .build()
                ).toList();
        System.err.println(list);
        return list;
    }
}
