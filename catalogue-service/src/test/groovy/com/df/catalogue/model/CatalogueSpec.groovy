package com.df.catalogue.model

import spock.lang.Specification

/**
 * Test specification for Catalogue model.
 *
 * @author bilalwahla
 */
class CatalogueSpec extends Specification {
  private static final String CATEGORY_SPORTS = 'Sports'

  private Catalogue catalogue1 = new Catalogue(id: '3cda218d738f40b8897300a168d81508',
      category: CATEGORY_SPORTS,
      product: 'Lahore Qalandars TV',
      location: 'LAHORE')

  private Catalogue catalogue2 = new Catalogue()

  def 'get and set id'() {
    when:
    catalogue1.id = '5c089e513a0c4c9ba714f02d07d8a9b1'

    then:
    catalogue1.id == '5c089e513a0c4c9ba714f02d07d8a9b1'
  }

  def 'get and set category'() {
    when:
    catalogue1.category = CATEGORY_SPORTS

    then:
    catalogue1.category == CATEGORY_SPORTS
  }

  def 'get and set product'() {
    when:
    catalogue1.product = 'Islamabad United TV'

    then:
    catalogue1.product == 'Islamabad United TV'
  }

  def 'get and set location'() {
    when:
    catalogue1.location = 'ISLAMABAD'

    then:
    catalogue1.location == 'ISLAMABAD'
  }

  def 'catalogue with id'() {
    when:
    catalogue2.withId('fcf1dc2911514a4697a6e5e825be0409')

    then:
    catalogue2.id == 'fcf1dc2911514a4697a6e5e825be0409'
  }

  def 'catalogue with category'() {
    when:
    catalogue2.withCategory(CATEGORY_SPORTS)

    then:
    catalogue2.category == CATEGORY_SPORTS
  }

  def 'catalogue with product'() {
    when:
    catalogue2.withProduct('Peshawar Zalmi TV')

    then:
    catalogue2.product == 'Peshawar Zalmi TV'
  }

  def 'catalogue with location'() {
    when:
    catalogue2.withLocation('PESHAWAR')

    then:
    catalogue2.location == 'PESHAWAR'
  }
}
