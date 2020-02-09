package org.pl.dropwizard;

import org.jdbi.v3.core.Jdbi;
//import org.jdbi.v3.postgres.PostgresPlugin;

import java.util.Map;

public class JpaConfig {
    private static JpaConfig instance;
    private Map<String, String> properties = Map.of("url", "jdbc:postgresql://127.0.0.1:5432/test2",
            "username", "postgres",
            "password", "");
    static Jdbi jdbi;

    private JpaConfig(Map<String, String> properties) {
        jdbi = Jdbi.create(properties.get("url"), properties.get("username"), properties.get("password"));
//                .installPlugin(new PostgresPlugin());
    }

    public static JpaConfig getInstance() {
        if (instance == null) {
            instance = new JpaConfig();
        }
        return instance;
    }

    public static JpaConfig getInstance(Map<String, String> properties) {
        if (instance == null) {
            instance = new JpaConfig(properties);
        }
        return instance;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }

    public void addProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    private JpaConfig() {
        jdbi = Jdbi.create(properties.get("url"), properties.get("username"), properties.get("password"));
//                .installPlugin(new PostgresPlugin());
    }
}
