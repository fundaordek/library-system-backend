package com.funda.library_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String ISBN;
    private int quantity;
    private int publishYear;
    private boolean isBorrowed=false;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties("books")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<BorrowRecord> borrowRecords;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

}
