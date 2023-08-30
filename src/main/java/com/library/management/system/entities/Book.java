package com.library.management.system.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="book")
public class Book {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long book_id;
    //ISBN, title, author, publication date, status (available, borrowed, in maintenance), and genre.

    @NotNull
    @Column(name="isbn")
    private String isbn;

    @NotNull
    @Column(name="title")

    private String title;

    @NotNull
    @Column(name="author")
    private String author;

    @NotNull
    @Column(name="publication_date")
    private Date publication_date;

    @NotNull
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private BookStatus status;


    @Column(name="genre")
    private String genre;

    public Book(){ }

}
