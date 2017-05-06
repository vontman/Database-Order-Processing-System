package controller;

import java.util.List;

import data.Book;
import data.Cart;
import data.Order;
import data.User;

/// Way to talk from the view to the controller
public interface ViewListener {
	public boolean userLogin(User user);
	public boolean userSignup(User user);
	public List<Book> bookSearch(Book book);
	public boolean addBook(Book book);
	public boolean updateBook(Book book);
	public boolean placeOrder(Order order);
	public boolean confirmOrder(Order order);
	public boolean addToCart(Book book);
	public boolean checkOutCart(Cart cart);
	public boolean promoteUser(User user);
}
