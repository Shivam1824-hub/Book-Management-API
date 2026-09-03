package com.practice.intern.Book.Management.API.demo.controller;

import com.practice.intern.Book.Management.API.demo.model.Book;
import com.practice.intern.Book.Management.API.demo.service.BookService;
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
    public Book addBook(@RequestBody Book book){
        return service.createBook(book);
    }

    @GetMapping
    public List<Book> getBook(){
        return service.getBook();
    }

    @GetMapping("/{id}")
    public Book getBookByid(@PathVariable Long id){
        return service.getBookByid(id);
    }
}
