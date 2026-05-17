package com.funda.library_system.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

        @NotBlank(message = "İsim boş olamaz")
        private String firstName;

        @NotBlank(message = "Soyisim boş olamaz")
        private String lastName;

        @Email(message = "Geçerli email gir")
        @NotBlank(message = "Email boş olamaz")
        private String email;

        @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
        private String password;
}
