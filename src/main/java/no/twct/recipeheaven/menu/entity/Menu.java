package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@SqlResultSetMapping(
        name = "MenuSearchResult",
        classes = {
                @ConstructorResult(
                        targetClass = no.twct.recipeheaven.search.entity.MenuSearchResult.class,
                        columns = {
                                @ColumnResult(name = "id", type = BigInteger.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "days", type = Integer[].class)})})
@Data
@Entity
@NoArgsConstructor
@Table(name = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends CreatableBase {

    @NotEmpty
    String name;

    @Column(name = "is_public")
    boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    List<@Valid MenuRecipe> recipes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    List<@Valid MenuMeal> meals;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
}
