package it.discovery.controller;

import it.discovery.model.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @RequestMapping("/book")
    public Book getBook() {
        Book book = new Book();
        book.setId(1);
        book.setAuthor("admin");
        book.setName("cooking");
        book.setYear(1999);
        return book;
    }
}
