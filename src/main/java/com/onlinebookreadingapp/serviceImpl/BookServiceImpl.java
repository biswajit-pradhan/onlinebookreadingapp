package com.onlinebookreadingapp.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlinebookreadingapp.dto.OnlineBookReadingAppResponse;
import com.onlinebookreadingapp.entity.Book;
import com.onlinebookreadingapp.exception.OnlineBookReadingAppException;
import com.onlinebookreadingapp.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book addBook(Book book) {
		Optional<Book> bookFound = bookRepository.findBookByBookNameIgnoreCase(book.getBookName());

		if (bookFound.isPresent()) {
			log.error("Book Already exists with name " + book.getBookName());
			throw new OnlineBookReadingAppException(
					OnlineBookReadingAppResponse.builder().status(HttpStatus.BAD_REQUEST)
							.message("Book Already exists with name " + book.getBookName()).build());
		}

		return bookRepository.save(book);

	}

	public Book getBookByBookId(Long bookId) {
		Optional<Book> bookFound = bookRepository.findById(bookId);

		if (bookFound.isEmpty()) {
			log.error("Book does not exists with bookId " + bookId);
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder()
					.status(HttpStatus.BAD_REQUEST).message("Book does not exists with bookId " + bookId).build());
		}

		return bookFound.get();

	}

	public Book getBookByBookName(String bookName) {
		Optional<Book> bookFound = bookRepository.findBookByBookNameIgnoreCase(bookName);

		if (bookFound.isEmpty()) {
			log.error("Book does not exists with bookName " + bookName);
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder()
					.status(HttpStatus.BAD_REQUEST).message("Book does not exists with bookName " + bookName).build());
		}
		return bookFound.get();

	}

	public Book deleteBookByBookId(Long bookId) {
		Optional<Book> bookFound = bookRepository.findById(bookId);

		if (bookFound.isEmpty()) {
			log.error("Book does not exists with bookId " + bookId);
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder()
					.status(HttpStatus.BAD_REQUEST).message("Book does not exists with bookId " + bookId).build());
		}

		Book newBook = bookFound.get();
		bookRepository.deleteById(bookId);
		log.info("Book with id " + bookId + " has been deleted successfully!!");
		return newBook;
	}

	public Book deleteBookByBookName(String bookName) {
		Optional<Book> bookFound = bookRepository.findBookByBookNameIgnoreCase(bookName);

		if (bookFound.isEmpty()) {
			log.error("Book does not exists with bookName " + bookName);
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder()
					.status(HttpStatus.BAD_REQUEST).message("Book does not exists with bookName " + bookName).build());
		}
		Book newBook = bookFound.get();
		bookRepository.deleteBookByBookNameIgnoreCase(bookName);
		log.info("Book with name " + bookName + " has been deleted successfully!!");
		return newBook;
	}

	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		if (books.isEmpty()) {
			log.error("No books found");
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder().status(HttpStatus.NOT_FOUND)
					.message("No books found").build());
		}
		return books;

	}

	public Book updateBookByBookId(Long bookId, Book book) {
		return bookRepository.findById(bookId).map(existingBook -> {
			Arrays.stream(Book.class.getDeclaredFields()).filter(field -> !field.getName().equals("bookId"))
					.forEach(field -> {
						field.setAccessible(true);
						try {
							Object value = field.get(book);
							if (value != null) {
								field.set(existingBook, value);
							}
						} catch (IllegalAccessException e) {
							throw new RuntimeException("Error updating book fields");
						}
					});

			return bookRepository.save(existingBook);
		}).orElseThrow(() -> {
			log.error("Book does not exist with bookId " + bookId);
			return new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder()
					.status(HttpStatus.NOT_FOUND).message("Book does not exist with bookId " + bookId).build());
		});

	}
	
	public Book updateBookByBookName(String bookName, Book book) {
		return bookRepository.findBookByBookNameIgnoreCase(bookName).map(existingBook -> {
			Arrays.stream(Book.class.getDeclaredFields()).filter(field -> !field.getName().equals("bookId"))
					.forEach(field -> {
						field.setAccessible(true);
						try {
							Object value = field.get(book);
							if (value != null) {
								field.set(existingBook, value);
							}
						} catch (IllegalAccessException e) {
							throw new RuntimeException("Error updating book fields");
						}
					});

			return bookRepository.save(existingBook);
		}).orElseThrow(() -> {
			log.error("Book does not exists with bookName " + bookName);
			throw new OnlineBookReadingAppException(OnlineBookReadingAppResponse.builder()
					.status(HttpStatus.NOT_FOUND).message("Book does not exists with bookName " + bookName).build());
		});

	}

}
