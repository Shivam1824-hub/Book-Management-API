package com.practice.intern.Book.Management.API.demo.service;

import com.practice.intern.Book.Management.API.demo.model.Book;
import com.practice.intern.Book.Management.API.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository){
        this.repository = repository;
    }

    public Book createBook(Book book){
        return repository.save(book);
    }
}
