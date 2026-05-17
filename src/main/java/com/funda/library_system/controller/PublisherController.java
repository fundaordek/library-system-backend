package com.funda.library_system.controller;

import com.funda.library_system.dto.request.PublisherRequest;
import com.funda.library_system.dto.response.PublisherResponse;
import com.funda.library_system.entity.Publisher;
import com.funda.library_system.service.interfaces.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/publishers")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PublisherController {

private final PublisherService publisherService;
    @GetMapping
    public ResponseEntity<List<PublisherResponse>> getAllPublishers() {
        List<PublisherResponse> dtoList = publisherService.getAllPublishers()
                .stream()
                .map(publisher -> {
                    PublisherResponse res = new PublisherResponse();
                    res.setId(publisher.getId());
                    res.setName(publisher.getName());
                    return res;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> createPublisher(@RequestBody PublisherRequest request) {
        Publisher entity = new Publisher();
        entity.setName(request.getName());

        Publisher savedEntity = publisherService.savePublisher(entity);

        PublisherResponse response = new PublisherResponse();
        response.setId(savedEntity.getId());
        response.setName(savedEntity.getName());

        return ResponseEntity.ok(response);
    }
    }

