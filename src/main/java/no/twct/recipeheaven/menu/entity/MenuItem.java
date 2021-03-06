package no.twct.recipeheaven.menu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    MenuItem(int day){
        this.day = day;
    }
}
