package com.fourbit.order_service.mapper;

import com.fourbit.order_service.dto.OrderLineItemDto;
import com.fourbit.order_service.model.OrderLineItem;


public class OrderListLineMapper {
public static OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto) {
		OrderLineItem orderLineItem = new OrderLineItem(orderLineItemDto.getId(), orderLineItemDto.getSkvCode(), orderLineItemDto.getPrice(), orderLineItemDto.getQuantity());
		return orderLineItem;
	}

	public static OrderLineItemDto mapToOrderLineItemDto(OrderLineItem orderLineItem) {
		OrderLineItemDto orderLineItemDto = new OrderLineItemDto(orderLineItem.getId(), orderLineItem.getSkvCode(), orderLineItem.getPrice(), orderLineItem.getQuantity() );
		return orderLineItemDto;
	}
}
