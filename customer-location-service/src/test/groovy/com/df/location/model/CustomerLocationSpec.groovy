package com.df.location.model

import spock.lang.Specification

/**
 * Test specification for CustomerLocation model.
 *
 * @author bilalwahla
 */
class CustomerLocationSpec extends Specification {
  private CustomerLocation customerLocation = new CustomerLocation(
      customerId: '48a7617fccd24f4485a9e1e872acfc3a',
      location: 'MULTAN')

  def 'get and set customer id'() {
    when:
    customerLocation.customerId = '71de4f3a5f0c4e2ebd2aa9826f1347c5'

    then:
    customerLocation.customerId == '71de4f3a5f0c4e2ebd2aa9826f1347c5'
  }

  def 'get and set location'() {
    when:
    customerLocation.location = 'LAHORE'

    then:
    customerLocation.location == 'LAHORE'
  }
}
