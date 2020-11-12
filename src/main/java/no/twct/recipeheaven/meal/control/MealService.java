package no.twct.recipeheaven.meal.control;


import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.meal.entity.MinifiedMealDTO;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;

@Transactional
public class MealService {

    @PersistenceContext
    EntityManager em;

    @Inject
    JsonWebToken token;

    @Inject
    AuthenticationService authenticationService;

    @Inject
    MealEntityTransformer mealEntityTransformer;

    /**
     * Creates a new meal, and inserts it into the database.
     *
     * @param meal the meal to save
     */
    public void createMeal(Meal meal) {
        meal.setCreator(authenticationService.getCurrentUser(token.getName()));
        em.persist(meal);
    }

    /**
     * Gets a meal from the database by its id, and returns a simplified projection of it.
     *
     * @param id the id of the meal to find
     * @return returns a meal object, or error response.
     */
    public MinifiedMealDTO getMinifiedMealDetail(BigInteger id) {
        return mealEntityTransformer.createMealDetailsDTO(em.find(Meal.class, id));
    }

    public Meal getFullMealDetails(BigInteger id) {
        return em.find(Meal.class, id);
    }


}
