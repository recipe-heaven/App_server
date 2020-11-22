package no.twct.recipeheaven.menu.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigInteger;

@Entity
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Min(0)
    @Max(6)
    private int day;

    private String type;

    @OneToOne
    private ValidMenuItem menuDayItem;
}
