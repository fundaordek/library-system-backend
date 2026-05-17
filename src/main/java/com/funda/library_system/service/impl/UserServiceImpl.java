package com.funda.library_system.service.impl;

import com.funda.library_system.dto.request.LoginRequest;
import com.funda.library_system.dto.request.UserRequest;
import com.funda.library_system.dto.response.AuthResponse;
import com.funda.library_system.dto.response.UserResponse;
import com.funda.library_system.entity.User;
import com.funda.library_system.exception.BadRequestException;
import com.funda.library_system.exception.ResourceNotFoundException;
import com.funda.library_system.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.funda.library_system.repository.UserRepository;
import com.funda.library_system.service.interfaces.UserService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı!"));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse saveUser(UserRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException(
                    "Bu email zaten kayıtlı"
            );
        }
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Güncellenecek kullanıcı bulunamadı!"));

        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserResponse.class);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("E-posta veya şifre hatalı!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("E-posta veya şifre hatalı!");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                "Giriş başarılı!",
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
