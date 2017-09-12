package com.df.catalogue.repository;

import com.df.catalogue.model.Catalogue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogueRepository extends CrudRepository<Catalogue, String> {
  List<Catalogue> findByLocation(String location);

  List<Catalogue> findByCategory(String category);
}
