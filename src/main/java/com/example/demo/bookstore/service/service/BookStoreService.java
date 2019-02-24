package com.example.demo.bookstore.service.service;

import com.example.demo.user.view.OrderRequest;
import java.util.List;

public interface BookStoreService {

    Double getPriceByOrderId(OrderRequest request) throws Exception;

    List<?> getBookStoreAll() throws Exception;
}
