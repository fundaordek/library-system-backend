package com.funda.library_system.service;

import com.funda.library_system.dto.request.LoginRequest;
import com.funda.library_system.dto.response.AuthResponse;
import com.funda.library_system.entity.User;
import com.funda.library_system.repository.UserRepository;
import com.funda.library_system.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void login_Success_Test() {
        //(Given)
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("funda@test.com");
        loginRequest.setPassword("12345");

        User mockUser = new User();
        mockUser.setEmail("funda@test.com");
        mockUser.setPassword("encoded_password");

        // Mock davranışlarını tanımlıyoruz
        when(userRepository.findByEmail("funda@test.com")).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("12345", "encoded_password")).thenReturn(true);

        AuthResponse response = userService.login(loginRequest);

        //DOĞRULAMA (Then)
        assertNotNull(response);
        assertEquals("Giriş başarılı!", response.getMessage());
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void login_WrongPassword_ThrowsException() {
        // Hatalı şifre senaryosu
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("funda@test.com");
        loginRequest.setPassword("yanlis_sifre");

        User mockUser = new User();
        mockUser.setPassword("encoded_password");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("yanlis_sifre", "encoded_password")).thenReturn(false);

        // Hata fırlatmasını bekliyoruz
        assertThrows(RuntimeException.class, () -> userService.login(loginRequest));
    }
}