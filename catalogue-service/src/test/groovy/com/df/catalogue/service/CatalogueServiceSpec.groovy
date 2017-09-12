package com.df.catalogue.service

import com.df.catalogue.model.Catalogue
import com.df.catalogue.repository.CatalogueRepository
import spock.lang.Specification

/**
 * Test specification for CatalogueService.
 *
 * @author bilalwahla
 */
class CatalogueServiceSpec extends Specification {
  private static final String CATALOGUE_ID_DUMMY = '0000000-00-00000'
  private static final String CATALOGUE_NONE = 'Sorry no catalogue information currently available'
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

  private CatalogueService catalogueService
  private CatalogueRepository catalogueRepository = Mock()

  def setup() {
    catalogueService = new CatalogueService(catalogueRepository)
  }

  def 'should be able to retrieve catalogues by location'() {
    given:
    String location = LOCATION_LONDON

    when:
    List<Catalogue> catalogues = catalogueService.getCataloguesByLocation(location)

    then:
    1 * catalogueRepository.findByLocation(LOCATION_LONDON) >> CATALOGUES_LONDON
    catalogues.size() == 2
    catalogues.findAll { it.location == LOCATION_LONDON }.size() == 2
  }

  def 'should retrieve dummy catalogues (by location) if the datasource takes too long to respond'() {
    given:
    String location = LOCATION_LONDON

    when:
    List<Catalogue> catalogues = catalogueService.getCataloguesByLocation(location)

    then:
    1 * catalogueRepository.findByLocation(_) >> {
      String l -> catalogueService.buildFallbackCatalogueListByLocation(l)
    }
    catalogues.size() == 1
    catalogues.id[0] == CATALOGUE_ID_DUMMY
    catalogues.category[0] == CATALOGUE_NONE
    catalogues.product[0] == CATALOGUE_NONE
    catalogues.location[0] == location
  }

  def 'should be able to retrieve catalogues by category'() {
    given:
    String category = CATEGORY_NEWS

    when:
    List<Catalogue> catalogues = catalogueService.getCataloguesByCategory(category)

    then:
    1 * catalogueRepository.findByCategory(_) >> CATALOGUES_NEWS
    catalogues.size() == 2
    catalogues.findAll { it.location == '' }.size() == 2
    catalogues.findAll { it.category == CATEGORY_NEWS }.size() == 2
  }

  def 'should retrieve dummy catalogues (by category) if the datasource takes too long to respond'() {
    given:
    String category = CATEGORY_NEWS

    when:
    List<Catalogue> catalogues = catalogueService.getCataloguesByCategory(category)

    then:
    1 * catalogueRepository.findByCategory(_) >> {
      String c -> catalogueService.buildFallbackCatalogueListByCategory(c)
    }
    catalogues.size() == 1
    catalogues.id[0] == CATALOGUE_ID_DUMMY
    catalogues.category[0] == category
    catalogues.product[0] == CATALOGUE_NONE
    catalogues.location[0] == CATALOGUE_NONE
  }
}
