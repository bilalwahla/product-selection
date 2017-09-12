package com.df.catalogue.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catalogue model.
 *
 * @author bilalwahla
 */
@Entity
@Table(name = "catalogue")
public class Catalogue {
  @Id
  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "category", nullable = false)
  private String category;

  @Column(name = "product", nullable = false)
  private String product;

  @Column(name = "location", nullable = true)
  private String location;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Catalogue withId(String id) {
    this.setId(id);
    return this;
  }

  public Catalogue withCategory(String category) {
    this.setCategory(category);
    return this;
  }

  public Catalogue withProduct(String product) {
    this.setProduct(product);
    return this;
  }

  public Catalogue withLocation(String location) {
    this.setLocation(location);
    return this;
  }
}
