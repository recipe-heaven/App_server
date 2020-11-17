package no.twct.recipeheaven.search.boundry;

import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.search.control.SearchOptions;
import no.twct.recipeheaven.search.control.SearchService;
import no.twct.recipeheaven.search.entity.SearchResultContainer;
import no.twct.recipeheaven.user.entity.Group;

import javax.annotation.security.RolesAllowed;
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
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response search(SearchOptions options) {
        Response.ResponseBuilder resp;
        SearchResultContainer    res = searchService.performMealRecipeMenuSearch(options);
        resp = Response.ok(new DataResponse(res).getResponse());
        return resp.build();
    }
}
