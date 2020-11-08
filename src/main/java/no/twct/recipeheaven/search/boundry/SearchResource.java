package no.twct.recipeheaven.search.boundry;

import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.search.entity.RecipeSearchResult;
import no.twct.recipeheaven.search.entity.ResultItem;
import no.twct.recipeheaven.search.entity.SearchOptions;
import no.twct.recipeheaven.search.entity.SearchResultContainer;
import no.twct.recipeheaven.user.entity.User;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class SearchResource {

    @Inject
    JsonWebToken token;

    @Inject
    AuthenticationService authenticationService;

    @PersistenceContext
    EntityManager em;

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
        List<RecipeSearchResult> r = em.createQuery("SELECT NEW RecipeSearchResult(p.name , p.cookTime)  FROM Recipe p", RecipeSearchResult.class).getResultList();
        for (RecipeSearchResult obj : r) {
            System.out.println(obj.getCookTime());
            System.out.println(obj.getName());
        }
        var recipe = new RecipeSearchResult("Kjøttboller", 1000);
        var ri     = new ResultItem();
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
