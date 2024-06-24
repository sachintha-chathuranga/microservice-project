package com.fourbit.order_service.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fourbit.order_service.dto.OrderDto;
import com.fourbit.order_service.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
   private final OrderService orderService;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod") // This is a circute breaker logic
   @TimeLimiter(name = "inventory") // You need to provid same name inside of properties file
   // @Retry(name = "inventory")
   public CompletableFuture<String> placeOrder(@RequestBody OrderDto orderDto) {
      return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderDto));
   }

   public CompletableFuture<String> fallbackMethod(OrderDto orderDto, RuntimeException runtimeException) {
      return CompletableFuture.supplyAsync(() -> "Oops! Something went wron, Please order after sometime!");
   }
}
