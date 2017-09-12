package com.df.location.model.response;

/**
 * A representation model class to be rendered for service errors.
 *
 * @author bilalwahla
 */
public class CustomerLocationServiceErrors {
  private String errorMessage;

  public CustomerLocationServiceErrors(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
