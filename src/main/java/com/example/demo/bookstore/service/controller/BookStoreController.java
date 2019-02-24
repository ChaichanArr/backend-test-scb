package com.example.demo.bookstore.service.controller;

import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.bookstore.service.service.BookStoreService;

@RestController
@Validated
@Log
public class BookStoreController {

    @Autowired
    private BookStoreService bookStoreService;

    @RequestMapping(value = "/books",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBooks() throws Exception {
        List<?> response = bookStoreService.getBookStoreAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
