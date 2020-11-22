package no.twct.recipeheaven.meal.boundry;

import no.twct.recipeheaven.meal.control.MealService;
import no.twct.recipeheaven.meal.entity.FullMealDTO;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.meal.entity.SimpleMealDTO;
import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.response.ErrorResponse;
import no.twct.recipeheaven.response.errors.ViolationErrorMessageBuilder;
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
public class MealResource {

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
        Response.ResponseBuilder response;
        try {
            mealService.createMeal(meal);
            response = Response.ok();
        } catch (
                ConstraintViolationException e) {
            var violations = new ViolationErrorMessageBuilder(e.getConstraintViolations()).getMessages();
            response = Response.ok(new ErrorResponse(violations));
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
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
        Response.ResponseBuilder response;
        try {
            mealService.updateMeal(meal);
            return Response.ok().build();
        } catch (
                ConstraintViolationException e) {
            var violations = new ViolationErrorMessageBuilder(e.getConstraintViolations()).getMessages();
            response = Response.ok(new ErrorResponse(violations));
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
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
        Response.ResponseBuilder response;
        try {
            var idList = StringParser.convertCsvNumberedStringToBigInt(mealIds);
            response = Response.ok(new DataResponse(mealService.getMultipleSimple(idList)));
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
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
        Response.ResponseBuilder response;
        try {
            SimpleMealDTO mealDTO = mealService.getSimpleMealDTO(id);
            if (mealDTO != null) {
                response = Response.ok(new DataResponse(mealDTO));
            } else {
                response = Response.ok(new ErrorResponse("Can't find a meal with id " + id)).status(Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
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
        Response.ResponseBuilder response;
        try {
            FullMealDTO mealDTO = mealService.getFullMealDTO(id);
            if (mealDTO != null) {
                response = Response.ok(new DataResponse(mealDTO));
            } else {
                response = Response.ok(new ErrorResponse("Can't find a meal with id " + id)).status(Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
    }
}
