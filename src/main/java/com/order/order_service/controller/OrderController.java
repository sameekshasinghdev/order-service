package com.order.order_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.order_service.client.BookClient;
import com.order.order_service.dto.BookDTO;
import com.order.order_service.dto.OrderRequestDTO;
import com.order.order_service.dto.OrderResponseDTO;
import com.order.order_service.entity.Order;
import com.order.order_service.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final BookClient bookClient;
	private final OrderService service;

	public OrderController(BookClient bookClient, OrderService service) {
		this.bookClient = bookClient;
		this.service = service;
	}

	@PostMapping
	public Order createOrder(@Valid @RequestBody OrderRequestDTO request) {
		return service.createOrder(request);
	}

	@GetMapping
	public List<Order> getOrders() {
		return service.getAllOrders();
	}

	@GetMapping("/{id}")
	public OrderResponseDTO getOrder(@PathVariable Long id) {
		return service.getOrder(id);
	}

	@DeleteMapping("/{id}")
	public String deleteOrder(@PathVariable Long id) {
		service.deleteOrder(id);
		return "Order deleted";
	}

	@GetMapping("/test/{id}")
	// @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackBookService")
	public String testOrder(@PathVariable Long id) {

		BookDTO book = bookClient.getBook(id);

		return "Order Service -> Book Title: " + book.getTitle() + ", Price: " + book.getPrice();
	}

	public String fallbackBookService(Long id, Exception ex) {
		return "Order Service -> Book Service currently unavailable";
	}
}
