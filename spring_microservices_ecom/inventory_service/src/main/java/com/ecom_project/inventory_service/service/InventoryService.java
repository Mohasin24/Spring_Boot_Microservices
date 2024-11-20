package com.ecom_project.inventory_service.service;

import com.ecom_project.inventory_service.dto.InventoryResponse;

import java.util.List;

public interface InventoryService
{
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
