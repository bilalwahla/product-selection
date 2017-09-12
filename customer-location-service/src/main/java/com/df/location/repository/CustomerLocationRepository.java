package com.df.location.repository;

import com.df.location.model.CustomerLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bilalwahla
 */
@Repository
public interface CustomerLocationRepository extends CrudRepository<CustomerLocation, String> {
  CustomerLocation findByCustomerId(String customerId);
}
