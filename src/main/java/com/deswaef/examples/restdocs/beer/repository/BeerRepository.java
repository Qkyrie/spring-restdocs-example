package com.deswaef.examples.restdocs.beer.repository;

import com.deswaef.examples.restdocs.beer.model.Beer;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface BeerRepository extends MongoRepository<Beer, String> {

}
