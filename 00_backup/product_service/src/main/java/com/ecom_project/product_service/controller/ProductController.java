package com.ecom_project.product_service.controller;

import com.ecom_project.product_service.dto.ProductRequest;
import com.ecom_project.product_service.dto.ProductResponse;
import com.ecom_project.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController
{
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.saveProduct(productRequest);
    }

    @GetMapping("/all-products")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
