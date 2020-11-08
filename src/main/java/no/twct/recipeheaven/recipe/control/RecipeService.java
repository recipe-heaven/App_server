package no.twct.recipeheaven.recipe.control;

import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RecipeService {

    @PersistenceContext
    EntityManager em;

    @Inject
    JsonWebToken token;

    @Inject
    AuthenticationService authenticationService;

    public void createRecipe(Recipe recipe) {
        recipe.setCreator(authenticationService.getCurrentUser(token.getName()));
        em.persist(recipe);
    }
}
