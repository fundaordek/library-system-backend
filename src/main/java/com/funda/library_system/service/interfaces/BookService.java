package com.funda.library_system.service.interfaces;

import com.funda.library_system.dto.request.BookRequest;
import com.funda.library_system.dto.response.BookResponse;
import com.funda.library_system.entity.Book;

import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();
    BookResponse getBookById(Long id);
    BookResponse createBook(BookRequest request);
    BookResponse updateBook(Long id, BookRequest request);
    void deleteBook(Long id);
    BookResponse getBookByISBN(String isbn);
    List<BookResponse> getBooksByAuthorId(Long authorId);
}

