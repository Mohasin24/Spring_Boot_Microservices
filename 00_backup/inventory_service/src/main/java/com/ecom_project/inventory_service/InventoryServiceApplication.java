package com.ecom_project.inventory_service;

import com.ecom_project.inventory_service.entity.Inventory;
import com.ecom_project.inventory_service.repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InventoryRepo inventoryRepo){
		return args->{
			inventoryRepo.save(new Inventory("iPhone 13",6));
			inventoryRepo.save(new Inventory("Samsung S20 Ultra",8));
		};
	}
}

