package com.ecom_project.inventory_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "t_inventory")
@NoArgsConstructor
@AllArgsConstructor

@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String skuCode;
    private int quantity;

    public Inventory(String skuCode, int quantity){
        this.skuCode=skuCode;
        this.quantity=quantity;
    }

}
