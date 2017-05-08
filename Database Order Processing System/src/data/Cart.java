package data;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import model.BookModel;

public class Cart {
	private HashMap<String, Integer> books;
	
	public Cart() {
		books = new HashMap<>();
	}
	
	public void addBook(String isbn, int copies) {
		books.put(isbn, books.getOrDefault(isbn, 0) + copies);
	}
	
	public void removeBook(String isbn) {
		books.remove(isbn);
	}
	
	public int getCost() throws SQLException {
		int ret = 0;
		for (Entry<String, Integer> book : books.entrySet()) {
			ret += BookModel.getBook(book.getKey()).getPrice() * book.getValue();
		}
		return ret;
	}
}
