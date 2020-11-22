package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import no.twct.recipeheaven.lib.CreatableBase;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private int day;

    private String type;

    @OneToOne
    private ValidMenuItem menuDayItem;
}
