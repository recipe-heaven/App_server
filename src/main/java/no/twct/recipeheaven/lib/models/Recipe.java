package no.twct.recipeheaven.lib.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

import no.twct.recipeheaven.lib.users.User;

@Entity
@Table(name = "recipes")
public class Recipe {

	User creator;

	String name;

	ArrayList<String> tags;

	String description;

	int cookTime = 0;

	String type;

	boolean visible;

	ArrayList<Ingredient> ingredients;

	String cookingSteps;

	ArrayList<String> recommendedDrinks;

}
