package com.funda.library_system.service.impl;

import com.funda.library_system.dto.request.BookRequest;
import com.funda.library_system.dto.response.BookResponse;
import com.funda.library_system.dto.response.PublisherResponse;
import com.funda.library_system.entity.Author;
import com.funda.library_system.entity.Book;
import com.funda.library_system.entity.Publisher;
import com.funda.library_system.exception.ResourceNotFoundException;
import com.funda.library_system.repository.AuthorRepository;
import com.funda.library_system.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.funda.library_system.repository.BookRepository;
import com.funda.library_system.service.interfaces.BookService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(book -> {
            BookResponse res = new BookResponse();
            res.setId(book.getId());
            res.setTitle(book.getTitle());
            res.setIsbn(book.getISBN());
            res.setAuthorName(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
            res.setPublishYear(book.getPublishYear());
            res.setBorrowed(book.isBorrowed());

            if (book.getPublisher() != null) {
                res.setPublisher(book.getPublisher().getName());
            }
            if (book.isBorrowed() && book.getBorrowRecords() != null) {
                book.getBorrowRecords().stream()
                        .filter(record -> !record.isReturned())
                        .findFirst()
                        .ifPresent(activeRecord -> {
                            res.setCurrentUserId(activeRecord.getUser().getId());
                            res.setCurrentBorrowerName(activeRecord.getUser().getFirstName() + " " + activeRecord.getUser().getLastName());
                            res.setDueDate(activeRecord.getReturnDate());
                        });
            }

            return res;
        }).collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Kitap bulunamadı!"));

        return mapToResponse(book);
    }

    @Override
    public BookResponse createBook(BookRequest request) {

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Yazar bulunamadı"));
        Publisher publisher = null;
        if (request.getPublisherId() != null) {
            publisher = publisherRepository.findById(request.getPublisherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Yayınevi bulunamadı"));
        }
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setISBN(request.getISBN());
        book.setPublishYear(request.getPublishYear());
        book.setAuthor(author);
        book.setPublisher(publisher);

        Book savedBook = bookRepository.save(book);

        BookResponse response = new BookResponse();
        response.setId(savedBook.getId());
        response.setTitle(savedBook.getTitle());
        response.setIsbn(savedBook.getISBN());
        response.setPublishYear(savedBook.getPublishYear());

        response.setAuthorName(
                savedBook.getAuthor().getFirstName() + " " +
                        savedBook.getAuthor().getLastName()
        );
        if (savedBook.getPublisher() != null) {
            response.setPublisher(savedBook.getPublisher().getName());
        }

        return response;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse getBookByISBN(String isbn) {

        Book book = bookRepository.findByISBN(isbn)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Kitap bulunamadı!"));

        return mapToResponse(book);
    }

    @Override
    public List<BookResponse> getBooksByAuthorId(Long authorId) {

        return bookRepository.findByAuthorId(authorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Kitap bulunamadı"));

        existingBook.setTitle(request.getTitle());
        existingBook.setISBN(request.getISBN());
        existingBook.setPublishYear(request.getPublishYear());

        Book updatedBook = bookRepository.save(existingBook);

        BookResponse response = new BookResponse();

        response.setId(updatedBook.getId());
        response.setTitle(updatedBook.getTitle());
        response.setIsbn(updatedBook.getISBN());
        response.setPublishYear(updatedBook.getPublishYear());

        if (updatedBook.getAuthor() != null) {

            response.setAuthorName(
                    updatedBook.getAuthor().getFirstName()
                            + " " +
                            updatedBook.getAuthor().getLastName()
            );
        }

        return response;
    }

    private BookResponse mapToResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setIsbn(book.getISBN());
        response.setPublishYear(book.getPublishYear());
        if (book.getAuthor() != null) {
            response.setAuthorName(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
        }
        return response;
    }
}