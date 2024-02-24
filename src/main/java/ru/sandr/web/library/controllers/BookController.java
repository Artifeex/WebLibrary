package ru.sandr.web.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sandr.web.library.models.dao.BookDAO;
import ru.sandr.web.library.models.dao.PersonDAO;
import ru.sandr.web.library.models.entities.Book;
import ru.sandr.web.library.models.entities.Person;
import ru.sandr.web.library.services.BooksService;
import ru.sandr.web.library.services.PeopleService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;

    private final PeopleService peopleService;

    private final int DEFAULT_BOOKS_PER_PAGE = 10;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String showAllBooks(Model model, @RequestParam(name = "sort_by_year") Optional<Boolean> isSortByYear,
                               @RequestParam(name = "page") Optional<Integer> page,
                               @RequestParam(name = "books_per_page") Optional<Integer> bookPerPage) {
        if (page.isPresent() && isSortByYear.isPresent()) {
            model.addAttribute("books", booksService.getPageBooksOrderedByYear(page.get(),
                    bookPerPage.orElse(DEFAULT_BOOKS_PER_PAGE)));
        } else if (isSortByYear.isPresent()) {
            model.addAttribute("books", booksService.getAllBooksOrderedByYear());
        } else if (page.isPresent()) {
            model.addAttribute("books", booksService.getPageBooks(page.get(),
                    bookPerPage.orElse(DEFAULT_BOOKS_PER_PAGE)));
        } else {
            model.addAttribute("books", booksService.getAllBooks());
        }
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
        if (bindingResult.hasErrors()) {
            return "books/add-new-book";
        }
        booksService.addNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("{id}")
    public String bookDetailPage(@PathVariable int id, Model model) {
        Book book = booksService.getBook(id);
        //Если поле null, то возвращается 0
        if (book.getBookOwner() != null) {
            model.addAttribute("person", book.getBookOwner());
        } else {
            model.addAttribute("person", new Person());
        }
        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.getAllPeople());
        return "books/book-detail";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable int id, Model model) {
        model.addAttribute("book", booksService.getBook(id));
        return "books/edit-book";
    }

    @PatchMapping("/{id}")
    public String editBook(@PathVariable int id, @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit-book";
        }
        booksService.updateBook(id, book);
        return "redirect:/books";
    }

    @PatchMapping("{id}/give")
    public String giveBook(@ModelAttribute("person") Person person, @PathVariable("id") int bookId) {
        booksService.giveBookToPerson(person.getId(), bookId);
        return "redirect:/books";
    }

    @PatchMapping("{id}/free")
    public String giveBook(@PathVariable("id") int bookId) {
        booksService.freeBook(bookId);
        return "redirect:/books/" + bookId;
    }

    @GetMapping("/search")
    public String searchBookPage(@RequestParam(name = "book_name", required = false) String bookName, Model model) {
        if (bookName == null) {
            model.addAttribute("isFirstVisit", true);
            return "books/search-book";
        }
        model.addAttribute("book", booksService.findBookByName(bookName));
        model.addAttribute("isFirstVisit", false);
        return "books/search-book";
    }
}
