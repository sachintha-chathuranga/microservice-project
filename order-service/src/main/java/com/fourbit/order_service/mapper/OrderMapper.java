package com.fourbit.order_service.mapper;


import java.util.List;

import com.fourbit.order_service.dto.OrderDto;
import com.fourbit.order_service.dto.OrderLineItemDto;
import com.fourbit.order_service.model.Order;
import com.fourbit.order_service.model.OrderLineItem;

public class OrderMapper {
   public static Order mapToOrder(OrderDto orderDto) {
      List<OrderLineItem> orderLineItems = orderDto.getOrderLineItemList().stream().map(item -> OrderListLineMapper.mapToOrderLineItem(item)).toList();
		Order order = new Order(orderDto.getId(), orderDto.getOrderNumber(), orderLineItems);
		return order;
	}

    public static OrderDto mapToOrderDto(Order order) {
      List<OrderLineItemDto> orderLineItemDtos = order.getOrderLineItemList().stream().map(item -> OrderListLineMapper.mapToOrderLineItemDto(item)).toList();
		OrderDto orderDto = new OrderDto(order.getId(), order.getOrderNumber(), orderLineItemDtos);
		return orderDto;
	}
}
