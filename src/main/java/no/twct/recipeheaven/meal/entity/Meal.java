package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.Const;
import no.twct.recipeheaven.lib.CreatableBase;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "meals")
@EqualsAndHashCode(callSuper = true)
public class Meal extends CreatableBase {

    String name;

    String type = Const.MEAL_TYPE_NAME;

    @Column(name = "is_public")
    boolean isPublic;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "meal_type_link")
    List<MealRecipeType> mealRecipeTypes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

}

