package com.fourbit.inventory_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourbit.inventory_service.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

   List<Inventory> findBySkvCodeIn(List<String> skvCodes);

}
