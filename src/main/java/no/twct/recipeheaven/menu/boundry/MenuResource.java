package no.twct.recipeheaven.menu.boundry;

import no.twct.recipeheaven.menu.control.MenuService;
import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.response.ErrorResponse;
import no.twct.recipeheaven.response.errors.ViolationErrorMessageBuilder;
import no.twct.recipeheaven.user.entity.Group;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

/**
 * Resource path for everything related to menu(s)
 */
@Path("menu")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {

    @Inject
    MenuService menuService;

    /**
     * Creates a new menu for the logged in user and returns a 200 response.
     * If the validation fails, a response is returned with error messages for each violation.
     * On any other error a 500 server error is returned.
     * The route is protected.
     *
     * @param menu the new menu
     * @return returns response
     */
    @POST
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response createMenu(Menu menu) {
        Response.ResponseBuilder response;
        try {
            menuService.createMenu(menu);
            return Response.ok().build();
        } catch (
                ConstraintViolationException e) {
            var violations = new ViolationErrorMessageBuilder(e.getConstraintViolations()).getMessages();
            response = Response.ok(new ErrorResponse(violations));
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
    }

    /**
     * Updates a menu with new details for the logged in user and returns a 200 response if successful.
     * If the validation fails, a response is returned with error messages for each violation.
     * On any other error a 500 server error is returned.
     * The route is protected.
     *
     * @param menu the updated menu
     * @return returns response
     */
    @PATCH
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response updateMenu(Menu menu) {
        Response.ResponseBuilder response;
        try {
            menuService.updateMenu(menu);
            response = Response.ok();
        } catch (
                ConstraintViolationException e) {
            var violations = new ViolationErrorMessageBuilder(e.getConstraintViolations()).getMessages();
            response = Response.ok(new ErrorResponse(violations));
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
    }


    /**
     * Returns a single simple menu by its id if it is found.
     * If no menu is found returns 404.
     * On any other error a 500 server error is returned.
     *
     * @param id the id of the menu to get
     * @return returns response
     */
    @GET
    @Path("simple/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMenuSimple(@PathParam("id") BigInteger id) {
        Response.ResponseBuilder response;
        try {
            var menuDTO = menuService.getSimpleMenuDTO(id);
            if (menuDTO != null) {
                response = Response.ok(new DataResponse(menuDTO));
            } else {
                response = Response.ok(new ErrorResponse("Can't find a menu with id " + id)).status(Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            response = Response.serverError();
        }
        return response.build();
    }


    /**
     * Returns a single full menu by its id if it is found.
     * If no menu is found returns 404.
     * On any other error a 500 server error is returned.
     *
     * @param id the id of the menu to get
     * @return returns response
     */
    @GET
    @Path("full/{id}")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getMenuFull(@PathParam("id") BigInteger id) {
        return Response.ok(new DataResponse("TO BE IMPLEMENTED")).build();
    }
}
