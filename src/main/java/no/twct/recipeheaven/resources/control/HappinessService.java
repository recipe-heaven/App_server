package no.twct.recipeheaven.resources.control;

import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.resources.entity.UserStatus;
import no.twct.recipeheaven.search.entity.SearchDAO;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import no.twct.recipeheaven.user.entity.User;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

public class HappinessService {

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    AuthenticationService authenticationService;



    @PersistenceContext
    EntityManager entityManager;


    public boolean starUserItem(BigInteger id){
        CreatableBase creatableBase = entityManager.find(CreatableBase.class, id);
        User user = authenticationService.getCurrentUser(jsonWebToken.getName());
        UserStatus userStatus = UserStatus.getByUserId(entityManager, user.getId());

        if (creatableBase != null){
            userStatus.getStaredEntities().add(creatableBase);
            entityManager.merge(userStatus);
            return  true;
        }
        return false;
    }

    public boolean unstarUserItem(BigInteger id){
        CreatableBase creatableBase = entityManager.find(CreatableBase.class, id);
        User user = authenticationService.getCurrentUser(jsonWebToken.getName());
        UserStatus userStatus = UserStatus.getByUserId(entityManager, user.getId());

        if (creatableBase != null){
            if (userStatus.getStaredEntities().contains(creatableBase)){
                userStatus.getStaredEntities().remove(creatableBase);
                entityManager.merge(userStatus);
                return  true;
            }
        }
        return false;
    }


}
