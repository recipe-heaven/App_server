package no.twct.recipeheaven.resources.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.user.entity.User;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "UserStatus")
@NamedQuery(name = "UserStatus.GetByOwnerId", query = "select s from UserMetaInfo s where s.owner.id = :id")
public class UserMetaInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @ManyToOne
    private Menu currentMenu;

    @OneToOne
    @JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<@Valid CreatableBase> staredEntities;

    public UserMetaInfo(User owner) {
        this.owner = owner;
        this.id = owner.getId();
    }

    public static UserMetaInfo getByUserId(EntityManager entityManager, BigInteger userId){
        Query query = entityManager.createNamedQuery("UserStatus.GetByOwnerId", UserMetaInfo.class).setParameter("id", userId );
        return (UserMetaInfo) query.getSingleResult();
    }
}
