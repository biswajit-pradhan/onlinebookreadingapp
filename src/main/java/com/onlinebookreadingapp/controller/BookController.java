package com.onlinebookreadingapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebookreadingapp.entity.Book;
import com.onlinebookreadingapp.serviceImpl.BookServiceImpl;

@RestController
@RequestMapping("/book/v1")
@CrossOrigin(origins = { "*" })
public class BookController {

	private final BookServiceImpl bookServiceImpl;

	public BookController(BookServiceImpl bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}

	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		return ResponseEntity.ok(bookServiceImpl.addBook(book));
	}
}
