package org.pl.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.pl.dropwizard.config.JdbiConfiguration;
import org.pl.dropwizard.dao.AddressRepo;
import org.pl.dropwizard.dao.BookDao;
import org.pl.dropwizard.dao.CarDao;
import org.pl.dropwizard.dao.UserDao;
import org.pl.dropwizard.resource.AddressService;
import org.pl.dropwizard.resource.BookService;
import org.pl.dropwizard.resource.CarService;
import org.pl.dropwizard.resource.UserResource;

import javax.ws.rs.client.Client;

public class StartApplication extends Application<JdbiConfiguration> {
    public static void main(String[] args) throws Exception {
        new StartApplication().run(args);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void run(JdbiConfiguration config, Environment environment) {
        // Datasource configuration
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
        final Client client = new JerseyClientBuilder(environment).using(config.getJerseyClientConfiguration())
                .build(getName());
        environment.jersey().register(client);
        // Register resources address
        AddressRepo addressRepo = new AddressRepo(jdbi);
        AddressService component = new AddressService(addressRepo);
        environment.jersey().register(component);
//        environment.jersey().register(new AddressResource(component));
        // Register resources user
        UserResource userResource = new UserResource(jdbi.onDemand(UserDao.class));
        environment.jersey().register(userResource);
        // Register resources book
        BookService bookService = new BookService(new BookDao(jdbi));
        environment.jersey().register(bookService);
        // Register resources car
        CarService carService = new CarService(new CarDao(jdbi));
        environment.jersey().register(carService);

        migrateDb(config.getDataSourceFactory().getUrl(), config.getDataSourceFactory().getUser(), config.getDataSourceFactory().getPassword());
    }

    private void migrateDb(String url, String username, String password) {
        Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .load();
        flyway.migrate();
    }
}
