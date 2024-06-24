package com.fourbit.order_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
   private Long id;
   private String orderNumber;
   private List<OrderLineItemDto> orderLineItemList;
}
