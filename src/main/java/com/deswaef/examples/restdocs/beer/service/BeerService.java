package com.deswaef.examples.restdocs.beer.service;

import com.deswaef.examples.restdocs.beer.model.Beer;
import com.deswaef.examples.restdocs.beer.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    private BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Beer findOne(String id) {
        return beerRepository.findOne(id);
    }

    public void save(Beer beer) {
        beerRepository.save(beer);
    }
}
