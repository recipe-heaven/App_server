package no.twct.recipeheaven.user.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

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