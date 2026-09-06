package com.practice.intern.Book.Management.API.demo.repository;

import com.practice.intern.Book.Management.API.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public  List<Book> findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCase(String titleKeyword,String authorKeyword);
}
