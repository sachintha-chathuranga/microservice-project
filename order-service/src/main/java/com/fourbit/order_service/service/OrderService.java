package com.fourbit.order_service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.fourbit.order_service.dto.InventoryResponse;
import com.fourbit.order_service.dto.OrderDto;
import com.fourbit.order_service.dto.OrderLineItemDto;
import com.fourbit.order_service.event.OrderPlaceEvent;
import com.fourbit.order_service.mapper.OrderMapper;
import com.fourbit.order_service.model.Order;
import com.fourbit.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
   private final OrderRepository orderRepository;

   private final WebClient.Builder webClientBuilder;

   private final KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;

   public String placeOrder(OrderDto orderDto) {
      // Call inventory service to check item availability
      List<String> skvCodes = orderDto.getOrderLineItemList().stream().map(OrderLineItemDto::getSkvCode)
         .toList();
      InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
            .uri("http://inventory-service/api/inventory",
            uriBuilder -> uriBuilder.queryParam("skvCodes", skvCodes).build())
         .retrieve()
         .bodyToMono(InventoryResponse[].class)
            .block();
      boolean allProductInStock  = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
      if (!allProductInStock)
         throw new IllegalArgumentException("Product Not in stock!");
      Order order = OrderMapper.mapToOrder(orderDto);
      order.setOrderNumber(UUID.randomUUID().toString());
      orderRepository.save(order);
      kafkaTemplate.send("notificationTopic", new OrderPlaceEvent(order.getOrderNumber()));
      return "Order Placed Successfully";
   }
}
