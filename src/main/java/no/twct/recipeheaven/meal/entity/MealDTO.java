package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.entity.OwnableEntity;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MealDTO extends OwnableEntity {
    String name;
    private Date updated;

}
