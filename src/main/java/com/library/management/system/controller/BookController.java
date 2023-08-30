package com.library.management.system.controller;


import com.library.management.system.dao.BookRepository;
import com.library.management.system.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/save")
    //this @Valid annotation will throw invalid exception that is defined in dto package errormessage which is custom
    //taken form the exception package where we create GlobalExceptionHandler which throw custom exception message
    public Book savingBookDetail( @Valid @RequestBody Book book){
        return bookRepository.save(book);
    }

}
