package com.funda.library_system.service.interfaces;

import com.funda.library_system.dto.request.AuthorRequest;
import com.funda.library_system.dto.response.AuthorResponse;
import com.funda.library_system.entity.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorResponse> getAllAuthors();
    AuthorResponse getAuthorById(Long id);
    AuthorResponse createAuthor(AuthorRequest request);
    AuthorResponse updateAuthor(Long id, AuthorRequest request);
    void deleteAuthor(Long id);
}
