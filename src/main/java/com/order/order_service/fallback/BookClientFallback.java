package com.order.order_service.fallback;

import org.springframework.stereotype.Component;

import com.order.order_service.client.BookClient;
import com.order.order_service.dto.BookDTO;

@Component
public class BookClientFallback implements BookClient {

//	@Override
//	public BookDTO getBook(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public BookDTO getBook(Long id) {

		System.out.println("Fallback triggered: Book Service unavailable");
		BookDTO book = new BookDTO();
		book.setId(id);
		book.setTitle("Book Service Unavailable");
		book.setAuthor("Unknown");
		book.setPrice(0.0);

		return book;
	}
}