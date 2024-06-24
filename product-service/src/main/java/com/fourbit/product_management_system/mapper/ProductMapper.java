package com.fourbit.product_management_system.mapper;

import com.fourbit.product_management_system.dto.ProductDto;
import com.fourbit.product_management_system.model.Product;

public class ProductMapper {
	public static Product mapToProduct(ProductDto productDto) {
		Product product = new Product(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice() );
		return product;
	}

	public static ProductDto mapToProductDto(Product product) {
		ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice() );
		return productDto;
	}
}
