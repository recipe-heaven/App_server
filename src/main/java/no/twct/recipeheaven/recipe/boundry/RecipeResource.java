package no.twct.recipeheaven.recipe.boundry;


import no.twct.recipeheaven.recipe.control.RecipeService;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;
import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.user.entity.Group;
import no.twct.recipeheaven.util.StringParser;
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
     * Creates a new recipe for the logged in user.
     * The route is protected
     *
     * @return returns success/fail response
     */
    @GET
    @Path("full/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getRecipe(@PathParam("id") BigInteger id) {
        FullRecipeDTO recipeDTO = recipeService.getRecipe(id);
        if (recipeDTO != null){
            return Response.ok(new DataResponse(recipeDTO).getResponse()).build();
        }else {
            return Response.noContent().build();
        }

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
        RecipeDTO recipeDTO = recipeService.getSimpleRecipe(id);
        if (recipeDTO != null){
            return Response.ok(new DataResponse(recipeDTO).getResponse()).build();
        }else {
            return Response.noContent().build();
        }
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
        try {
            var idList = StringParser.convertCsvNumberedStringToBigInt(recipeIds);
            return Response.ok(new DataResponse(recipeService.getMultiplesimple(idList)).getResponse()).build();
        } catch (Exception e) {
        }
        return Response.serverError().build();
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

        // maby inject somhow so not create on every run
        Jsonb jsonb = JsonbBuilder.create();

        Recipe recipe = jsonb.fromJson(recipeString, Recipe.class);
        recipeService.createRecipe(recipe, photos);

        return Response.ok().build();


    }


}
