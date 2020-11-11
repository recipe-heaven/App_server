package no.twct.recipeheaven.recipe.boundry;


import com.ibm.websphere.jaxrs20.multipart.IAttachment;
import com.ibm.websphere.jaxrs20.multipart.IMultipartBody;
import no.twct.recipeheaven.recipe.control.RecipeService;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.resources.entity.Image;
import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.response.ErrorResponse;
import no.twct.recipeheaven.response.errors.ErrorMessage;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import no.twct.recipeheaven.user.entity.Group;
import no.twct.recipeheaven.user.entity.User;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.activation.DataHandler;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


@Path("recipe")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {

    @Inject
    RecipeService recipeService;



    /**
     * Creates a new recipe for the logged in user.
     * The route is protected
     *
     * @return returns success/fail response
     */
    @POST
    @Path("get")
    @RolesAllowed({Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME})
    public Response getRecipe(int recipeId) {
        recipeService.getRecipe(recipeId);
        return Response.ok().build();
    }




    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA )
    @RolesAllowed({ Group.USER_GROUP_NAME, Group.ADMIN_GROUP_NAME })
    public Response createRecipe(
            //@FormDataParam("recipe")String recipe,
            FormDataMultiPart photos
    ) {
        System.out.println(photos.toString());
        //recipeService.createRecipe(recipe, photos);

        return Response.ok().build();


    }




}
