package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class ValidMenuItem extends CreatableBase {

}
