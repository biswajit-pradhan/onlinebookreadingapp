package com.onlinebookreadingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebookreadingapp.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	public Optional<Book> findBookByBookNameIgnoreCase(String bookName);

	public Book deleteBookByBookNameIgnoreCase(String bookName);
}
