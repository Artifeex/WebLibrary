package ru.sandr.web.library.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле ФИО не может быть пустым!")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "ФИО должно соответствовать формату: Фамилия, Имя, Отчество")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1900, message = "Дата рождения должна быть больше 1900 года")
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    //Решил использовать ленивую инициализацию, так как при получении всего списка людей не хотелось бы отдельно получать книги для каждого человека, когда
    //это на самом деле могло не понадобится
    @OneToMany(mappedBy = "bookOwner", fetch = FetchType.LAZY)
    private List<Book> personBooks;

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }


    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getPersonBooks() {
        return personBooks;
    }

    public void setPersonBooks(List<Book> personBooks) {
        this.personBooks = personBooks;
    }
}
