package com.onlinebookreadingapp.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlinebookreadingapp.dto.OnlineBookReadingAppResponse;
import com.onlinebookreadingapp.entity.Book;
import com.onlinebookreadingapp.entity.Publisher;
import com.onlinebookreadingapp.exception.OnlineBookReadingAppException;
import com.onlinebookreadingapp.repository.PublisherRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PublisherServiceImpl {

	private final PublisherRepository publisherRepository;

	private final BookServiceImpl bookServiceImpl;

	public PublisherServiceImpl(PublisherRepository publisherRepository, BookServiceImpl bookServiceImpl) {
		this.publisherRepository = publisherRepository;
		this.bookServiceImpl = bookServiceImpl;
	}

	public Publisher addPublisher(Publisher publisher) {
		if (publisher.getPublisherBooks() != null) {
			List<Book> savedBooks = publisher.getPublisherBooks().stream().map(bookServiceImpl::addBook)
					.collect(Collectors.toList());
			publisher.setPublisherBooks(savedBooks);
		}
		return publisherRepository.save(publisher);
	}

	public Publisher getPublisherByPublisherId(Long publisherId) {
		return publisherRepository.findById(publisherId).orElseThrow(() -> {
			log.error("Publisher does not exist with id " + publisherId);
			return new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder().status(HttpStatus.NOT_FOUND)
					.message("Publisher does not exist with id " + publisherId).build());
		});
	}

	public Publisher getPublisherByPublisherName(String publisherName) {
		Optional<Publisher> publisherFound = publisherRepository.findPublisherByPublisherNameIgnoreCase(publisherName);

		if (publisherFound.isEmpty()) {
			log.error("Publisher does not exists with publisherName " + publisherName);
			throw new OnlineBookReadingAppException(
					OnlineBookReadingAppResponse.builder().status(HttpStatus.BAD_REQUEST)
							.message("Publisher does not exists with publisherName " + publisherName).build());
		}
		return publisherFound.get();

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

	public Publisher updatePublisherByPublisherId(Long publisherId, Publisher publisher) {
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

	public Publisher deletePublisherByPublisherId(Long publisherId) {
		Optional<Publisher> publisher = publisherRepository.findById(publisherId);
		if (publisher.isEmpty()) {
			log.error("Publisher does not exist with id " + publisherId);
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder().status(HttpStatus.NOT_FOUND)
					.message("Publisher does not exist with id " + publisherId).build());
		}
		publisherRepository.deleteById(publisherId);
		log.info("Publisher with id " + publisherId + " has been deleted successfully!!");
		return publisher.get();
	}

	public Publisher deletePublisherByPublisherName(String publisherName) {
		Optional<Publisher> publisher = publisherRepository.findPublisherByPublisherNameIgnoreCase(publisherName);
		if (publisher.isEmpty()) {
			log.error("Publisher does not exist with Name " + publisherName);
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder().status(HttpStatus.NOT_FOUND)
					.message("Publisher does not exist with name " + publisherName).build());
		}
		publisherRepository.deleteById(publisher.get().getPublisherId());
		log.info("Publisher with name " + publisherName + " has been deleted successfully!!");
		return publisher.get();
	}
}
