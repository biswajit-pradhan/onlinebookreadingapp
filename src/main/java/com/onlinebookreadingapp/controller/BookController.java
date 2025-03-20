package com.onlinebookreadingapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    return ResponseEntity.ok(bookServiceImpl.addBook(book));
  }

  @GetMapping("/getBookByBookId/{bookId}")
  public ResponseEntity<Book> getBookByBookId(@PathVariable(value = "bookId") Long bookId) {
    return ResponseEntity.ok(bookServiceImpl.getBookByBookId(bookId));
  }

  @GetMapping("/getBookByBookName/{bookName}")
  public ResponseEntity<Book> getBookByBookName(@PathVariable(value = "bookName") String bookName) {
    return ResponseEntity.ok(bookServiceImpl.getBookByBookName(bookName));
  }

  @DeleteMapping("/deleteBookByBookId/{bookId}")
  public ResponseEntity<Book> deleteBookByBookId(@PathVariable(value = "bookId") Long bookId) {
    return ResponseEntity.ok(bookServiceImpl.deleteBookByBookId(bookId));
  }

  @DeleteMapping("/deleteBookByBookName/{bookName}")
  public ResponseEntity<Book> deleteBookByBookName(@PathVariable(value = "bookName") String bookName) {
    return ResponseEntity.ok(bookServiceImpl.deleteBookByBookName(bookName));
  }

  @GetMapping("/getAllBooks")
  public ResponseEntity<List<Book>> getAllBooks() {
    return ResponseEntity.ok(bookServiceImpl.getAllBooks());
  }

  @PutMapping("/updateBookByBookId/{bookId}")
  public ResponseEntity<Book> updateBookByBookId(@PathVariable(value = "bookId") Long bookId, @RequestBody Book book) {
    return ResponseEntity.ok(bookServiceImpl.updateBookByBookId(bookId, book));
  }

  @PutMapping("/updateBookByBookName/{bookName}")
  public ResponseEntity<Book> updateBookByBookName(@PathVariable(value = "bookId") String bookName,
      @RequestBody Book book) {
    return ResponseEntity.ok(bookServiceImpl.updateBookByBookName(bookName, book));
  }
}
