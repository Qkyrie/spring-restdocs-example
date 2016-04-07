package com.deswaef.examples.restdocs.beer.rest.hateos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class BeerResource extends ResourceSupport {

    private final String internalId;
    private final String name;
    private final String alcohol;

    @JsonCreator
    public BeerResource(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("alcohol") String alcohol) {
        this.internalId = id;
        this.name = name;
        this.alcohol = alcohol;
    }

    public String getName() {
        return name;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public String getInternalId() {
        return internalId;
    }
}
