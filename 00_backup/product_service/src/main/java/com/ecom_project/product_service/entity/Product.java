package com.ecom_project.product_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private String price;
}
