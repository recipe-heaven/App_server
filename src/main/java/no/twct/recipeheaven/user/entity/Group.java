package no.twct.recipeheaven.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
	Represents a user group in the application. A user group is
	used for permissions, where each group has different permissions when 
	accessing resources. 
*/
@Data
@Entity
@Table(name = "Groups")
public class Group {

    public static final String USER_GROUP_NAME = "user";
    public static final String ADMIN_GROUP_NAME = "admin";
    public static final String[] GROUPS = {USER_GROUP_NAME, ADMIN_GROUP_NAME};

    @Id
    String name;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

}