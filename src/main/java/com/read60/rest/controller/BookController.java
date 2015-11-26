package com.read60.rest.controller;

import com.read60.rest.entity.Book;
import com.read60.rest.repository.BookRepository;

public class BookController extends GenericController<Book> implements BookRepository{

	public BookController() {
		super(Book.class);
	}

}
