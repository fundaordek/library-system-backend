package com.funda.library_system.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

        @Email(message = "Geçerli email gir")
        private String email;

        @NotBlank(message = "Şifre boş olamaz")
        private String password;
}
