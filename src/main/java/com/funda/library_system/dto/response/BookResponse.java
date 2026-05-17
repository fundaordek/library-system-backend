package com.funda.library_system.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String isbn;
    private String authorName;
    private int publishYear;
    private boolean isBorrowed;
    private Long currentUserId;
    private String currentBorrowerName;
    private LocalDate dueDate;
    private String publisher;
}
