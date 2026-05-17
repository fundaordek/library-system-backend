package com.funda.library_system.service.impl;

import com.funda.library_system.dto.response.BorrowRecordResponse;
import com.funda.library_system.entity.Book;
import com.funda.library_system.entity.BorrowRecord;
import com.funda.library_system.entity.User;
import com.funda.library_system.exception.BadRequestException;
import com.funda.library_system.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.funda.library_system.repository.BookRepository;
import com.funda.library_system.repository.BorrowRecordRepository;
import com.funda.library_system.repository.UserRepository;
import com.funda.library_system.service.interfaces.BorrowRecordService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowRecordServiceImpl implements BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BorrowRecordResponse borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        if (book.isBorrowed()) throw new BadRequestException("Kitap zaten birinde!");

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setReturnDate(LocalDate.now().plusDays(15));
        record.setReturned(false);

        book.setBorrowed(true);
        bookRepository.save(book);

        return mapToResponse(borrowRecordRepository.save(record));
    }

    @Override
    @Transactional
    public BorrowRecordResponse returnBook(Long userId, Long bookId) {
        BorrowRecord record = borrowRecordRepository.findByUserIdAndBookIdAndIsReturnedFalse(userId, bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu kullanıcıya ait iade edilecek aktif bir kayıt bulunamadı!"));

        record.setReturnDate(LocalDate.now());
        record.setReturned(true);

        Book book = record.getBook();
        book.setBorrowed(false);
        bookRepository.save(book);

        BorrowRecord saved = borrowRecordRepository.save(record);
        return mapToResponse(saved);
    }

    @Override
    public List<BorrowRecordResponse> getAllRecords() {
        return borrowRecordRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BorrowRecordResponse mapToResponse(BorrowRecord record) {
        BorrowRecordResponse res = new BorrowRecordResponse();
        res.setId(record.getId());
        res.setUserId(record.getUser().getId());
        res.setBookId(record.getBook().getId());
        res.setUserName(record.getUser().getFirstName() + " " + record.getUser().getLastName());
        res.setBookTitle(record.getBook().getTitle());
        res.setBorrowDate(record.getBorrowDate());
        res.setReturnDate(record.getReturnDate());
        res.setReturned(record.isReturned());
        return res;
    }

}
