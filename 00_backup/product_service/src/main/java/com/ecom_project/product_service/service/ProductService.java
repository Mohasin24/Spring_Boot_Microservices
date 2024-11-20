package com.ecom_project.product_service.service;

import com.ecom_project.product_service.dto.ProductRequest;
import com.ecom_project.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService
{
    ProductResponse saveProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
}
