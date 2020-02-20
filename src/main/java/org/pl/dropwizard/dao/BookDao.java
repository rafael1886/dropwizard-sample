package org.pl.dropwizard.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import org.pl.dropwizard.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class BookDao {
    private static final Logger log = LoggerFactory.getLogger(BookDao.class);
    private final Jdbi jdbi;

    public BookDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Optional<Book> findById(Long id) {
        log.info("Find book by id " + id);
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Book.class));
            return handle.createQuery("select * from books where id = :id")
                    .bind("id", id)
                    .mapTo(Book.class)
                    .findOne();
        });
    }

    public List<Book> findAll() {
        log.info("Find all books");
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(BeanMapper.factory(Book.class, "b"));
//            handle.registerRowMapper(BeanMapper.factory(User.class, "u"));
            return handle.createQuery("select * from books ")//b left join users u on u.id = b.user_id
                    .mapTo(Book.class)
                    .list();
        });
    }
}
