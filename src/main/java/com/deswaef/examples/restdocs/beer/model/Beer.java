package com.deswaef.examples.restdocs.beer.model;

import org.springframework.data.annotation.Id;

public class Beer {

	@Id
	private String id;

	private String name;
	private Double alcoholPercentage;

	public Beer() {
	}

	public Beer(String name, Double alcoholPercentage) {
		this.name = name;
		this.alcoholPercentage = alcoholPercentage;
	}

	public String getId() {
		return id;
	}

	public Beer setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Beer setName(String name) {
		this.name = name;
		return this;
	}

	public Double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public Beer setAlcoholPercentage(Double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
		return this;
	}

	@Override public String toString() {
		return "Beer{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", alcoholPercentage=" + alcoholPercentage +
				'}';
	}
}
