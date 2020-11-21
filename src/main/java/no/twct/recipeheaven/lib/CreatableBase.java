package no.twct.recipeheaven.lib;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.user.entity.User;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * Base entity class for
 * entities that has a creator (a user)
 * and and ID which are linked (one to one).
 */
@Data
@NoArgsConstructor
//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CreatableBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "is_public")
    boolean isPublic;


    @OneToOne
    @JoinColumn(name = "creator", referencedColumnName = "id", nullable = false)
    User creator;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
}
