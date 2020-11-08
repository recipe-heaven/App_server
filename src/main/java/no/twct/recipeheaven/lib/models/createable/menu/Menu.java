package no.twct.recipeheaven.lib.models.createable.menu;

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
@Table(name = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends CreatableBase {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated;
}
