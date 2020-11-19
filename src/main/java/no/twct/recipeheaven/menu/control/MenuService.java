package no.twct.recipeheaven.menu.control;

import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.menu.entity.MenuSimpleDTO;
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
    MenuEntityTransformer menuEntityTransformer;

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
     * Updates a menu in the database with the provided menu.
     *
     * @param menu the menu to create
     */
    public void updateMenu(Menu menu) {
//        entityManager.merge(menu);
    }

    /**
     * Returns a simple menu projection
     *
     * @param id id of the menu to get
     */
    public MenuSimpleDTO getSimpleMenuDTO(BigInteger id) {
        var menu = entityManager.find(Menu.class, id);
        if (menu != null) {
            return menuEntityTransformer.createSimpleMenuDTO(menu);
        }
        return null;
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
