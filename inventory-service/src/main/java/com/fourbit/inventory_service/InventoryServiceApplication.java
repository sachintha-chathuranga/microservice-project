package com.fourbit.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fourbit.inventory_service.model.Inventory;
import com.fourbit.inventory_service.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkvCode("iphone17");
			inventory.setQuantity(100);
			Inventory inventory1 = new Inventory();
			inventory1.setSkvCode("iphone8");
			inventory1.setQuantity(10);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
