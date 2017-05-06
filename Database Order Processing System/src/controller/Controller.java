package controller;

import java.util.List;

import data.Book;
import data.Cart;
import data.Order;
import data.User;

public class Controller implements ViewListener{

	
	/*(non-Javadoc)
	 * @see controller.ViewListener#userLogin(data.User)
	 */
	@Override
	public boolean userLogin(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userSignup(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> bookSearch(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean placeOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean confirmOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addToCart(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkOutCart(Cart cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promoteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/*
	 * Controller to View part
	 */
	public void viewUserLogin(){
		
	}
	public void viewUserSignup(){
		
	}
	public void viewAddBook(){
		
	}
	public void viewModifyBook(){
		
	}
	public void viewOrders(){
		
	}
	public void viewPlaceOrder(){
		
	}
	public void viewConfirmOrder(){
		
	}
}
