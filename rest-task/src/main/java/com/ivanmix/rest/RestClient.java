package com.ivanmix.rest;

import com.ivanmix.model.Book;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

public class RestClient {

    //private static final TestRestTemplate restTemplate = new RestTemplate();


    private static final TestRestTemplate restTemplate = new TestRestTemplate("admin", "admin");
/*
    public RestClient(){
        restTemplate = new TestRestTemplate("admin", "admin");
    }*/

    private static List<Book> findBooks(){
        return (List<Book>) restTemplate.getForObject(
                "http://localhost:8080/book/", List.class);
    }

    private static Book findBook(){
        return (Book) restTemplate.getForObject(
                "http://localhost:8080/book/1", Book.class);
    }

    public static void main(String[] args){
        System.out.println(findBooks());
        System.out.println(findBook());
    }
}
