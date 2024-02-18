package ru.sandr.web.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sandr.web.library.models.dao.BookDAO;
import ru.sandr.web.library.models.dao.PersonDAO;
import ru.sandr.web.library.models.entities.Book;
import ru.sandr.web.library.models.entities.Person;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/book-list";
    }

    @GetMapping("/new")
    public String newBookPage(Model model) {
        //или можно сделать через ModelAttribute,но сделано так, чтобы явно показать, что происходит при аннотации ModelAttribute
        model.addAttribute("book", new Book());

        return "books/add-new-book";
    }

    @PostMapping()
    public String addNewBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/add-new-book";
        }
        bookDAO.addNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("{id}")
    public String bookDetailPage(@PathVariable int id, Model model) {
        Book book = bookDAO.getBook(id);
        //Если поле null, то возвращается 0
        if(book.getOwnerId() != 0) {
            model.addAttribute("person", personDAO.getPerson(book.getOwnerId()));
        } else {
            model.addAttribute("person", new Person());
        }
        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.getPeople());
        return "books/book-detail";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDAO.getBook(id));
        return "books/edit-book";
    }

    @PatchMapping("/{id}")
    public String editBook(@PathVariable int id, @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/edit-book";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @PatchMapping("{id}/give")
    public String giveBook(@ModelAttribute("person") Person person, @PathVariable("id") int bookId) {
        bookDAO.giveBook(person.getId(), bookId);
        return "redirect:/books";
    }

    @PatchMapping("{id}/free")
    public String giveBook(@PathVariable("id") int bookId) {
        bookDAO.freeBook(bookId);
        return "redirect:/books/" + bookId;
    }

}
