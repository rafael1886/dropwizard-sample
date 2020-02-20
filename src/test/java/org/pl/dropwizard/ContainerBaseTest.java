package org.pl.dropwizard;

import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.testcontainers.containers.PostgreSQLContainer;


public class ContainerBaseTest {
    private static PostgreSQLContainer postgreSQLContainer;
    protected static Jdbi jdbi;

    static {
        mysqlContainerCongigurationAndStart();
        migrateDb();
        jdbi = Jdbi.create(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword());
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    private static void mysqlContainerCongigurationAndStart() {
        postgreSQLContainer = new PostgreSQLContainer("postgres:12.1-alpine");
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
