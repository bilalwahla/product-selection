package com.df.location.model.response

import spock.lang.Specification

/**
 * Test specification for CustomerLocationServiceErrors.
 *
 * @author bilalwahla
 */
class CustomerLocationServiceErrorsSpec extends Specification {
  def "get and set error message"() {
    given:
    CustomerLocationServiceErrors customerLocationServiceErrors = new CustomerLocationServiceErrors(
        "Error")

    when:
    customerLocationServiceErrors.errorMessage = "Bug"

    then:
    customerLocationServiceErrors.errorMessage == "Bug"
  }
}
