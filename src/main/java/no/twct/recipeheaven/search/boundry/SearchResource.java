package no.twct.recipeheaven.search.boundry;

import no.twct.recipeheaven.lib.Resource;
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
public class SearchResource extends Resource {

    @Inject
    SearchService searchService;

    /**
     * Performs search in the database for recipes, menus and meals based on the
     * provided search options.
     * Returns a list of results, or an empty list of there are no results.
     * On any other errors 500 message is returned.
     *
     * @return returns response
     */
    @POST
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response search(SearchOptions options) {
        try {
            SearchResultContainer searchResult = searchService.performMealRecipeMenuSearch(options);
            createDataResponse(searchResult);
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }
}
