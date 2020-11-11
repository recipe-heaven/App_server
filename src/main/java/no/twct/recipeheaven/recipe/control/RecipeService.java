package no.twct.recipeheaven.recipe.control;

import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.resources.entity.Image;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import no.twct.recipeheaven.user.entity.User;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
public class RecipeService {

    @Inject
    @ConfigProperty(name = "photo.storage.path", defaultValue = "images/items")
    String imageStoragePath;

    private final File imageDir = new File(imageStoragePath);

    @Inject
    IdentityStoreHandler identityStoreHandler;

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer", defaultValue = "issuer")
    String issuer;

    @Context
    SecurityContext securityContext;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    JsonWebToken token;

    @Inject
    AuthenticationService authenticationService;


    private User getCurrentUser(){
        return entityManager.find(User.class, securityContext.getUserPrincipal().getName());
    }

    public void createRecipe(Recipe recipe,
                             FormDataMultiPart photos) {
        User             user = this.getCurrentUser();
        ArrayList<Image> p    = new ArrayList<>();


        try{


            List<FormDataBodyPart> images = photos.getFields("image");


            if(images != null) {


                for (FormDataBodyPart part : images) {
                    InputStream        is   = part.getEntityAs(InputStream.class);
                    ContentDisposition meta = part.getContentDisposition();

                    String uid = UUID.randomUUID().toString();
                    Files.copy(is, Paths.get(imageDir.toString(), uid));

                    Image photo = new Image();
                    photo.setName(meta.getFileName());
                    photo.setSize(meta.getSize());
                    photo.setMimeType(meta.getType());
                    photo.setOwner(recipe);

                    p.add(photo);

                    entityManager.persist(photo);
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }
        recipe.setRecipeImage(p.get(0));
        entityManager.persist(recipe);

        recipe.setCreator(getCurrentUser());
        entityManager.persist(recipe);
    }

    public Recipe getRecipe(int recipeId){
        return entityManager.find(Recipe.class, recipeId);
    }

}
