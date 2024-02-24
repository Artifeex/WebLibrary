package ru.sandr.web.library.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sandr.web.library.models.entities.Person;
import ru.sandr.web.library.models.mappers.PersonMapper;

import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "singleton")
public class PersonDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> getPeople() {
//        return jdbcTemplate.query("SELECT * FROM PERSON", new Object[]{}, new PersonMapper());
//    }
//
//    public Person getPerson(int id) {
//        return jdbcTemplate.query("SELECT * FROM PERSON WHERE person_id=?", new Object[]{id},
//                new PersonMapper()).stream().findAny().orElse(null);
//    }
//
//    public Person getPerson(String fullName) {
//        return jdbcTemplate.query("SELECT * FROM PERSON WHERE full_name=?", new Object[]{fullName},
//                new PersonMapper()).stream().findAny().orElse(null);
//    }
//
//    public void addNewPerson(Person person) {
//        jdbcTemplate.update("INSERT INTO PERSON(full_name, year_of_birth) VALUES(?, ?)", person.getFullName(), person.getYearOfBirth());
//    }
//
//    public void updatePerson(int id, Person person) {
//        jdbcTemplate.update("UPDATE PERSON SET full_name=?, year_of_birth=? where person_id=?",
//                person.getFullName(),
//                person.getYearOfBirth(),
//                id);
//    }
//
//    public void deletePerson(int id) {
//        jdbcTemplate.update("DELETE FROM PERSON WHERE person_id=?", id);
//    }



}
