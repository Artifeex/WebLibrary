package ru.sandr.web.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sandr.web.library.models.dao.BookDAO;
import ru.sandr.web.library.models.dao.PersonDAO;
import ru.sandr.web.library.models.entities.Person;
import ru.sandr.web.library.services.PeopleService;
import ru.sandr.web.library.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    private final PersonValidator validator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator validator) {
        this.peopleService = peopleService;
        this.validator = validator;
    }


    @GetMapping()
    public String showAllPeople(Model model) {
        model.addAttribute("people", peopleService.getAllPeople());
        return "people/people-list";
    }

    @GetMapping("/{personId}")
    public String getPerson(@PathVariable int personId, Model model) {
        Person person = peopleService.getPerson(personId, true);
        model.addAttribute("person", person);
        model.addAttribute("books", person.getPersonBooks());
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
        peopleService.addNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String editPersonPage(@PathVariable int id, Model model) {
        model.addAttribute("person", peopleService.getPerson(id, false));
        return "people/edit-person";
    }

    //ModelAttribute из формы создает новый объект person, даже если мы ранее на страницу передали объект person
    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person, @PathVariable int id,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "people/edit-person";
        }
        peopleService.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleService.deletePerson(id);
        return "redirect:/people";
    }

}
