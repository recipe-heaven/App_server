package no.twct.recipeheaven.menu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.meal.entity.Meal;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MenuMeal extends MenuItem {
    private Meal meal;
}
