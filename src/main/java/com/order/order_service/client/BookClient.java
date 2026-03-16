package com.order.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.order_service.dto.BookDTO;
import com.order.order_service.fallback.BookClientFallback;

@FeignClient(name = "BOOK-SERVICE", fallback = BookClientFallback.class)
public interface BookClient {

    @GetMapping("/books/{id}")
    BookDTO getBook(@PathVariable("id") Long id);
}