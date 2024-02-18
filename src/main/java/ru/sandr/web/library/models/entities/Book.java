package ru.sandr.web.library.models.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {

    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String author;

    @Min(value = 1)
    private int year;


    private int ownerId;

    public Book(int id, String name, String author, int year, int ownerId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
}
