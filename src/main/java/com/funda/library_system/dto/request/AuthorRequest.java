package com.funda.library_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorRequest {

        @NotBlank(message = "Yazar adı boş olamaz")
        private String firstName;

        @NotBlank(message = "Yazar soyadı boş olamaz")
        private String lastName;
}
