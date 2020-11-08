package no.twct.recipeheaven.lib.models.createable;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.users.User;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Base entity class for
 * entities that has a creator (a user)
 * and and ID which are linked (one to one).
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class CreatableBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @OneToOne
    @JoinColumn(name = "creator", referencedColumnName = "id", nullable = false)
    User creator;
}
