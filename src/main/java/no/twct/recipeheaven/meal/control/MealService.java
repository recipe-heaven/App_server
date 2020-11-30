package no.twct.recipeheaven.meal.control;


import no.twct.recipeheaven.meal.entity.FullMealDTO;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.meal.entity.SimpleMealDTO;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class MealService {

    @PersistenceContext
    EntityManager entityManager;

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
    public Meal createMeal(Meal meal) {
        meal.setCreator(authenticationService.getLoggedInUser());

        return entityManager.merge(meal);
    }

    /**
     * Updates a meal in the database
     *
     * @param updatedMeal the edited meal
     */
    public void updateMeal(Meal updatedMeal) {
        var mealInDb = entityManager.find(Meal.class, updatedMeal.getId());
        mealInDb.setName(updatedMeal.getName());
        mealInDb.setPublic(updatedMeal.isPublic());
        mealInDb.setRecipes(updatedMeal.getRecipes());
        entityManager.persist(mealInDb);
    }


    /**
     * Gets a meal from the database by its id, and returns a simplified projection of it.
     *
     * @param id the id of the meal to find
     * @return returns a meal object, or error response.
     */
    public SimpleMealDTO getSimpleMealDTO(BigInteger id) {
        return mealEntityTransformer.createSimpleMealDTO(entityManager.find(Meal.class, id));
    }

    public FullMealDTO getFullMealDTO(BigInteger id) {
        return mealEntityTransformer.createFullMealDTO(entityManager.find(Meal.class, id));
    }


    /**
     * Returns multiple simplified meals
     *
     * @param mealIds list of ids for the meals to get
     * @return return list containing 0 or more simplified meals DTOs
     */
    public List<SimpleMealDTO> getMultipleSimple(List<BigInteger> mealIds) {
        var recipesQuery = entityManager.createNamedQuery(Meal.GET_MULTIPLE_MEALS, Meal.class);
        recipesQuery.setParameter("ids", mealIds);
        var recipes = recipesQuery.getResultList();
        return recipes.stream().map(mealEntityTransformer::createSimpleMealDTO).collect(Collectors.toList());
    }


}
