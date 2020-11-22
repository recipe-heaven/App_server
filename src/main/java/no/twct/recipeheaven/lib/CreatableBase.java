package no.twct.recipeheaven.lib;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.user.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * Base entity class for
 * entities that has a creator (a user)
 * and and ID which are linked (one to one).
 */
@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class CreatableBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "is_public")
    boolean isPublic;

    @NotNull
    @OneToOne
    @JoinColumn(name = "creator", referencedColumnName = "id", nullable = false)
    User creator;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
}
