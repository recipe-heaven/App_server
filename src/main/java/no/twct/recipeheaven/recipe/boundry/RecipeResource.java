package no.twct.recipeheaven.recipe.boundry;


import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("recipe")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {

    @POST
    @Path("create")
    public Response createRecipe(Recipe e) {
        System.out.println(e.getName());

        return Response.ok().build();
    }

}