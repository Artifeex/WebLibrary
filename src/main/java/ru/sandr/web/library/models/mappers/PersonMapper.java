package ru.sandr.web.library.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sandr.web.library.models.entities.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Person(resultSet.getInt("person_id"),
                resultSet.getString("full_name"),
                resultSet.getInt("year_of_birth"));
    }
}
