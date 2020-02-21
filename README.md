# Dropwizard rest + JDBI3

Build application:
````
gradle clean build runShadow
````
Run application:
````
java -jar build/libs/dropwizard-sample.jar server confg.yml
````

### Run with Docker
Build project:
````
gradle clean build runShadow
````
And next run docker compose:
````
docker-compose up
````
If your image alredy exist, you must remove or rebuild images. 
To rebuild this image you must use `docker-compose build` or `docker-compose up --build`.

Delete Docker containers and image:
````
docker rm psql_wizard && docker rm wizard-rest && docker image rm wizard-rest-app
````

### Build With
* [Dropwizard](http://www.dropwizard.io/) - The web framework used
* [JDBI](http://jdbi.org/) - Jdbi provides convenient, idiomatic access to relational data in Java.
* [Gradle](https://gradle.org/) - Dependency Management
* [Flyway](https://flywaydb.org/) - Version control for database
* [JUnit5](https://junit.org/junit5/) - The new major version of the programmer-friendly testing framework for Java
