package no.twct.recipeheaven;

import javax.annotation.sql.DataSourceDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@DataSourceDefinition(
        name = "java:global/jdbc/DemoDataSource",
        className = "org.postgresql.ds.PGSimpleDataSource",
        serverName = "postgres_db",  // set the property
        portNumber = 5432,        // set the property
        databaseName = "recipe_heaven",    // set the property
        user = "recipe_heaven",
        password = "recipe_heaven")
@ApplicationPath("/api")
public class App extends Application {}
