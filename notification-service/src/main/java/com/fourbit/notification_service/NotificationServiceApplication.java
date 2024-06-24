package com.fourbit.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fourbit.notification_service.event.OrderPlaceEvent;

@SpringBootApplication
public class NotificationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationServiceApplication.class, args);
  }
  @KafkaListener(topics = "notificationTopic")
  public void handleNotification(OrderPlaceEvent orderPlaceEvent) {
     System.out.println("Received Notification for order : " +orderPlaceEvent.getOrderNumber());
  }
}
