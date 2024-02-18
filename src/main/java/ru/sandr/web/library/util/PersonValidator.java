package ru.sandr.web.library.util;

//Используется для проверки более сложных условий, например, уникальность имени в БД

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sandr.web.library.models.dao.PersonDAO;
import ru.sandr.web.library.models.entities.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //валидатор поддерживает проверки только для класса Person
    @Override
    public boolean supports(Class<?> aClass) {

        return Person.class.equals(aClass);
    }

    //как пример того, что можно валидировать
    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(personDAO.getPerson(person.getFullName()) != null) {
            errors.rejectValue("fullName", "", "Пользователь с таким ФИО уже существует!");
        }
    }
}
