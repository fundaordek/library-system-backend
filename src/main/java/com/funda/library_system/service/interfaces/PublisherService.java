package com.funda.library_system.service.interfaces;

import com.funda.library_system.entity.Publisher;
import java.util.List;

public interface PublisherService {

    List<Publisher> getAllPublishers();
    Publisher savePublisher(Publisher publisher);
}
