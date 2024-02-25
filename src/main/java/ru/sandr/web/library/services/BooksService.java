package ru.sandr.web.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sandr.web.library.models.entities.Book;
import ru.sandr.web.library.models.entities.Person;
import ru.sandr.web.library.repositories.BooksRepository;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    private final PeopleService peopleService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Book getBook(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addNewBook(Book book) {
        booksRepository.save(book);
    }

    public Person getOwnerBook(int id) {
        Book book = booksRepository.findById(id).orElse(new Book());
        return book.getBookOwner();
    }

    @Transactional
    public void updateBook(int id, Book book) {
        Book bookToBeUpdated = booksRepository.findById(id).get();
        book.setId(id);
        book.setBookOwner(bookToBeUpdated.getBookOwner());
        booksRepository.save(book);
    }

    @Transactional
    public void giveBookToPerson(int personId, int bookId) {
        Book book = booksRepository.findById(bookId).orElse(new Book());
        book.setRentalTime(new Date());
        //обновится в БД ? Да, обновится, так как book будет находиться в persistent context
        book.setBookOwner(peopleService.getPerson(personId, false));
    }

    @Transactional
    public void freeBook(int bookId) {
        Book book = booksRepository.findById(bookId).orElse(new Book());
        book.setRentalTime(null);
        //обновится в БД? Да, обновится, так как book будет находиться в persistent context
        book.setBookOwner(null);
    }

    public List<Book> getAllBooksOrderedByYear() {
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> getPageBooks(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> getPageBooksOrderedByYear(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public Book findBookByName(String bookName) {
       return booksRepository.findBookByNameStartingWith(bookName).stream().findAny().orElse(null);
    }

}
