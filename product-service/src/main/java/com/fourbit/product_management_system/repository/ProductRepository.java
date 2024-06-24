package com.fourbit.product_management_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fourbit.product_management_system.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

  
}