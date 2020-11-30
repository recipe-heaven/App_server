package no.twct.recipeheaven.meal.boundry;

import no.twct.recipeheaven.lib.Resource;
import no.twct.recipeheaven.meal.control.MealService;
import no.twct.recipeheaven.meal.entity.FullMealDTO;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.meal.entity.SimpleMealDTO;
import no.twct.recipeheaven.user.entity.Group;
import no.twct.recipeheaven.util.StringParser;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

/**
 * Resource path for everything related to meal(s)
 */
@Path("meal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MealResource extends Resource {

    @Inject
    MealService mealService;

    /**
     * Creates a new meal for the logged in user and returns a 200 response.
     * If the validation fails, a response is returned with error messages for each violation.
     * On any other error a 500 server error is returned.
     * The route is protected.
     *
     * @param meal the new meal
     * @return returns response
     */
    @POST
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response createMeal(Meal meal) {
        try {
            var m =mealService.createMeal(meal);
            System.out.println(m.getId());
            createDataResponseOr404(m,"kljafd");
        } catch (
                ConstraintViolationException e) {
            createConstraintViolationResponse(e);
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }

    /**
     * Updates a meal with new details for the logged in user and returns a 200 response if successful.
     * If the validation fails, a response is returned with error messages for each violation.
     * On any other error a 500 server error is returned.
     * The route is protected.
     *
     * @param meal the updated meal
     * @return returns response
     */
    @PATCH
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response updateMeal(Meal meal) {
        try {
            mealService.updateMeal(meal);
        } catch (
                ConstraintViolationException e) {
            createConstraintViolationResponse(e);
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }

    /**
     * Returns a list of simple meals by the ids provided.
     * If no meals are found, an empty list is returned.
     * On any other error a 500 server error is returned.
     *
     * @param mealIds the ids of the meals to get as comma separated string
     * @return returns response
     */
    @GET
    @Path("multiple/simple")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMultipleSimple(@QueryParam("ids") String mealIds) {
        try {
            var idList = StringParser.convertCsvNumberedStringToBigInt(mealIds);
            createDataResponse(mealService.getMultipleSimple(idList));
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }

    /**
     * Returns a single simple meal by its id if it is found.
     * If no meal is found returns 404.
     * On any other error a 500 server error is returned.
     *
     * @param id the id of the meal to get
     * @return returns response
     */
    @GET
    @Path("simple/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMealSimple(@PathParam("id") BigInteger id) {
        try {
            SimpleMealDTO mealDTO = mealService.getSimpleMealDTO(id);
            createDataResponseOr404(mealDTO, "Can't find a meal with id " + id);
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }

    /**
     * Returns a single full meal by its id if it is found.
     * If no meal is found returns 404.
     * On any other error a 500 server error is returned.
     *
     * @param id the id of the meal to get
     * @return returns response
     */
    @GET
    @Path("full/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMealFull(@PathParam("id") BigInteger id) {
        try {
            FullMealDTO mealDTO = mealService.getFullMealDTO(id);
            createDataResponseOr404(mealDTO, "Can't find a meal with id " + id);
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }
}
