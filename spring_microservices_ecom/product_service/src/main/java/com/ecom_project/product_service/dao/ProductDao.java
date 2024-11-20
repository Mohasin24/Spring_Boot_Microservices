package com.ecom_project.product_service.dao;

import com.ecom_project.product_service.entity.Product;
import com.ecom_project.product_service.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao
{
    private final ProductRepo productRepo;

    @Autowired
    public ProductDao(ProductRepo productRepo){
        this.productRepo=productRepo;
    }

    public Product save(Product product){
        return productRepo.save(product);
    }

    public List<Product> retrieveAllProducts(){
        return productRepo.findAll();
    }
}
