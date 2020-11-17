package no.twct.recipeheaven.recipe.control;

import no.twct.recipeheaven.meal.entity.FullMealDTO;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.meal.entity.SimpleMealDTO;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;
import no.twct.recipeheaven.resources.entity.Image;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import no.twct.recipeheaven.user.entity.User;
import no.twct.recipeheaven.util.FileUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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
import java.math.BigInteger;
import java.net.URLConnection;
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

    @Inject
    RecipeEntityTransformer recipeEntityTransformer;

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
    AuthenticationService authenticationService;


    private User getCurrentUser() {
        return authenticationService.getCurrentUser(securityContext.getUserPrincipal().getName());
    }


    public void createRecipe(Recipe recipe,
                               FormDataMultiPart photos
    ) {
        ArrayList<Image> formPhotos = new ArrayList<>();
        try {
            List<FormDataBodyPart> images = photos.getFields("image");
            if (images != null) {
                for (FormDataBodyPart part : images) {
                    InputStream        inputStream = part.getEntityAs(InputStream.class);
                    ContentDisposition meta        = part.getFormDataContentDisposition();

                    String saveName = UUID.randomUUID().toString() + FileUtils.getFilePathExtension(meta.getFileName());
                    long   size     = Files.copy(inputStream, Paths.get(imageStoragePath, saveName));

                    Image photo = new Image();
                    photo.setName(saveName);
                    photo.setSize(size);
                    photo.setMimeType(URLConnection.guessContentTypeFromName(meta.getFileName()));

                    formPhotos.add(photo);

                    entityManager.persist(photo);
                    System.out.println("cc");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        recipe.setRecipeImage(formPhotos.get(0));
        recipe.setCreator(getCurrentUser());

        entityManager.persist(recipe);
    }



    public RecipeDTO getSimpleRecipeDTO(BigInteger id) {
        return recipeEntityTransformer.createSimpleRecipeDTO(entityManager.find(Recipe.class, id));
    }

    public FullRecipeDTO getFullRecipeDTO(BigInteger id) {
        return recipeEntityTransformer.createFullRecipeDTO(entityManager.find(Recipe.class, id));
    }
}
