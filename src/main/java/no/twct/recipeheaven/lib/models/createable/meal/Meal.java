package no.twct.recipeheaven.lib.models.createable.meal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.models.createable.CreatableBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "meals")
@EqualsAndHashCode(callSuper = true)
public class Meal extends CreatableBase {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated;

}
