package com.funda.library_system.service.impl;

import com.funda.library_system.dto.request.AuthorRequest;
import com.funda.library_system.dto.response.AuthorResponse;
import com.funda.library_system.entity.Author;
import com.funda.library_system.exception.ResourceNotFoundException;
import com.funda.library_system.repository.AuthorRepository;
import com.funda.library_system.service.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> modelMapper.map(author, AuthorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Yazar bulunamadı! ID: " + id));
        return modelMapper.map(author, AuthorResponse.class);
    }

    @Override
    public AuthorResponse createAuthor(AuthorRequest request) {
        Author author = modelMapper.map(request, Author.class);
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorResponse.class);
    }

    @Override
    public AuthorResponse updateAuthor(Long id, AuthorRequest request) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Güncellenecek yazar bulunamadı!"));

        existingAuthor.setFirstName(request.getFirstName());
        existingAuthor.setLastName(request.getLastName());

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return modelMapper.map(updatedAuthor, AuthorResponse.class);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

}
