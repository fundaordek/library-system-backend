package com.funda.library_system.dto.request;

import lombok.Data;

@Data
public class BorrowRecordRequest {
    private Long userId;
    private Long bookId;
}
