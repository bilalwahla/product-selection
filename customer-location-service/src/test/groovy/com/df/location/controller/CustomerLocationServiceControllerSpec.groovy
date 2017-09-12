package com.df.location.controller

import com.df.location.exception.NoCustomerLocationFoundException
import com.df.location.model.CustomerLocation
import com.df.location.service.CustomerLocationService
import spock.lang.Specification

/**
 * Test specification for CustomerLocationServiceController.
 *
 * @author bilalwahla
 */
class CustomerLocationServiceControllerSpec extends Specification {
  private static final String LOCATION_MULTAN = 'MULTAN'

  private CustomerLocationServiceController customerLocationServiceController
  private CustomerLocationService customerLocationService = Mock()

  def setup() {
    customerLocationServiceController = new CustomerLocationServiceController(customerLocationService)
  }

  def 'should be able to retrieve customer location'() {
    given:
    String customerId = 'uniqueCustomerId'

    when:
    CustomerLocation customerLocation = customerLocationServiceController.getCustomerLocation(customerId)

    then:
    1 * customerLocationService.getCustomerLocation(_) >> [
        customerId: customerId, location: LOCATION_MULTAN
    ]
    customerLocation.customerId == customerId
    customerLocation.location == LOCATION_MULTAN
  }

  def 'should throw an exception when retrieving location for an unknown customer id'() {
    given:
    String unknownCustomerId = 'unknownCustomerId'

    when:
    customerLocationServiceController.getCustomerLocation(unknownCustomerId)

    then:
    1 * customerLocationService.getCustomerLocation(_) >> {
      throw new NoCustomerLocationFoundException(unknownCustomerId)
    }
    NoCustomerLocationFoundException noCustomerLocationFoundException = thrown()
    noCustomerLocationFoundException.getMessage().contains(unknownCustomerId)
  }
}
