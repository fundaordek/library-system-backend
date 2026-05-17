package com.funda.library_system.repository;

import com.funda.library_system.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {

    Optional<BorrowRecord> findByUserIdAndBookIdAndIsReturnedFalse(Long userId, Long bookId);
}
