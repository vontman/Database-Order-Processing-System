package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.Book;

public class BookModel {
	private static Model model = Model.instance;
	public static boolean createBook(Book book) throws SQLException {
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("isbn");
		vals.add(book.getIsbn());
		cols.add("created");
		vals.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		model.insert("BOOK", cols, vals);
		System.out.println("Book added successfully.");
		return true;
	}
	
	public static boolean updateBook(Book book, String isbn) throws SQLException {
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("isbn");
		vals.add(book.getIsbn());
		cols.add("title");
		cols.add("price");
		cols.add("copies");
		cols.add("publication_year");
		cols.add("category_id");
		cols.add("publisher_name");
		cols.add("threshold");
		
		model.update("BOOK", cols, vals, "isbn", isbn);
		System.out.println("Book updated successfully.");
		return true;
	}
	public static Book getBook(String isbn) throws SQLException {

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		Book book = new Book();
		cols.add("isbn");
		vals.add(isbn);
		
		ResultSet result = model.select("BOOK", cols, vals);
		if(result.next()){
			book.setProperty("isbn", result.getString("isbn"));	
			book.setProperty("title", result.getString("title"));	
			book.setProperty("price", ""+result.getInt("price"));	
			book.setProperty("copies", ""+result.getInt("copies"));	
			book.setProperty("publication_year", result.getString("publication_year"));	
			book.setProperty("category_id", result.getString("category_id"));	
			book.setProperty("publisher_name", result.getString("publisher_name"));	
			book.setProperty("threshold", result.getString("threshold"));
			book.setProperty("authors", getAuthors(isbn));
			return book;
		}else{
			System.out.println("Book not found");
			return null;
		}
		
	}
	public static String getAuthors(String isbn) throws SQLException{

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		cols.add("isbn");
		vals.add(isbn);
		
		ResultSet result = model.select("BOOK_HAS_AUTHORS", cols, vals);
		while(result.next()){
			if(builder.length() > 0)
				builder.append(",");
			builder.append(result.getString("authors_name"));
		}
		return builder.toString();
	
	}
	
}
