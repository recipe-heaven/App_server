package no.twct.recipeheaven.recipe.boundry;


import no.twct.recipeheaven.meal.entity.FullMealDTO;
import no.twct.recipeheaven.meal.entity.SimpleMealDTO;
import no.twct.recipeheaven.recipe.control.RecipeService;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;
import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.user.entity.Group;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;


@Path("recipe")
@Stateless
public class RecipeResource {

    @Inject
    RecipeService recipeService;



    /**
     * Returns a simplified recipe with the given id.
     *
     * @param id of the recipe
     * @return returns success/fail response
     */
    @GET
    @Path("simple/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMealSimple(@PathParam("id") BigInteger id) {
        RecipeDTO recipeDTO = recipeService.getSimpleRecipeDTO(id);
        return Response.ok(new DataResponse(recipeDTO).getResponse()).build();
    }

    /**
     * Returns a recipe with all details.
     *
     * @param id of the recipe
     * @return returns success/fail response
     */
    @GET
    @Path("full/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMealFull(@PathParam("id") BigInteger id) {
        FullRecipeDTO fullRecipeDTO = recipeService.getFullRecipeDTO(id);
        return Response.ok(new DataResponse(fullRecipeDTO).getResponse()).build();
    }


    /**
     * Creates a new recipe for the logged in user.
     * The route is protected
     *
     * @param recipeString the json string of the recipe
     * @param photos       the image to the recipe
     *
     * @return returns success/fail response
     */
    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response createRecipe(
            @FormDataParam("recipe") String recipeString,
            FormDataMultiPart photos

    ) {

        // maby inject somhow so not create on every run
        Jsonb jsonb = JsonbBuilder.create();

        Recipe recipe = jsonb.fromJson(recipeString, Recipe.class);
        recipeService.createRecipe(recipe, photos);

        return Response.ok().build();


    }


}
