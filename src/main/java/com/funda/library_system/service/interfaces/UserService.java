package com.funda.library_system.service.interfaces;


import com.funda.library_system.dto.request.LoginRequest;
import com.funda.library_system.dto.request.UserRequest;
import com.funda.library_system.dto.response.AuthResponse;
import com.funda.library_system.dto.response.UserResponse;
import com.funda.library_system.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse saveUser(UserRequest request);
    UserResponse updateUser(Long id, UserRequest request);
    AuthResponse login(LoginRequest request);
    void deleteUser(Long id);
}
