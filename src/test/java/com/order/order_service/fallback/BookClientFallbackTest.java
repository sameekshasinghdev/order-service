package com.order.order_service.fallback;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.order.order_service.dto.BookDTO;

class BookClientFallbackTest {

	@Test
	void testFallbackReturnsNull() {
		//fail("Not yet implemented");
		BookClientFallback fallback = new BookClientFallback();

	    BookDTO book = fallback.getBook(1L);

	    assertNotNull(book);
	}

}
