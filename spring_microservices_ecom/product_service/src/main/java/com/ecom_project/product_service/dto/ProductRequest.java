package com.ecom_project.product_service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest
{
    private String name;
    private String description;
    private String price;
}
