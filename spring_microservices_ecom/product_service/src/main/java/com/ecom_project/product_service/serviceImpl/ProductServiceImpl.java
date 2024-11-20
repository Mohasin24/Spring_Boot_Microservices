package com.ecom_project.product_service.serviceImpl;

import com.ecom_project.product_service.dao.ProductDao;
import com.ecom_project.product_service.dto.ProductRequest;
import com.ecom_project.product_service.dto.ProductResponse;
import com.ecom_project.product_service.entity.Product;
import com.ecom_project.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao){
        this.productDao=productDao;
    }
    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        Product savedProduct = productDao.save(product);
        ProductResponse productResponse = ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .build();

        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productDao.retrieveAllProducts();

        List<ProductResponse> productResponseList=productList.stream().map(product->mapProduct(product)).toList();

        return productResponseList;
    }

    private ProductResponse mapProduct(Product product){
        ProductResponse productResponse=ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        return productResponse;
    }
}
