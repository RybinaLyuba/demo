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
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/book")
public class BookRestController {
    public static final Logger logger = LoggerFactory.getLogger(BookRestController.class);

    public  BookRestController(){logger.info("BookRestController was created!");}

    @Autowired
    private BookRepository bookRepository;

    @PostMapping(produces = "application/json",consumes = "application/json")
    public ResponseEntity<Book> postBook(@RequestBody Book book){
        book = bookRepository.save(book);
        logger.info("Book with id={} was created!",book.getId());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(book);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getBook(
            @PathVariable Long id){
        Book book = bookRepository.getOne(id);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(book);
    }

    @PutMapping(value = "/{id}",produces = "application/json",consumes = "application/json")
    public  ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        return bookRepository.findById(id)
                .map(record->{
                    record.setName(book.getName());
                    Book updatedBook = bookRepository.save(record);
                    logger.info("Book with id={} was updated!",id);
                    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(updatedBook);})
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(value = "/{id}",produces = "application/json",consumes = "application/json")
    public ResponseEntity<Long> deleteBook(
            @PathVariable Long id) {
        return bookRepository.findById(id)
                .map(record->{
                    bookRepository.delete(record);
                    logger.info("Book with id={} was deleted!",id);
                    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(id);})
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Book>> getAll(){
        List<Book> listBook = bookRepository.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(listBook);
    }

}
