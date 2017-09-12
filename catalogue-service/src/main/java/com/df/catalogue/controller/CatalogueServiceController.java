package com.df.catalogue.controller;

import com.df.catalogue.model.Catalogue;
import com.df.catalogue.service.CatalogueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping(value = "v1/catalogue")
public class CatalogueServiceController {
  private static final Logger logger = LoggerFactory.getLogger(CatalogueServiceController.class);

  private final CatalogueService catalogueService;

  @Autowired
  public CatalogueServiceController(CatalogueService catalogueService) {
    this.catalogueService = catalogueService;
  }

  @RequestMapping(value = "/{location}", method = RequestMethod.GET)
  public List<Catalogue> getCataloguesByLocation(@PathVariable("location") String location) {
    List<Catalogue> catalogues =  catalogueService.getCataloguesByLocation(location);
    // Always return channels in the 'News' category i.e. regardless of the location
    catalogues.addAll(catalogueService.getCataloguesByCategory("News"));
    return catalogues;
  }
}
