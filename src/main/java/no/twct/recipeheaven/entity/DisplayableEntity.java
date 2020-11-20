package no.twct.recipeheaven.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * A displayable entity is a type entity which has a name, can be public or private and
 * a date when it was updated. It is a child of an ownable entity,
 * as a display type is create by a user which is the owner.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DisplayableEntity extends OwnableEntity {

    String name;

    boolean isPublic;

    private Date updated;
}
