package no.twct.recipeheaven.lib.models;

import javax.persistence.Entity;

@Entity
public class Ingredient {

	String name;

	int amount;

	String unitType;

	String comment;

}
