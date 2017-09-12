package com.df.catalogue.controller

import com.df.catalogue.model.Catalogue
import com.df.catalogue.service.CatalogueService
import spock.lang.Specification

/**
 * Test specification for CatalogueServiceController.
 *
 * @author bilalwahla
 */
class CatalogueServiceControllerSpec extends Specification {
  private static final String LOCATION_LONDON = 'LONDON'
  private static final String CATEGORY_SPORTS = 'Sports'
  private static final String CATEGORY_NEWS = 'News'

  private static final List<Catalogue> CATALOGUES_LONDON = [
      [
          id: 'bd41f437b7a64cf6885a16aa9f02e143',
          category: CATEGORY_SPORTS,
          product: 'Arsenal TV',
          location: LOCATION_LONDON
      ],
      [
          id: 'db135ee8710f4dc7ab65f31cb5aaa86b',
          category: CATEGORY_SPORTS,
          product: 'Chelsea TV',
          location: LOCATION_LONDON
      ]
  ] as List<Catalogue>

  private static final List<Catalogue> CATALOGUES_NEWS = [
      [
          id: '9dd135b1c5e54068accc45ccd42300a4',
          category: CATEGORY_NEWS,
          product: 'Sky News',
          location: ''
      ],
      [
          id: 'bb01e329729646f395f33424a54f00f3',
          category: CATEGORY_NEWS,
          product: 'Sky Sports News',
          location: ''
      ]
  ] as List<Catalogue>

  private CatalogueServiceController catalogueServiceController
  private CatalogueService catalogueService = Mock()

  def setup() {
    catalogueServiceController = new CatalogueServiceController(catalogueService)
  }

  def 'should be able to retrieve catalogues by location'() {
    given:
    String location = LOCATION_LONDON

    when:
    List<Catalogue> catalogues = catalogueServiceController.getCataloguesByLocation(location)

    then:
    1 * catalogueService.getCataloguesByLocation(_) >> CATALOGUES_LONDON
    1 * catalogueService.getCataloguesByCategory(_) >> CATALOGUES_NEWS
    catalogues.size() == 4
    catalogues.findAll { it.location == LOCATION_LONDON }.size() == 2
    catalogues.findAll { it.location == '' }.size() == 2
  }
}
