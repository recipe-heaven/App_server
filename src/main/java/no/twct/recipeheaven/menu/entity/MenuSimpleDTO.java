package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuSimpleDTO extends MenuDTO {

    List<MenuRecipeDTO> recipes;

    List<MenuMealDTO> meals;

}
