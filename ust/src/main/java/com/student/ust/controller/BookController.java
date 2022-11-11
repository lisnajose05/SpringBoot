package com.student.ust.controller;

import com.student.ust.entity.Book;
import com.student.ust.entity.Student;
import com.student.ust.service.BookService;
import com.student.ust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> get(@PathVariable Integer id) {
        log.debug("passed in ID is >>>>"+id);

        try {
            Book book = bookService.getBookByID(id);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/book")
    public void add(@RequestBody Book book) {
        bookService.saveBook(book);


    }


    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable Integer id){
        bookService.deleteBook1(id);
    }


    @PutMapping("/book")
    public ResponseEntity<Book> put(@RequestBody Book book)  {
        try {
            Book updatedbook = bookService.updateBook(book);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }



    }



}

