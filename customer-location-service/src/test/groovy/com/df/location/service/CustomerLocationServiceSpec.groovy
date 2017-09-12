package com.df.location.service

import com.df.location.exception.NoCustomerLocationFoundException
import com.df.location.model.CustomerLocation
import com.df.location.repository.CustomerLocationRepository
import org.springframework.cloud.sleuth.DefaultSpanNamer
import org.springframework.cloud.sleuth.Sampler
import org.springframework.cloud.sleuth.Span
import org.springframework.cloud.sleuth.TraceKeys
import org.springframework.cloud.sleuth.Tracer
import org.springframework.cloud.sleuth.log.SpanLogger
import org.springframework.cloud.sleuth.sampler.AlwaysSampler
import org.springframework.cloud.sleuth.trace.DefaultTracer
import org.springframework.cloud.sleuth.util.ArrayListSpanAccumulator
import spock.lang.Specification

/**
 * Test specification for CustomerLocationService.
 *
 * @author bilalwahla
 */
class CustomerLocationServiceSpec extends Specification {
  private static final String LOCATION_LAHORE = 'LAHORE'
  private CustomerLocationRepository customerLocationRepository = Mock()

  private Sampler sampler = new AlwaysSampler();
  private SpanLogger spanLogger = Mock()
  ArrayListSpanAccumulator spanReporter = new ArrayListSpanAccumulator();
  private Span span;

  private class DelegateSampler implements Sampler {
    @Override
    public boolean isSampled(Span span) {
      return CustomerLocationServiceSpec.this.sampler.isSampled(span);
    }
  }

  private Tracer tracer = Mock()

  private CustomerLocationService customerLocationService

  def setup() {
    tracer = new DefaultTracer(new DelegateSampler(), new Random(), new DefaultSpanNamer(),
        spanLogger, spanReporter, new TraceKeys()) {
      @Override
      public Span continueSpan(Span span) {
        CustomerLocationServiceSpec.this.span = super.continueSpan(span);
        return CustomerLocationServiceSpec.this.span;
      }
    };
    customerLocationService = new CustomerLocationService(customerLocationRepository, tracer)
  }

  def 'should be able to retrieve customer location'() {
    given:
    String customerId = 'uniqueCustomerId'

    when:
    CustomerLocation customerLocation = customerLocationService.getCustomerLocation(customerId)

    then:
    1 * customerLocationRepository.findByCustomerId(_) >> [
        customerId: customerId, location: LOCATION_LAHORE
    ]
    customerLocation.customerId == customerId
    customerLocation.location == LOCATION_LAHORE
  }

  def 'should throw an exception when retrieving location for an unknown customer id'() {
    given:
    String unknownCustomerId = 'unknownCustomerId'

    when:
    CustomerLocation customerLocation = customerLocationService.getCustomerLocation(unknownCustomerId)

    then:
    1 * customerLocationRepository.findByCustomerId(_) >> null
    NoCustomerLocationFoundException noCustomerLocationFoundException = thrown()
    noCustomerLocationFoundException.getMessage().contains(unknownCustomerId)
  }
}
