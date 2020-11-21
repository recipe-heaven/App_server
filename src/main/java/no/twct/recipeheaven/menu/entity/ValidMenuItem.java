package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import no.twct.recipeheaven.lib.CreatableBase;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ValidMenuItem extends CreatableBase {

}
