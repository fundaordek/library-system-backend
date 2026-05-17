package com.funda.library_system.service.interfaces;

import com.funda.library_system.dto.response.BorrowRecordResponse;
import com.funda.library_system.entity.BorrowRecord;
import java.util.List;


public interface BorrowRecordService {
    BorrowRecordResponse borrowBook(Long userId, Long bookId);
    BorrowRecordResponse returnBook(Long userId, Long bookId); // Sadece ID'lerle iade
    List<BorrowRecordResponse> getAllRecords();
}
