package com.practice.intern.Book.Management.API.demo.service;

import com.practice.intern.Book.Management.API.demo.exception.BookNotFoundException;
import com.practice.intern.Book.Management.API.demo.model.Book;
import com.practice.intern.Book.Management.API.demo.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<Book> getBooks(){
        return repository.findAll();
    }

    public Book getBookById(Long id){
        return repository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found with id: " + id));
    }

    public Book updateBookById(Long id, Book updatedData){
        Book book = repository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found with id: " + id));
        book.setAuthor(updatedData.getAuthor());
        book.setPrice(updatedData.getPrice());
        book.setTitle(updatedData.getTitle());

        return repository.save(book);
    }

    public String deleteBookById(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        } repository.deleteById(id);
        return "ID has been deleted";
    }

    public List<Book> findByTitleOrAuthor(String title,String author){
        return repository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCase(title,author);
    }

    public Page<Book> getBooksSortedByPrice(int page, int size, String sortBy, String direction){
        Sort sorted = Sort.by(sortBy).ascending();
        if("desc".equalsIgnoreCase(direction)){
            sorted =Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(page, size, sorted);
        return repository.findAll(pageable);
    }
}
