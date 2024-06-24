package com.fourbit.inventory_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourbit.inventory_service.dto.InventoryResponse;
import com.fourbit.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class InventoryService {
   private final InventoryRepository inventoryRepository;
   
   @Transactional(readOnly = true)
   public List<InventoryResponse> isINStock(List<String> skvCodes) {
      return inventoryRepository.findBySkvCodeIn(skvCodes).stream().map(inventory -> 
         InventoryResponse.builder()
               .skvCode(inventory.getSkvCode())
               .isInStock(inventory.getQuantity() > 0)
               .build()
      ).toList();
   }
}
