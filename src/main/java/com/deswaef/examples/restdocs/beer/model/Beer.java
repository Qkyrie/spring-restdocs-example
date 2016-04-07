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

	@Override public String toString() {
		return "Beer{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", alcoholPercentage=" + alcoholPercentage +
				'}';
	}
}
