package no.twct.recipeheaven.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * User details data transfer object
 * Contains only username and user id of a user.
 */
@Data
@NoArgsConstructor
public class UserDetailsDTO {
    String username;
    BigInteger id;
}
