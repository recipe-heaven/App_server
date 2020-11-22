package no.twct.recipeheaven.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/* 
Represents a User in the system.
A user has a An ID, email, first name, last name and password.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@NamedQuery(name = User.USER_BY_EMAIL, query = "SELECT e FROM User e WHERE e.email = :email")

public class User implements Serializable {

    public static final String USER_BY_EMAIL = "User.getByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 6)
    @JsonbTransient
    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JsonbTransient
    @JoinTable(name = "user_groups", joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"), inverseJoinColumns = @JoinColumn(name = "groups_name", referencedColumnName = "name"))
    List<Group> groups;



    public User(String email, String name, String username, String password) {
        this.setEmail(email);
        this.setName(name);
        this.setUsername(username);
        this.setPassword(password);
    }

    public List<Group> getGroups() {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        return groups;
    }

}