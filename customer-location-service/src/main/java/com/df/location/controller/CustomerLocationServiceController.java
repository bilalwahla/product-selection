package com.df.location.controller;

import com.df.location.model.CustomerLocation;
import com.df.location.service.CustomerLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bilalwahla
 */
@RestController
@RequestMapping(value = "v1/customer-location")
public class CustomerLocationServiceController {
  private static final Logger logger = LoggerFactory.getLogger(CustomerLocationServiceController.class);

  private final CustomerLocationService customerLocationService;

  @Autowired
  public CustomerLocationServiceController(CustomerLocationService customerLocationService) {
    this.customerLocationService = customerLocationService;
  }

  @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
  public CustomerLocation getCustomerLocation(@PathVariable("customerId") String customerId) {
    logger.debug("Entering the getCustomerLocation() method for the customerId: {}", customerId);
    return customerLocationService.getCustomerLocation(customerId);
  }
}
