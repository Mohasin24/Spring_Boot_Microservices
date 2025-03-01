package com.ecom_project.inventory_service.dto;

import com.ecom_project.inventory_service.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class InventoryResponse {

    public String skuCode;
    public boolean isInStock;
}
