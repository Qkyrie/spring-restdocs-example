package com.deswaef.examples.restdocs.beer.configuration;

import com.deswaef.examples.restdocs.beer.model.Beer;
import com.deswaef.examples.restdocs.beer.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BeerConfiguration {

    @Autowired
    private BeerRepository beerRepository;

    @PostConstruct
    public void init() {
        if (beerRepository.findAll().isEmpty()) {
            beerRepository.save(new Beer("Sint Bernardus Abt 12", 10.));
            beerRepository.save(new Beer("Duvel", 8.5));
            beerRepository.save(new Beer("Leffe (Brune)", 6.5));
        }
    }

}
