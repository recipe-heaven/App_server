package no.twct.recipeheaven.menu.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigInteger;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Min(0)
    @Max(6)
    private int day;

    public abstract ItemTypes getItemType();

    public enum ItemTypes {
        meal,
        recipe
    }
}
