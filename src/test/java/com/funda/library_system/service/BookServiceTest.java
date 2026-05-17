package com.funda.library_system.service;

import com.funda.library_system.dto.request.BookRequest;
import com.funda.library_system.dto.response.BookResponse;
import com.funda.library_system.entity.Author;
import com.funda.library_system.entity.Book;
import com.funda.library_system.repository.AuthorRepository;
import com.funda.library_system.repository.BookRepository;
import com.funda.library_system.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void shouldCreateBookSuccessfully() {

        // given
        BookRequest request = new BookRequest();
        request.setTitle("1984");
        request.setISBN("12345");
        request.setAuthorId(1L);
        request.setPublishYear(1949);

        Author author = new Author();
        author.setId(1L);
        author.setFirstName("George");
        author.setLastName("Orwell");

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle("1984");
        savedBook.setISBN("12345");
        savedBook.setPublishYear(1949);
        savedBook.setAuthor(author);

        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(author));

        when(bookRepository.save(any(Book.class)))
                .thenReturn(savedBook);

        // when
        BookResponse response = bookService.createBook(request);

        // then
        assertNotNull(response);
        assertEquals("1984", response.getTitle());
        assertEquals("George Orwell", response.getAuthorName());

        verify(authorRepository).findById(1L);
        verify(bookRepository).save(any(Book.class));
    }
}


