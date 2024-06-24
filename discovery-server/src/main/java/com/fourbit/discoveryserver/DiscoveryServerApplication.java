package com.fourbit.discoveryserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer // Behave as Registory server which can use to store client application ip address and port details
public class DiscoveryServerApplication {
   public static void main(String[] args) {
      SpringApplication.run(DiscoveryServerApplication.class, args);
   }
}
