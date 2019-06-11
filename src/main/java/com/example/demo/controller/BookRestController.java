package com.example.demo.controller;

import com.example.demo.dao.BookRepository;
import com.example.demo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/book")
public class BookRestController {
    public static final Logger logger = LoggerFactory.getLogger(BookRestController.class);

    public  BookRestController(){logger.info("BookRestController was created!");}

    @Autowired
    private BookRepository bookRepository;

    @PostMapping(produces = "application/json",consumes = "application/json")
    public ResponseEntity<Book> postBook(@RequestBody Book book, HttpServletRequest request){
        book = bookRepository.save(book);
        logger.info("Book with id={} was created!",book.getId());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(
            @PathVariable Long id,
            HttpServletRequest request){
        Book book = bookRepository.getOne(id);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(book);
    }

}
