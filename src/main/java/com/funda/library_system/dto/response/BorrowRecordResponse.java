package com.funda.library_system.dto.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BorrowRecordResponse {
    private Long id;
    private Long userId;
    private String userName;
    private Long bookId;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;
}
