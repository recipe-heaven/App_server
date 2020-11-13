package no.twct.recipeheaven.menu.entity;

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
@Table(name = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends CreatableBase {

    String name;

    String type = Const.MENU_TYPE_NAME;

    @Column(name = "is_public")
    boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "menu_recipe_link")
    List<MenuRecipe> recipes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "menu_meal_link")
    List<MenuMeal> meals;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
}
