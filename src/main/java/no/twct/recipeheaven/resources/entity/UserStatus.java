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
@NamedQuery(name = "UserStatus.GetByOwnerId", query = "select s from UserStatus s where s.owner.id = :id")
public class UserStatus implements Serializable {
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

    public UserStatus(User owner) {
        this.owner = owner;
        this.id = owner.getId();
    }

    public static UserStatus getByUserId(EntityManager entityManager, BigInteger userId) {
        Query query = entityManager.createNamedQuery("UserStatus.GetByOwnerId", UserStatus.class).setParameter("id", userId);
        return (UserStatus) query.getSingleResult();
    }
}
