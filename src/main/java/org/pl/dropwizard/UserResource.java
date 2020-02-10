package org.pl.dropwizard;

import org.checkerframework.checker.units.qual.Time;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDao userDao;

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Time
    public Representation<List<User>> findAll() {
        return new Representation<>(HttpStatus.OK_200, userDao.findAll());
    }

    @GET
    @Time
    @Path("{id}")
    public Representation<User> findById(@PathParam("id") final Long id) {
        return new Representation<>(HttpStatus.OK_200,
                userDao.findById(id).orElseThrow(
                        () -> new WebApplicationException("User not found", HttpStatus.NOT_FOUND_404)));
    }

    @GET
    @Time
    @Path("/all")
    public Representation<List<User>> findAllWithAddress() {
        return new Representation<>(HttpStatus.OK_200, userDao.findAllWithAddress());
    }
}
