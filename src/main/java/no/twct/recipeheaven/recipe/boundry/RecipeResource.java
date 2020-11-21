package no.twct.recipeheaven.recipe.boundry;


import no.twct.recipeheaven.recipe.control.RecipeService;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;
import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.response.ErrorResponse;
import no.twct.recipeheaven.response.errors.ViolationErrorMessageBuilder;
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


@Path("recipe")
@Stateless
public class RecipeResource {

    @Inject
    RecipeService recipeService;


    /**
     * Returns a recipes with all fields by its id.
     * If the recipe is not found, return 404.
     * On error 500 server error is returned.
     *
     * @return returns response
     */
    @GET
    @Path("full/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getRecipe(@PathParam("id") BigInteger id) {
        Response.ResponseBuilder response;
        try {
            FullRecipeDTO recipeDTO = recipeService.getRecipe(id);
            if (recipeDTO != null) {
                response = Response.ok(new DataResponse(recipeDTO));
            } else {
                response = Response.ok(new ErrorResponse("Can't find recipe with id " + id)).status(Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
    }

    /**
     * Creates a new recipe for the logged in user.
     * The route is protected
     *
     * @return returns success/fail response
     */
    @GET
    @Path("simple/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getRecipeSimple(@PathParam("id") BigInteger id) {
        Response.ResponseBuilder response;
        RecipeDTO                recipeDTO = recipeService.getSimpleRecipe(id);
        if (recipeDTO != null) {
            response = Response.ok(new DataResponse(recipeDTO));
        } else {
            response = Response.ok(new ErrorResponse("Can't find recipe with id " + id)).status(Response.Status.NOT_FOUND);
        }
        return response.build();
    }

    /**
     * Returns a list of simple recipes from ids provided as query param ?ids=1,2,3,4
     *
     * @param recipeIds the id of the recipes to get as numbered strings comma separated
     * @return returns a response with list of simple recipes or empty list or server error.
     */
    @GET
    @Path("multiple/simple")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMultipleSimple(@QueryParam("ids") String recipeIds) {
        Response.ResponseBuilder response;
        try {
            var idList = StringParser.convertCsvNumberedStringToBigInt(recipeIds);
            response = Response.ok(new DataResponse(recipeService.getMultiplesimple(idList)));
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
    }

    /**
     * Creates a new recipe for the logged in user.
     * The route is protected
     *
     * @param recipeString the json string of the recipe
     * @param photos       the image to the recipe
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
        Response.ResponseBuilder response;
        try {
            Jsonb  jsonb  = JsonbBuilder.create();
            Recipe recipe = jsonb.fromJson(recipeString, Recipe.class);
            recipeService.createRecipe(recipe, photos);
            response = Response.ok();
        } catch (ConstraintViolationException e) {
            var violations = new ViolationErrorMessageBuilder(e.getConstraintViolations()).getMessages();
            response = Response.ok(new ErrorResponse(violations));
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
    }


}
