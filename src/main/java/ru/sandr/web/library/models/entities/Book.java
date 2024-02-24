package ru.sandr.web.library.models.entities;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "author")
    private String author;

    @Min(value = 1)
    @Column(name = "year")
    private int year;

    @Column(name = "rental_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date rentalTime;

    @ManyToOne(fetch = FetchType.EAGER) //хоть по умолчанию так же, но решил явно показать
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person bookOwner;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getBookOwner() {
        return bookOwner;
    }

    public void setBookOwner(Person bookOwner) {
        this.bookOwner = bookOwner;
    }

    public Date getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Date rentalTime) {
        this.rentalTime = rentalTime;
    }

    public boolean isOverdue() {
        if(rentalTime != null) {
            Date cur = new Date();
            GregorianCalendar currentDate = new GregorianCalendar(cur.getYear(), cur.getMonth() ,cur.getDate());
            GregorianCalendar rentalDate = new GregorianCalendar(rentalTime.getYear(), rentalTime.getMonth() ,rentalTime.getDate());
            rentalDate.add(Calendar.DAY_OF_MONTH, 10);
            return currentDate.after(rentalDate);
        }
        return false;
    }


}
