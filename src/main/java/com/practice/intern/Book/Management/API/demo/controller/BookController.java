package com.practice.intern.Book.Management.API.demo.controller;

import com.practice.intern.Book.Management.API.demo.model.Book;
import com.practice.intern.Book.Management.API.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book saveBook= service.createBook(book);
        return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBook(){
        List<Book> book = service.getBook();
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book= service.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id,@RequestBody Book updateData){
        Book updatebook = service.updateBookById(id, updateData);
        return ResponseEntity.ok(updatebook);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteBookById(@PathVariable Long id){
         String message = service.deleteBookById(id);
         return ResponseEntity.ok(message);
    }
}