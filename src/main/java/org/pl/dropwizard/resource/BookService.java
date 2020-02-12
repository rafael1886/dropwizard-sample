package org.pl.dropwizard.resource;

import org.eclipse.jetty.http.HttpStatus;
import org.pl.dropwizard.dao.BookDao;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

public class BookService implements BookResource {
    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Response findById(Long id) {
        return ok(bookDao.findById(id).orElseThrow(
                () -> new WebApplicationException("Book not found", HttpStatus.NOT_FOUND_404)
        )).build();
    }

    public Response findAll() {
        return bookDao.findAll().isEmpty() ? Response.status(Response.Status.NOT_FOUND).build() : ok(bookDao.findAll()).build();
    }
}
