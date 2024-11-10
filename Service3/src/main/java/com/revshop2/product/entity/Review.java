package com.revshop2.product.entity;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int id, String username, int rating, String comment, Product product) {
		super();
		this.id = id;
		this.username = username;
		this.rating = rating;
		this.comment = comment;
		this.product = product;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", username=" + username + ", rating=" + rating + ", comment=" + comment
				+ ", product=" + product + "]";
	}

    // Getters and Setters
}
