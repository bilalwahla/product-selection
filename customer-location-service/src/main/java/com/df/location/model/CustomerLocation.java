package com.df.location.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer location model.
 *
 * @author bilalwahla
 */
@Entity
@Table(name = "customer_location")
public class CustomerLocation {
  @Id
  @Column(name = "customer_id", nullable = false)
  private String customerId;

  @Column(name = "location", nullable = false)
  private String location;

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
