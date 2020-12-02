package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.entity.DisplayableEntity;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends DisplayableEntity {

    List<MenuRecipeDTO> recipes;

    List<MenuMealDTO> meals;
}
