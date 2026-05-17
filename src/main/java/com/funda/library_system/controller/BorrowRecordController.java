package com.funda.library_system.controller;

import com.funda.library_system.dto.request.BookRequest;
import com.funda.library_system.dto.request.BorrowRecordRequest;
import com.funda.library_system.dto.response.BorrowRecordResponse;
import com.funda.library_system.entity.BorrowRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.funda.library_system.service.interfaces.BorrowRecordService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/borrowing")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;
    @PostMapping("/borrows")
    public ResponseEntity<BorrowRecordResponse> borrowBook(
            @RequestParam Long userId,
            @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowRecordService.borrowBook(userId, bookId));
    }

    @PutMapping("/return")
    public ResponseEntity<BorrowRecordResponse> returnBook(
            @RequestParam Long userId,
            @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowRecordService.returnBook(userId, bookId));
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecordResponse>> getAll() {
        return ResponseEntity.ok(borrowRecordService.getAllRecords());
    }

}
