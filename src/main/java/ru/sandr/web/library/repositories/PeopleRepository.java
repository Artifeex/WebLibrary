package ru.sandr.web.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sandr.web.library.models.entities.Person;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
