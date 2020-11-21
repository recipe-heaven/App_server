package no.twct.recipeheaven.menu.entity;

import lombok.Data;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private int day;


    public abstract ItemTypes getItemType();

    public static enum ItemTypes{
        meal,
        recipe
    }
}
