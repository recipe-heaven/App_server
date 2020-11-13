package no.twct.recipeheaven.entity;

import lombok.Data;
import no.twct.recipeheaven.user.entity.UserDetailsDTO;

import java.math.BigInteger;

/**
 * Describes an entity which has an ID and an owner
 */
@Data
public abstract class OwnableEntity {
    /**
     * The id of the entity
     */
    BigInteger id;

    /**
     * The owner of the entity
     */
    UserDetailsDTO owner;

}
