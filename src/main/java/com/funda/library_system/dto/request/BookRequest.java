package com.funda.library_system.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequest {

        @NotBlank(message = "Kitap adı boş olamaz")
        private String title;

        @NotBlank(message = "ISBN boş olamaz")
        private String ISBN;

        @NotNull(message = "Yazar ID boş olamaz")
        private Long authorId;

        @Min(value = 1500, message = "Yayın yılı geçersiz")
        private int publishYear;

        @Min(value = 1, message = "Kitap adedi en az 1 olmalı")
        private int quantity;

        private Long publisherId;
    }
