package com.practice.intern.Book.Management.API.demo.controller;

import com.practice.intern.Book.Management.API.demo.model.Book;
import com.practice.intern.Book.Management.API.demo.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> book = service.getAllBooks() ;
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book= service.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updatedBookById(@PathVariable Long id,@RequestBody Book updateData){
        Book updatebook = service.updateBookById(id, updateData);
        return ResponseEntity.ok(updatebook);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletedBookById(@PathVariable Long id){
         String message = service.deleteBookById(id);
         return ResponseEntity.ok(message);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Book>> findByTitleOrAuthor(
//            @RequestParam(value = "title") String title,
//            @RequestParam(value = "author") String author){
//        List<Book> findBook = service.findByTitleOrAuthor(title, author);
//        return  ResponseEntity.ok(findBook);
//    }

//    @GetMapping("/")
//    public ResponseEntity<Page<Book>> getSortedBook(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "price") String sortBy,
//            @RequestParam(defaultValue = "asc") String direction){
////        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
//        Page<Book> sortedbooks = service.getBooksSortedByPrice(page, size, sortBy,direction);
//        return  ResponseEntity.ok(sortedbooks);
//    }
//
//    @GetMapping("/filter")
//    public ResponseEntity<List<Book>> filter(
//            @RequestParam(required = false) BigDecimal minPrice,
//            @RequestParam(required = false) BigDecimal maxPrice){
//        return ResponseEntity.ok(service.filterBooks(minPrice,maxPrice));
//    }

    @GetMapping("/")
    public ResponseEntity<Page<Book>> getBooks(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction){
        Page<Book> sortedBooks = service.getBooks(search,minPrice, maxPrice, page, size, sortBy, direction);
        return ResponseEntity.ok(sortedBooks);
    }


}