package org.pl.dropwizard.model;

import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.mapper.RowMapperFactory;

import java.lang.reflect.Type;
import java.util.Optional;

public class UserMapperFactory implements RowMapperFactory {
    @Override
    public Optional<RowMapper<?>> build(Type type, ConfigRegistry config) {
        User user = new User();

        return Optional.empty();
    }
}
