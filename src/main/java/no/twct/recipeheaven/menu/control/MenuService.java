package no.twct.recipeheaven.menu.control;

import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;

@Transactional
public class MenuService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    AuthenticationService authenticationService;

    @Inject
    MenuEntityTransformer mealEntityTransformer;

    /**
     * Creates a new menu for the logged in user.
     *
     * @param menu the menu to create
     */
    public void createMenu(Menu menu) {
        menu.setCreator(authenticationService.getCurrentUser(jsonWebToken.getName()));
        entityManager.persist(menu);
    }

    /**
     * Returns a simple menu projection
     *
     * @param id id of the menu to get
     */
    public void getSimpleMenuDTO(BigInteger id) {
        // TODO
    }

    /**
     * Returns a full menu projection
     *
     * @param id id of the menu to get
     */
    public void getFullMenuDTO(BigInteger id) {
        // TODO
    }


}
