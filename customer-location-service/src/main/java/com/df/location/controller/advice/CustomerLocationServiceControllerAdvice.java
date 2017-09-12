package com.df.location.controller.advice;

import com.df.location.exception.NoCustomerLocationFoundException;
import com.df.location.model.response.CustomerLocationServiceErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advice for CustomerLocationServiceController.
 *
 * @author bilalwahla
 */
@ControllerAdvice
public class CustomerLocationServiceControllerAdvice {
  @ResponseBody
  @ExceptionHandler(NoCustomerLocationFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  CustomerLocationServiceErrors noCustomerLocationFoundExceptionHandler(
      NoCustomerLocationFoundException e) {
    return new CustomerLocationServiceErrors(e.getMessage());
  }
}
