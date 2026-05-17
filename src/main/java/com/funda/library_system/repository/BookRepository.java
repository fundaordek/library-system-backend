package com.funda.library_system.repository;

import com.funda.library_system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByISBN(String ISBN);
    List<Book> findByAuthorId(Long authorId);
}
