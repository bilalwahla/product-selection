package com.df.location.service;

import com.df.location.exception.NoCustomerLocationFoundException;
import com.df.location.model.CustomerLocation;
import com.df.location.repository.CustomerLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

/**
 * Service to interact with CustomerLocation data.
 *
 * @author bilalwahla
 */
@Service
public class CustomerLocationService {
  private static final Logger logger = LoggerFactory.getLogger(CustomerLocationService.class);

  private final CustomerLocationRepository customerLocationRepository;

  private final Tracer tracer;

  @Autowired
  public CustomerLocationService(CustomerLocationRepository customerLocationRepository, Tracer tracer) {
    this.customerLocationRepository = customerLocationRepository;
    this.tracer = tracer;
  }

  public CustomerLocation getCustomerLocation(String customerId) {
    logger.debug("In the customerLocationService.getCustomerLocation() call");

    Span newSpan = tracer.createSpan("getCustomerLocation DB call");
    try {
      CustomerLocation customerLocation = customerLocationRepository.findByCustomerId(customerId);
      if (customerLocation != null)
        return customerLocation;
      else throw new NoCustomerLocationFoundException(customerId);
    } finally {
      newSpan.tag("peer.service", "postgres");
      newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
      tracer.close(newSpan);
    }
  }
}
