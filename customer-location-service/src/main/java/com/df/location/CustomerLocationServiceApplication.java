package com.df.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

/**
 * @author bilalwahla
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class CustomerLocationServiceApplication {
  @Bean
  public Sampler defaultSampler() {
    return new AlwaysSampler();
  }

  public static void main(String[] args) {
    SpringApplication.run(CustomerLocationServiceApplication.class, args);
  }
}
