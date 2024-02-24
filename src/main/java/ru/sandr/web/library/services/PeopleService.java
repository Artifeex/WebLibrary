package ru.sandr.web.library.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sandr.web.library.models.entities.Person;
import ru.sandr.web.library.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getAllPeople() {
        return peopleRepository.findAll();
    }

    public Person getPerson(int id, boolean isGetPersonBook) {
        Person person = peopleRepository.findById(id).orElse(new Person());
        if(isGetPersonBook)
            Hibernate.initialize(person.getPersonBooks()); //т.к. lazy type для fetch
        return person;
    }

    @Transactional
    public void addNewPerson(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void updatePerson(int id, Person person) {
        person.setId(id);
        //save работает так, что, если передается сущность с уже существующим в БД id, то значение для нее обновляются
        peopleRepository.save(person);
    }

    @Transactional
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }
}
