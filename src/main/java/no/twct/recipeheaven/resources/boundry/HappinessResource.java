package no.twct.recipeheaven.resources.boundry;

import no.twct.recipeheaven.lib.Resource;
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
public class HappinessResource extends Resource {

    @Inject
    HappinessService happinessService;

    /**
     * Adds a favorite tag to an item with given id.
     * If the staring is successful returns ok response.
     * If not, returns an error message.
     * On any other error a 500 server error is returned.
     *
     * @param id the id of the item to favorite
     * @return returns response
     */
    @POST
    @Path("star/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response favoriteItem(@PathParam("id") BigInteger id) {
        try {
            if (!happinessService.starUserItem(id)) {
                createErrorResponse("Failed to star item with id " + id);
            }
        } catch (Exception e) {
            serverError();
        }
        return buildResponse();
    }


}
