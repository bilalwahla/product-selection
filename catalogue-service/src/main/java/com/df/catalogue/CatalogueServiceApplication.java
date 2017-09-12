package com.df.catalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author bilalwahla
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class CatalogueServiceApplication {
  @LoadBalanced
  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public Sampler defaultSampler() {
    return new AlwaysSampler();
  }

  public static void main(String[] args) {
    SpringApplication.run(CatalogueServiceApplication.class, args);
  }
}
