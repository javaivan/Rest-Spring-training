package it.discovery.controller;

import it.discovery.model.Book;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import it.discovery.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookRepository bookRepository;

	@RequestMapping
	//@Secured("ADMIN")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Resource<Book>> findBooks() {
		List<Book> books = bookRepository.findAll();

		List<Resource<Book>> resources = new ArrayList<>();

		for (Book book: books){
			Resource<Book> resource = new Resource<Book>(book);
			resource.add(linkTo(methodOn(BookController.class)
					.findById(String.valueOf(book.getId())))
					.withSelfRel());
			resource.add(linkTo(methodOn(BookController.class)
					.findById(String.valueOf(book.getId())))
					.withRel("buy"));
			resources.add(resource);
		}

		return resources;
	}

	@RequestMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable("id") String id){
		int bookId = NumberUtils.toInt(id);
		if(bookId<0) {
			Book book = new Book();
			book.setAuthor("admin");
			book.setName("cooking");
			book.setYear(1999);
			bookRepository.save(book);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Book book = bookRepository.findById(bookId);
		if(book == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(book, HttpStatus.OK);

	}


	@RequestMapping(path = "/", method = RequestMethod.POST)
	public Book saveBook(@RequestBody Book book){
		bookRepository.save(book);
		return book;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable("id") int id){
		bookRepository.delete(id);
	}


	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void updateBook(@PathVariable("id") int id, @RequestBody Book book){
		bookRepository.save(book);
	}


}
