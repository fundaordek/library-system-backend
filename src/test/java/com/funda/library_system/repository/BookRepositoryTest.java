package com.funda.library_system.repository;

import com.funda.library_system.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveBook() {

        Book book = new Book();
        book.setTitle("1984");

        Book saved = bookRepository.save(book);

        assertNotNull(saved.getId());
    }
}
