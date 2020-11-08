package no.twct.recipeheaven.services;

import no.twct.recipeheaven.lib.response.DataResponse;
import no.twct.recipeheaven.lib.search.RecipeSearchResult;
import no.twct.recipeheaven.lib.search.ResultItem;
import no.twct.recipeheaven.lib.search.SearchOptions;
import no.twct.recipeheaven.lib.search.SearchResultContainer;
import no.twct.recipeheaven.lib.users.User;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * Search related endpoints.
 */
@Path("search")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchService {

    @Inject
    JsonWebToken token;

    @Inject
    AuthenticationService authenticationService;

    /**
     * Handles the searching for recipes, menus and meals.
     *
     * @return returns search result
     */
    @POST
    @PermitAll
    public Response search(SearchOptions options) {
        Response.ResponseBuilder resp;
        User                     user = authenticationService.getCurrentUser(token.getName());
        if (user == null) {
            options.setOwnedOnly(false);
        }
        System.out.println(options.isIncludeRecipes());

        var recipe = new RecipeSearchResult();
        recipe.setTitle("Kjottollder");
        recipe.setCookTime(1000);
        var ri = new ResultItem();
        ri.setType("recipe");
        ri.setData(recipe);
        var meal = "";
        var menu = "";

        var              res = new SearchResultContainer();
        List<ResultItem> ls  = new ArrayList<ResultItem>();
        ls.add(ri);
        res.setResult(ls);
        resp = Response.ok(new DataResponse(res).getResponse());

        return resp.build();
    }
}
