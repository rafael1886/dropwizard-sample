package org.pl.dropwizard.resource;

import org.eclipse.jetty.http.HttpStatus;
import org.pl.dropwizard.dao.BookDao;
import org.pl.dropwizard.model.Book;

import javax.ws.rs.WebApplicationException;
import java.util.List;

public class BookService implements BookResource {
    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Representation<Book> findById(Long id) {
        return new Representation<>(HttpStatus.OK_200,
                bookDao.findById(id).orElseThrow(
                        () -> new WebApplicationException("Book not found", HttpStatus.NOT_FOUND_404)
                ));
    }

    public List<Book> findAll() {
        return bookDao.findAll();
    }
}
