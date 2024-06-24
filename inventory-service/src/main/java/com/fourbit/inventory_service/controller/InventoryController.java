package com.fourbit.inventory_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fourbit.inventory_service.dto.InventoryResponse;
import com.fourbit.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
   private final InventoryService inventoryService;

   @GetMapping
   @ResponseStatus(HttpStatus.OK)
   public List<InventoryResponse> isINStock(@RequestParam List<String> skvCodes) {
       return inventoryService.isINStock(skvCodes);
   }
   
}
