package com.example.demo.bookstore.service.view;

import lombok.Data;

@Data
public class BookStoreResponse {

    private Long id;

    private String book_name;

    private Double price;

    private String author_name;
}
