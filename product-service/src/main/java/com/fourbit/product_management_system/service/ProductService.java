package com.fourbit.product_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fourbit.product_management_system.dto.ProductDto;
import com.fourbit.product_management_system.mapper.ProductMapper;
import com.fourbit.product_management_system.model.Product;
import com.fourbit.product_management_system.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
   private final ProductRepository productRepository;

   public void createProduct(ProductDto productDto) {
      Product product = ProductMapper.mapToProduct(productDto);
      productRepository.save(product);
      log.info("Product {} is save", product.getId());
   }

   public List<ProductDto> getAllProducts() {
      List<Product> products = productRepository.findAll();
      return products.stream().map(product -> ProductMapper.mapToProductDto(product)).toList();
   }
}
