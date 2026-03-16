package com.order.order_service.service;

import java.util.List;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import com.order.order_service.client.BookClient;
import com.order.order_service.dto.BookDTO;
import com.order.order_service.dto.OrderRequestDTO;
import com.order.order_service.dto.OrderResponseDTO;
import com.order.order_service.entity.Order;
import com.order.order_service.repository.OrderRepository;

@Service
public class OrderService {
	
	@CircuitBreaker(name = "bookService", fallbackMethod = "bookFallback")
	public BookDTO getBookFromService(Long bookId) {
	    return bookClient.getBook(bookId);
	}
	public BookDTO bookFallback(Long bookId, Exception ex) {

	    System.out.println("Fallback triggered: Book Service unavailable");

	    BookDTO book = new BookDTO();
	    book.setId(bookId);
	    book.setTitle("Service unavailable");
	    book.setPrice(0.0);

	    return book;
	}
	private final OrderRepository repository;
	private final BookClient bookClient;
	
	public OrderService(OrderRepository repository, BookClient bookClient) {
        this.repository = repository;
        this.bookClient = bookClient;
    }
	
	public Order createOrder(OrderRequestDTO request) {

        //BookDTO book = bookClient.getBook(request.getBookId());
		BookDTO book = getBookFromService(request.getBookId());
        if (book == null) {
            throw new RuntimeException("Book not found or Book Service unavailable");
        }

        Order order = new Order();
        order.setBookId(request.getBookId());
        order.setQuantity(request.getQuantity());

        double total = book.getPrice() * request.getQuantity();
        order.setTotalPrice(total);

        return repository.save(order);
    }
	 public List<Order> getAllOrders() {
	        return repository.findAll();
	    }

//	    public Order getOrder(Long id) {
//	        return repository.findById(id).orElse(null);
//	    }

	    public void deleteOrder(Long id) {
	        repository.deleteById(id);
	    }
	    
	    public OrderResponseDTO getOrder(Long id) {

	        Order order = repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found"));

	        //BookDTO book = bookClient.getBook(order.getBookId());
	        BookDTO book = getBookFromService(order.getBookId());

	        return new OrderResponseDTO(
	                order.getId(),
	                order.getQuantity(),
	                order.getTotalPrice(),
	                book
	        );
	    }
}
