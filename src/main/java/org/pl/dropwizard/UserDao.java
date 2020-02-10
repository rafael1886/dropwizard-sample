package org.pl.dropwizard;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    @SqlQuery("select * from users where id = :id")
    @RegisterBeanMapper(User.class)
    Optional<User> findById(@Bind("id") Long id);

    @SqlQuery("select * from users")
    @RegisterBeanMapper(User.class)
    List<User> findAll();

    @SqlQuery("select * from users u inner join address a on a.id = u.address_id")
    @RegisterBeanMapper(value = User.class, prefix = "u")
    @RegisterBeanMapper(value = Address.class, prefix = "a")
    List<User> findAllWithAddress();
}
