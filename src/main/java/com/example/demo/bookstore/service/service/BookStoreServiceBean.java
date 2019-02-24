package com.example.demo.bookstore.service.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.val;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import com.example.demo.bookstore.service.view.BookStoreResponse;
import org.springframework.web.client.RestTemplate;
import com.example.demo.user.view.OrderRequest;

@Setter
@Log
@Service
public class BookStoreServiceBean implements BookStoreService {

    @Override
    public Double getPriceByOrderId(OrderRequest request) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://scb-test-book-publisher.herokuapp.com/books";
        val response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookStoreResponse>>() {
        });
        List<BookStoreResponse> BookStoreMaster = response.getBody();
        Double price = 0.0;
        Long[] orders = request.getOrders();
        for (int i =0; i < orders.length; i++) {
            Long orderId = orders[i];
            List<BookStoreResponse> temp = BookStoreMaster.stream().filter(o -> o.getId() == orderId).collect(Collectors.toList());
            price = price + temp.get(0).getPrice();
        }
        return price;
    }

    @Override
    public List<?> getBookStoreAll() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://scb-test-book-publisher.herokuapp.com/books";
        val responseMaster = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookStoreResponse>>() {
        });
        List<BookStoreResponse> bookStoreMaster = responseMaster.getBody();

        String uri2 = "http://scb-test-book-publisher.herokuapp.com/books/recommendation";
        val responseRecommendation = restTemplate.exchange(uri2, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookStoreResponse>>() {
        });

        List<BookStoreResponse> bookStoreRecommendation = responseRecommendation.getBody();
        bookStoreMaster.removeAll(bookStoreRecommendation);
        bookStoreRecommendation.addAll(bookStoreMaster);
        return bookStoreRecommendation;
    }
}
