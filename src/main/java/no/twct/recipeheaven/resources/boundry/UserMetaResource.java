package no.twct.recipeheaven.resources.boundry;

import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.resources.control.UserMetaService;
import no.twct.recipeheaven.user.entity.Group;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

@Path("user-info")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class UserMetaResource {

    @Inject
    UserMetaService userMetaService;


    /**
     * Registers a star to the item id provided
     *
     * @param id the item id liked
     * @return returns success/fail response
     */
    @POST
    @Path("star/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response starItem(@PathParam("id") BigInteger id) {
        if (userMetaService.starUserItem(id)){
            return Response.ok().build();
        }else {
            return Response.noContent().build();
        }
    }

    /**
     * Registers a star to the item id provided
     *
     * @param id the item id unliked
     * @return returns success/fail response
     */
    @POST
    @Path("unstar/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response unstarItem(@PathParam("id") BigInteger id) {
        if (userMetaService.unstarUserItem(id)){
            return Response.ok().build();
        }else {
            return Response.noContent().build();
        }
    }


    /**
     * Sets the current active meal for the user
     *
     * @param id the id of the meal
     * @return returns success/fail response
     */
    @POST
    @Path("current-menu/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response setCurrentMenu(@PathParam("id") BigInteger id) {
        if (userMetaService.setUserCurrentMenu(id)){
            return Response.ok().build();
        }else {
            return Response.noContent().build();
        }
    }


    /**
     * gets the current active menu for the user
     *
     * @return returns success/fail response
     */
    @GET
    @Path("current-menu")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getCurrentMenu() {
        Menu menu = userMetaService.getUserCurrentMenu();
        if (menu != null){
            return Response.ok().build();
        }else {
            return Response.noContent().build();
        }
    }

}
