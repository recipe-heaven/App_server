package no.twct.recipeheaven.meal.boundry;

import no.twct.recipeheaven.meal.control.MealService;
import no.twct.recipeheaven.meal.entity.FullMealDTO;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.meal.entity.SimpleMealDTO;
import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.user.entity.Group;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

@Path("meal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MealResource {

    @Inject
    MealService mealService;

    /**
     * Creates a new meal for the logged in user.
     * The route is protected
     *
     * @param meal meal from request
     * @return returns success/fail response
     */
    @POST
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response createMeal(Meal meal) {
        mealService.createMeal(meal);
        return Response.ok().build();
    }

    /**
     * Returns a simplified meal with the given id.
     *
     * @param id of the meal
     * @return returns success/fail response
     */
    @GET
    @Path("simple/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMealSimple(@PathParam("id") BigInteger id) {
        SimpleMealDTO m = mealService.getSimpleMealDTO(id);
        return Response.ok(new DataResponse(m).getResponse()).build();
    }

    /**
     * Returns a meal with all details.
     *
     * @param id of the meal
     * @return returns success/fail response
     */
    @GET
    @Path("full/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMealFull(@PathParam("id") BigInteger id) {
        FullMealDTO m = mealService.getFullMealDTO(id);
        return Response.ok(new DataResponse(m).getResponse()).build();
    }
}
