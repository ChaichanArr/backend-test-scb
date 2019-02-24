package com.example.demo.user.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.user.view.UserRequest;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import com.example.demo.user.view.OrderRequest;
import com.example.demo.bookstore.service.service.BookStoreService;

@RestController
@Validated
@Log
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookStoreService bookStoreService;

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@Valid @RequestBody UserRequest request,
                                Errors errors) throws Exception {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        if (userService.login(request)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers(@RequestParam(name = "id", required = true) final Long id) throws Exception {

        User user = userService.getUserById(id);
        String[] books = user.getBooks().split(",");
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("name", user.getName());
        mapResponse.put("surname", user.getSurname());
        mapResponse.put("date_of_birth", dt1.format(user.getDateOfBirth()));
        mapResponse.put("books", books);
        return new ResponseEntity(mapResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestParam(name = "id", required = true) final Long id) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                String error = "No content to delete";
                return new ResponseEntity(error, HttpStatus.NO_CONTENT);
            }

            userService.deleteUser(id);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            String error = "Cannot Delete : " + exception;
            return new ResponseEntity(error, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@Valid @RequestBody UserRequest request, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        try {
            userService.createUser(new UserBuilder().detail(request).build());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            String error = "Cannot Create : " + ex;
            return new ResponseEntity(error, HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/users/orders",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPrice(@Valid @RequestBody OrderRequest request, Errors errors) throws Exception {
        Double price = bookStoreService.getPriceByOrderId(request);
        Map<String, Double> mapResponse = new HashMap<>();
        mapResponse.put("name", price);
        return new ResponseEntity(mapResponse, HttpStatus.OK);
    }

    class UserBuilder {
        private User target = new User();

        public User build() {
            return target;
        }

        public UserBuilder detail(UserRequest request) throws ParseException {
            target.setUsername(request.getUsername());
            target.setPassword(request.getPassword());
            if (request.getDate_of_birth() != null) {
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                target.setDateOfBirth(formatter.parse(request.getDate_of_birth()));
            }
            return this;
        }
    }

}
