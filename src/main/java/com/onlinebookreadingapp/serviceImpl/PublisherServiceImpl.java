package com.onlinebookreadingapp.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlinebookreadingapp.dto.OnlineBookReadingAppResponse;
import com.onlinebookreadingapp.entity.Publisher;
import com.onlinebookreadingapp.exception.OnlineBookReadingAppException;
import com.onlinebookreadingapp.repository.PublisherRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PublisherServiceImpl {

	private final PublisherRepository publisherRepository;

	public PublisherServiceImpl(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}

	public Publisher addPublisher(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	public Publisher getPublisherById(Long publisherId) {
		return publisherRepository.findById(publisherId).orElseThrow(() -> {
			log.error("Publisher does not exist with id " + publisherId);
			return new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder().status(HttpStatus.NOT_FOUND)
					.message("Publisher does not exist with id " + publisherId).build());
		});
	}

	public List<Publisher> getAllPublishers() {
		List<Publisher> publishers = publisherRepository.findAll();
		if (publishers.isEmpty()) {
			log.error("No publishers found");
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder().status(HttpStatus.NOT_FOUND)
					.message("No publishers found").build());
		}
		return publishers;
	}

	public Publisher updatePublisherById(Long publisherId, Publisher publisher) {
		return publisherRepository.findById(publisherId).map(existingPublisher -> {
			Arrays.stream(Publisher.class.getDeclaredFields()).filter(field -> !field.getName().equals("publisherId"))
					.forEach(field -> {
						field.setAccessible(true);
						try {
							Object value = field.get(publisher);
							if (value != null) {
								field.set(existingPublisher, value);
							}
						} catch (IllegalAccessException e) {
							log.error("Error updating publisher fields", e);
							throw new RuntimeException("Error updating publisher fields", e);
						}
					});

			return publisherRepository.save(existingPublisher);
		}).orElseThrow(() -> {
			log.error("Publisher does not exist with id " + publisherId);
			return new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder().status(HttpStatus.NOT_FOUND)
					.message("Publisher does not exist with id " + publisherId).build());
		});
	}

	public void deletePublisherById(Long publisherId) {
		if (!publisherRepository.existsById(publisherId)) {
			log.error("Publisher does not exist with id " + publisherId);
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder().status(HttpStatus.NOT_FOUND)
					.message("Publisher does not exist with id " + publisherId).build());
		}
		publisherRepository.deleteById(publisherId);
		log.info("Publisher with id " + publisherId + " has been deleted successfully!!");
	}
}
