package com.df.location.exception

import spock.lang.Specification

/**
 * Test specification for NoCustomerLocationFoundException.
 *
 * @author bilalwahla
 */
class NoCustomerLocationFoundExceptionSpec extends Specification {
  def 'should be able to construct the exception'() {
    when:
    NoCustomerLocationFoundException exception = new NoCustomerLocationFoundException('dodgyCustomerId')

    then:
    exception.message.contains('dodgyCustomerId')
  }
}
