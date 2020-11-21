package no.twct.recipeheaven.resources.boundry;

import no.twct.recipeheaven.resources.control.HappinessService;
import no.twct.recipeheaven.user.entity.Group;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

@Path("stats")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class HappinessResource {

    @Inject
    HappinessService happinessService;


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
        if (happinessService.starUserItem(id)){
            return Response.ok().build();
        }else {
            return Response.noContent().build();
        }
    }



}
