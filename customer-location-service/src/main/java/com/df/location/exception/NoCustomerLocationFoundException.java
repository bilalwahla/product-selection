package com.df.location.exception;

/**
 * This exception is thrown when trying to find customer location that does not exist.
 *
 * @author bilalwahla
 */
public class NoCustomerLocationFoundException extends RuntimeException {
  public NoCustomerLocationFoundException(final String customerId) {
    super("No customer location found for customer id: " + customerId + ".");
  }
}
