package com.order.order_service.dto;

public class OrderResponseDTO {

	private Long orderId;
	private Integer quantity;
	private Double totalPrice;
	private BookDTO book;

	public OrderResponseDTO() {
	}

	public OrderResponseDTO(Long orderId, Integer quantity, Double totalPrice, BookDTO book) {
		this.orderId = orderId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.book = book;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public BookDTO getBook() {
		return book;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}
}