package no.twct.recipeheaven.resources.control;

import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.menu.control.MenuEntityTransformer;
import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.menu.entity.MenuDTO;
import no.twct.recipeheaven.resources.entity.UserMetaInfo;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import no.twct.recipeheaven.user.entity.User;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

public class UserMetaService {

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    AuthenticationService authenticationService;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    MenuEntityTransformer menuEntityTransformer;

    public boolean starUserItem(BigInteger id){
        CreatableBase creatableBase = entityManager.find(CreatableBase.class, id);
        User         user         = authenticationService.getLoggedInUser();
        UserMetaInfo userMetaInfo = UserMetaInfo.getByUserId(entityManager, user.getId());

        if (creatableBase != null){
            userMetaInfo.getStaredEntities().add(creatableBase);
            entityManager.merge(userMetaInfo);
            return  true;
        }
        return false;
    }

    public boolean unstarUserItem(BigInteger id){
        CreatableBase creatableBase = entityManager.find(CreatableBase.class, id);
        User user = authenticationService.getLoggedInUser();
        UserMetaInfo userMetaInfo = UserMetaInfo.getByUserId(entityManager, user.getId());

        if (creatableBase != null){
            if (userMetaInfo.getStaredEntities().contains(creatableBase)){
                userMetaInfo.getStaredEntities().remove(creatableBase);
                entityManager.merge(userMetaInfo);
                return  true;
            }
        }
        return false;
    }

    /**
     * Returns the active menu for the current user if no active menu is set null is returned
     * @return the users active menu null rnif no is found
     */
    public MenuDTO getUserCurrentMenu(){

        User user = authenticationService.getLoggedInUser();
        UserMetaInfo userMetaInfo = UserMetaInfo.getByUserId(entityManager, user.getId());
        return menuEntityTransformer.createFullMenuDTO(userMetaInfo.getCurrentMenu());

    }

    public boolean setUserCurrentMenu(BigInteger id){
        Menu menu = entityManager.find(Menu.class, id);
        if (menu != null){
            User user = authenticationService.getLoggedInUser();
            UserMetaInfo userMetaInfo = UserMetaInfo.getByUserId(entityManager, user.getId());
            userMetaInfo.setCurrentMenu(menu);
            entityManager.merge(userMetaInfo);
            return true;

        }

        return false;
    }

}
