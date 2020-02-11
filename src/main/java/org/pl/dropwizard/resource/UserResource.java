package org.pl.dropwizard.resource;

import org.checkerframework.checker.units.qual.Time;
import org.eclipse.jetty.http.HttpStatus;
import org.pl.dropwizard.dao.UserDao;
import org.pl.dropwizard.model.Address;
import org.pl.dropwizard.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<User> list = new ArrayList<>();
        Map<User, Address> map = userDao.findAllWithAddress();
        for (Map.Entry<User, Address> userAddressEntry : map.entrySet()) {
            User user = userAddressEntry.getKey();
            user.setAddress(userAddressEntry.getValue());
            list.add(user);
        }
        return new Representation<>(HttpStatus.OK_200, list);
    }
}
