package no.twct.recipeheaven.resources.boundry;

import net.coobird.thumbnailator.Thumbnails;
import no.twct.recipeheaven.resources.entity.Image;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("resource")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResourcesResource {

    @Inject
    @ConfigProperty(name = "photo.storage.path", defaultValue = "images/items")
    String imageStoragePath;

    @Inject
    IdentityStoreHandler identityStoreHandler;

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer", defaultValue = "issuer")
    String issuer;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    JsonWebToken token;

    @Inject
    AuthenticationService authenticationService;

    /**
     * Returns the image with the given id. If the width parameter is set, it will
     * scale the image width/height to the specified witdth.
     *
     * @param id    id of the image
     * @param width desired return width
     * @return image or 404
     */
    @GET
    @Path("image/{id}")
    @Produces(MediaType.WILDCARD)
    public Response getPhoto(@PathParam("id") int id, @QueryParam("width") int width) {
        Image imageObject = entityManager.find(Image.class, BigInteger.valueOf(id));
        if (imageObject != null) {
            StreamingOutput result = (OutputStream outputStream) -> {
                java.nio.file.Path image = Paths.get(imageStoragePath, imageObject.getName());
                if (width == 0) {
                    Files.copy(image, outputStream);
                    outputStream.flush();
                } else {
                    Thumbnails.of(image.toFile())
                              .size(width, width)
                              .outputFormat(imageObject.getMimeType())
                              .toOutputStream(outputStream);
                }
            };

            // Ask the browser to cache the image for 24 hours
            CacheControl cc = new CacheControl();
            cc.setMaxAge(86400);
            cc.setPrivate(true);

            return Response.ok(result).cacheControl(cc).type(imageObject.getMimeType()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    }
