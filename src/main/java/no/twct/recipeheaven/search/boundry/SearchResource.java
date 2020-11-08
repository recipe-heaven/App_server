package no.twct.recipeheaven.search.boundry;

import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.search.control.SearchOptions;
import no.twct.recipeheaven.search.control.SearchService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Search related endpoints.
 */
@Path("search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    @Inject
    SearchService searchService;

    /**
     * Handles the searching for recipes, menus and meals.
     *
     * @return returns search result
     */
    @POST
    @PermitAll
    public Response search(SearchOptions options) {
        Response.ResponseBuilder resp;
        var                      res = searchService.performMealRecipeMenuSearch(options);
        resp = Response.ok(new DataResponse(res).getResponse());
        return resp.build();
    }
}
