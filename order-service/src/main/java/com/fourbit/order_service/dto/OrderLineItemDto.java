package com.fourbit.order_service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto {
   private Long id;
   private String skvCode;
   private BigDecimal price;
   private Integer quantity;
}
