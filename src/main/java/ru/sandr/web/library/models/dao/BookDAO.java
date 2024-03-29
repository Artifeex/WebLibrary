package ru.sandr.web.library.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.sandr.web.library.models.entities.Book;
import ru.sandr.web.library.models.mappers.BookMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Scope(value = "singleton")
public class BookDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> getAllBooks() {
//        return jdbcTemplate.query("SELECT * FROM BOOK", new Object[]{}, new BookMapper());
//    }
//
//    public void addNewBook(Book book) {
//        jdbcTemplate.update("INSERT INTO BOOK(name, author, year) VALUES (?, ?, ?)", book.getName(),
//                book.getAuthor(), book.getYear());
//    }
//
//    public Book getBook(int id) {
//        return jdbcTemplate.query("SELECT * FROM BOOK WHERE book_id=?", new Object[]{id}, new BookMapper())
//                .stream().findAny().orElse(null);
//    }
//
//    public void updateBook(int id, Book book) {
//        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? where book_id=?", book.getName(),
//                book.getAuthor(), book.getYear(), id);
//    }
//
//    public void giveBook(int personId, int bookId) {
//        jdbcTemplate.update("UPDATE BOOK set person_id=? where book_id=?", personId, bookId);
//    }
//
//    public void freeBook(int bookId) {
//        jdbcTemplate.update("UPDATE BOOK SET person_id=null where book_id=?", bookId);
//    }

}
