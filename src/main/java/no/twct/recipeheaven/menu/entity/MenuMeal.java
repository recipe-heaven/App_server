package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.meal.entity.Meal;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menu_meals")
public class MenuMeal {

    private int day;

    @OneToOne
    private Meal meal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;


}
