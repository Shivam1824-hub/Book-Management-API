package com.practice.intern.Book.Management.API.demo.service;

import com.practice.intern.Book.Management.API.demo.model.Book;
import com.practice.intern.Book.Management.API.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository){
        this.repository = repository;
    }

    public Book createBook(Book book){
        return repository.save(book);
    }

    public List<Book> getBook(){
        return repository.findAll();
    }

    public Book getBookById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public Book updateBookById(Long id, Book updatedData){
        Book book = repository.findById(id).orElseThrow();
        book.setAuthor(updatedData.getAuthor());
        book.setPrice(updatedData.getPrice());
        book.setTitle(updatedData.getTitle());

        return repository.save(book);
    }

    public  String deleteBookById(Long id){
         repository.findById(id).orElseThrow();
         repository.deleteById(id);
         return "ID has been deleted";
    }
}
