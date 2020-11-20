package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.meal.entity.Meal;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menu_meals")
public class MenuMeal {

    @Min(0)
    private int day;

    @NotNull
    @OneToOne
    private Meal meal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;


}
