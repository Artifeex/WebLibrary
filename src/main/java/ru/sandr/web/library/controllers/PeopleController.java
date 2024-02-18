package ru.sandr.web.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sandr.web.library.models.dao.BookDAO;
import ru.sandr.web.library.models.dao.PersonDAO;
import ru.sandr.web.library.models.entities.Person;
import ru.sandr.web.library.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    private final PersonValidator validator;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator validator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.validator = validator;
    }


    @GetMapping()
    public String showAllPeople(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/people-list";
    }

    @GetMapping("/{personId}")
    public String getPerson(@PathVariable int personId, Model model) {
        model.addAttribute("person", personDAO.getPerson(personId));
        model.addAttribute("books", bookDAO.getPersonBooks(personId));
        return "people/person-detail";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/add-new-person";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        validator.validate(person, bindingResult);

        if(bindingResult.hasErrors()) {
            return "people/add-new-person";
        }
        personDAO.addNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String editPersonPage(@PathVariable int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/edit-person";
    }

    //ModelAttribute из формы создает новый объект person, даже если мы ранее на страницу передали объект person
    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person, @PathVariable int id,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "people/edit-person";
        }

        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

}
