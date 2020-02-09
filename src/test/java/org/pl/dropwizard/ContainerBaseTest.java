package org.pl.dropwizard;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class ContainerBaseTest {
    private static PostgreSQLContainer postgreSQLContainer;

    static{
        mysqlContainerCongigurationAndStart();
//        migrateDb();

        Map<String, String> properties = Map.of("url", postgreSQLContainer.getJdbcUrl(),
                "username", postgreSQLContainer.getUsername(),
                "password", postgreSQLContainer.getPassword());
        JpaConfig.getInstance(properties);
    }

    private static void mysqlContainerCongigurationAndStart() {
        postgreSQLContainer = new PostgreSQLContainer("postgres:11.6");
        postgreSQLContainer.withInitScript("db/migration/V1.0.0__script.sql");
        postgreSQLContainer.withEnv("TZ", "Europe/Warsaw");
        postgreSQLContainer.addParameter("useUnicode", "yes");
        postgreSQLContainer.addParameter("characterEncoding", "UTF-8");
        postgreSQLContainer.start();
    }
    private static void migrateDb() {
        Flyway flyway = Flyway.configure()
                .dataSource(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword())
                .load();
        flyway.migrate();
    }
}
