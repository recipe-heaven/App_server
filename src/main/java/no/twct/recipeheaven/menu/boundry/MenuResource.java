package no.twct.recipeheaven.menu.boundry;

import no.twct.recipeheaven.menu.control.MenuService;
import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.user.entity.Group;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

@Path("menu")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {

    @Inject
    MenuService menuService;

    /**
     * Creates a new menu for the logged in user.
     * The route is protected
     *
     * @param menu menu from request
     * @return returns success/fail response
     */
    @POST
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response createMenu(Menu menu) {
        menuService.createMenu(menu);
        return Response.ok().build();
    }

    /**
     * Updates a menu.
     * The route is protected
     *
     * @param menu menu from request
     * @return returns success/fail response
     */
    @PATCH
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response updateMenu(Menu menu) {
        menuService.updateMenu(menu);
        return Response.ok().build();
    }

    /**
     * Returns a simplified menu with the given id
     *
     * @param id of the menu
     * @return returns success/fail response
     */
    @GET
    @Path("simple/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMealSimple(@PathParam("id") BigInteger id) {
        var m = menuService.getSimpleMenuDTO(id);
        return Response.ok(new DataResponse(m).getResponse()).build();
    }

    /**
     * Returns a full menu with the given id
     *
     * @param id of the menu
     * @return returns success/fail response
     */
    @GET
    @Path("full/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMealFull(@PathParam("id") BigInteger id) {
        var m = menuService.getFullMenuDTO(id);
        return Response.ok(new DataResponse(m).getResponse()).build();
    }
}
