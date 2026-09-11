package com.practice.intern.Book.Management.API.demo.service;

import com.practice.intern.Book.Management.API.demo.DTO.BookRequestDto;
import com.practice.intern.Book.Management.API.demo.DTO.BookResponseDto;
import com.practice.intern.Book.Management.API.demo.DTO.BookSearchRequestDto;
import com.practice.intern.Book.Management.API.demo.exception.BookNotFoundException;
import com.practice.intern.Book.Management.API.demo.model.Book;
import com.practice.intern.Book.Management.API.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class BookService {
    private final BookRepository repository;


    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookResponseDto createBook(BookRequestDto requestDto){
        Book book = new Book();
        book.setTitle(requestDto.getTitle());
        book.setPrice(requestDto.getPrice());
        book.setAuthor(requestDto.getAuthor());

        Book savedBook = repository.save(book);
//        BookResponseDto responseDto = new BookResponseDto(savedBook.getId(),savedBook.getTitle(), savedBook.getAuthor(),savedBook.getPrice());
//        responseDto.setId(savedBook.getId());
//        responseDto.setTitle(savedBook.getTitle());
//        responseDto.setAuthor(savedBook.getAuthor());
//        responseDto.setPrice(savedBook.getPrice());
        return new BookResponseDto(savedBook.getId(),savedBook.getTitle(), savedBook.getAuthor(),savedBook.getPrice());
    }

    public List<BookResponseDto> getAllBooks(){
        List<Book> books= repository.findAll();
       return books.stream()
                .map(book ->new BookResponseDto(
                        book.getId(),book.getTitle(), book.getAuthor(),book.getPrice())).toList();
    }

    public BookResponseDto getBookById(Long id){
        Book book = repository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found with id: " + id));
        return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthor(),book.getPrice());
    }

    public BookResponseDto updateBookById(Long id, BookRequestDto updatedData){
        Book book = repository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found with id: " + id));
        book.setTitle(updatedData.getTitle());
        book.setAuthor(updatedData.getAuthor());
        book.setPrice(updatedData.getPrice());

        Book savedBook = repository.save(book);
        return new BookResponseDto(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor(), savedBook.getPrice());
    }

    public String deleteBookById(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        } repository.deleteById(id);
        return "ID has been deleted";
    }

//    public List<Book> findByTitleOrAuthor(String title,String author){
//        return repository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCase(title,author);
//    }
//    public Page<Book> getBooksSortedByPrice(int page, int size, String sortBy, String direction){
//        Sort sorted = "desc".equalsIgnoreCase(direction) ? Sort.by(sortBy).ascending() :Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sorted);
//        return repository.findAll(pageable);
//    }
//    public List<Book> filterBooks(BigDecimal minPrice, BigDecimal maxPrice){
//        Specification<Book> spec = (root, query, cb) -> cb.conjunction();
//        if(minPrice != null){
//            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice));}
//        if (maxPrice != null){
//            spec = spec.and(((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice) ));}
//        return repository.findAll(spec);
//    }

//    public Page<Book> getBooks(String search,BigDecimal minPrice,BigDecimal maxPrice,int page,int size,String sortBy,String direction){
//        Sort sort = "desc".equalsIgnoreCase(direction) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();// it is using inline ternary operator
//        Pageable pageable = PageRequest.of(page,size,sort);
//        Specification<Book> spec = ((root, query, cb) -> cb.conjunction());
//
//        if(search!=null && !search.trim().isEmpty()){
//            String matchPattern = "%"+search.trim().toLowerCase()+"%";
//            spec =spec.and((root, query, cb) ->cb.or(
//                    cb.like(cb.lower(root.get("title")),matchPattern),
//                    cb.like(cb.lower(root.get("author")),matchPattern)) );
//        }
//        if(minPrice !=null){
//            spec =spec.and((root, query, cb) ->cb.greaterThanOrEqualTo(root.get("price"),minPrice));
//        }
//        if(maxPrice!=null){
//            spec = spec.and((root, query, cb) ->cb.lessThanOrEqualTo(root.get("price"),maxPrice));
//        }
//        return repository.findAll(spec,pageable);
//    }

    public Page<BookResponseDto> getBooks(BookSearchRequestDto searchRequestDto){

    }

}
