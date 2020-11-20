package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.meal.entity.MealDTO;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class MenuMealDTO {

    private int day;

    private MealDTO meal;

    private BigInteger id;

}
