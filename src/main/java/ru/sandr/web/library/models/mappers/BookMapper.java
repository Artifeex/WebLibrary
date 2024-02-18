package ru.sandr.web.library.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sandr.web.library.models.entities.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Book(resultSet.getInt("book_id"),
                resultSet.getString("name"),
                resultSet.getString("author"),
                resultSet.getInt("year"),
                resultSet.getInt("person_id"));
    }
}
