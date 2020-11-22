package no.twct.recipeheaven.recipe.boundry;


import no.twct.recipeheaven.lib.Resource;
import no.twct.recipeheaven.recipe.control.RecipeService;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;
import no.twct.recipeheaven.user.entity.Group;
import no.twct.recipeheaven.util.StringParser;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

/**
 * Resource path for everything related to recipe(s)
 */
@Path("recipe")
@Stateless
public class RecipeResource extends Resource {

    @Inject
    RecipeService recipeService;


    /**
     * Returns a single full recipe by its id if it is found.
     * If no recipe is found returns 404.
     * On any other error a 500 server error is returned.
     *
     * @param id the id of the recipe to get
     * @return returns response
     */
    @GET
    @Path("full/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getRecipe(@PathParam("id") BigInteger id) {
        try {
            FullRecipeDTO recipeDTO = recipeService.getRecipe(id);
            createDataResponseOr404(recipeDTO, "Can't find recipe with id " + id);
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }

    /**
     * Returns a single simple recipe by its id if it is found.
     * If no recipe is found returns 404.
     * On any other error a 500 server error is returned.
     *
     * @param id the id of the recipe to get
     * @return returns response
     */
    @GET
    @Path("simple/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getRecipeSimple(@PathParam("id") BigInteger id) {
        try {
            RecipeDTO recipeDTO = recipeService.getSimpleRecipe(id);
            createDataResponseOr404(recipeDTO, "Can't find recipe with id " + id);
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }

    /**
     * Returns a list of simple recipes by the ids provided.
     * If no recipes are found, an empty list is returned.
     * On any other error a 500 server error is returned.
     *
     * @param recipeIds the ids of the recipes to get as comma separated string
     * @return returns response
     */
    @GET
    @Path("multiple/simple")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMultipleSimple(@QueryParam("ids") String recipeIds) {
        try {
            var idList = StringParser.convertCsvNumberedStringToBigInt(recipeIds);
            createDataResponse(recipeService.getMultiplesimple(idList));
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }


    /**
     * Creates a new recipe for the logged in user and returns a 200 response.
     * If the validation fails, a response is returned with error messages for each violation.
     * On any other error a 500 server error is returned.
     * The route is protected.
     *
     * @param recipeString the json string of the recipe
     * @param photos       the image of the recipe
     * @return returns response
     */
    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response createRecipe(
            @FormDataParam("recipe") String recipeString,
            FormDataMultiPart photos

    ) {
        try {
            Jsonb  jsonb  = JsonbBuilder.create();
            Recipe recipe = jsonb.fromJson(recipeString, Recipe.class);
            recipeService.createRecipe(recipe, photos);
        } catch (ConstraintViolationException e) {
            createConstraintViolationResponse(e);
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }


}
