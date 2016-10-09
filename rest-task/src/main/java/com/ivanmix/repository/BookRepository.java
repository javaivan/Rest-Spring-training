package com.ivanmix.repository;

import java.util.List;

import com.ivanmix.model.Book;

public interface BookRepository {
	Book findById(int id);
	
	List<Book> findAll();
	
	void save(Book book);
	
	boolean delete(int id);
}
