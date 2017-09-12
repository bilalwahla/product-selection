package com.df.catalogue.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.df.catalogue.model.Catalogue;
import com.df.catalogue.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to interact with the Catalogue data.
 *
 * @author bilalwahla
 */
@Service
public class CatalogueService {
  private static final String CATALOGUE_ID_DUMMY = "0000000-00-00000";
  private static final String CATALOGUE_NONE = "Sorry no catalogue information currently available";

  private final CatalogueRepository catalogueRepository;

  @Autowired
  public CatalogueService(CatalogueRepository catalogueRepository) {
    this.catalogueRepository = catalogueRepository;
  }

  @HystrixCommand(fallbackMethod = "buildFallbackCatalogueListByLocation",
      threadPoolKey = "catalogueByLocationThreadPool",
      threadPoolProperties = {
      @HystrixProperty(name = "coreSize", value = "30"),
          @HystrixProperty(name = "maxQueueSize", value = "10")
      },
      commandProperties = {
          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
          @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")}
  )
  public List<Catalogue> getCataloguesByLocation(String location) {
    return catalogueRepository.findByLocation(location);
  }

  List<Catalogue> buildFallbackCatalogueListByLocation(String location) {
    List<Catalogue> fallbackList = new ArrayList<>();
    Catalogue catalogue = new Catalogue().withId(CATALOGUE_ID_DUMMY).withCategory(CATALOGUE_NONE)
        .withProduct(CATALOGUE_NONE).withLocation(location);
    fallbackList.add(catalogue);
    return fallbackList;
  }

  List<Catalogue> buildFallbackCatalogueListByCategory(String category) {
    List<Catalogue> fallbackList = new ArrayList<>();
    Catalogue catalogue = new Catalogue().withId(CATALOGUE_ID_DUMMY).withCategory(category)
        .withProduct(CATALOGUE_NONE).withLocation(CATALOGUE_NONE);
    fallbackList.add(catalogue);
    return fallbackList;
  }

  @HystrixCommand(fallbackMethod = "buildFallbackCatalogueListByCategory")
  public List<Catalogue> getCataloguesByCategory(String category) {
    return catalogueRepository.findByCategory(category);
  }
}
