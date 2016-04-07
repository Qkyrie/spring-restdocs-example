package com.deswaef.examples.restdocs.beer.rest;

import com.deswaef.examples.restdocs.beer.model.Beer;
import com.deswaef.examples.restdocs.beer.rest.hateos.BeerResource;
import com.deswaef.examples.restdocs.beer.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/beers")
public class BeerRestController {

    private BeerService beerService;

    @Autowired
    public BeerRestController(BeerService beerService) {
        this.beerService = beerService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<BeerResource>> beers() {
        return new ResponseEntity<>(
                beerService.findAll()
                        .stream()
                        .map(this::constructBeerResource)
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity<BeerResource> beer(@PathVariable("id") String id) {
        Beer theBeer = beerService.findOne(id);
        return new ResponseEntity<>(
                constructBeerResource(theBeer),
                HttpStatus.OK
        );

    }

    private BeerResource constructBeerResource(Beer theBeer) {
        BeerResource beerResource = new BeerResource(theBeer.getId(), theBeer.getName(), theBeer.getAlcoholPercentage() + "°");
        beerResource
                .add(linkTo(methodOn(BeerRestController.class).beer(theBeer.getId())).withSelfRel());
        return beerResource;
    }


}
