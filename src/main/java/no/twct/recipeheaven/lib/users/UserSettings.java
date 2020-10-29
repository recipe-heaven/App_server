package no.twct.recipeheaven.lib.users;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Holds the user settings.
 */
@Entity
@Table(name = "user_settings")
public class UserSettings implements Serializable {

	@Id
	private BigInteger id;

	@Column(nullable = false)
	private String settings;

	@ManyToMany
	@JsonbTransient
	@JoinTable(name = "user_groups", joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"), inverseJoinColumns = @JoinColumn(name = "name", referencedColumnName = "name"))
	List<Group> groups;

	public UserSettings() {
	}

	public UserSettings(String email, String firstName, String lastName, String password) {

	}

	public String getFirstName() {
		return "";
	}


}