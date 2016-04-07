package com.deswaef.examples.restdocs.beer.repository;

import com.deswaef.examples.restdocs.beer.model.Beer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeerRepository extends MongoRepository<Beer, String> {

}
