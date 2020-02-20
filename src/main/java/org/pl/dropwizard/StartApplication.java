package org.pl.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.pl.dropwizard.config.JdbiConfiguration;
import org.pl.dropwizard.dao.BrandDao;
import org.pl.dropwizard.dao.CarDao;
import org.pl.dropwizard.dao.ModelDao;
import org.pl.dropwizard.resource.BrandService;
import org.pl.dropwizard.resource.CarService;
import org.pl.dropwizard.resource.ModelService;

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
    public void run(final JdbiConfiguration config, final Environment environment) {
        // Datasource configuration
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
        final Client client = new JerseyClientBuilder(environment).using(config.getJerseyClientConfiguration())
                .build(getName());
        environment.jersey().register(client);

        // Register resources brand
        BrandDao brandDao = jdbi.onDemand(BrandDao.class);
        BrandService brandService = new BrandService(brandDao);
        environment.jersey().register(brandService);
        // Register resources model
        ModelDao modelDao = new ModelDao(jdbi);
        ModelService modelService = new ModelService(modelDao, brandDao);
        environment.jersey().register(modelService);
        // Register resources car
        CarService carService = new CarService(jdbi.onDemand(CarDao.class), modelDao);
        environment.jersey().register(carService);

        migrateDb(config.getDataSourceFactory().getUrl(), config.getDataSourceFactory().getUser(), config.getDataSourceFactory().getPassword());
    }

    private void migrateDb(final String url, final String username, final String password) {
        final Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .load();
        flyway.migrate();
    }
}
