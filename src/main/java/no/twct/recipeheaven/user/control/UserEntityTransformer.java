package no.twct.recipeheaven.user.control;

import no.twct.recipeheaven.user.entity.User;
import no.twct.recipeheaven.user.entity.UserDetailsDTO;

/**
 * Provides transformer methods to create user specific DTOs
 */
public class UserEntityTransformer {

    /**
     * Creates a UserDetailsDTO
     *
     * @param user the user to transform
     */
    public UserDetailsDTO createUserDetailsDTO(User user) {
        UserDetailsDTO userDetails = new UserDetailsDTO();
        userDetails.setId(user.getId());
        userDetails.setUsername(user.getUsername());
        return userDetails;
    }
}
