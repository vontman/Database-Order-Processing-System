package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.Book;
import data.User;

public class BookModel {
	private static Model model = Model.instance;
	public static void createBook(Book book){
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("isbn");
		vals.add(book.getIsbn());
		cols.add("created");
		vals.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		try {
			model.insert("BOOK", cols, vals);
			System.out.println("Book added successfully.");
		} catch (SQLException e) {
			System.out.println("Book not added, Error : "+e.getMessage());
		}
	}
	
	public static void updateBook(Book book, String isbn){
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
		
		try {
			model.update("BOOK", cols, vals, "isbn", isbn);
			System.out.println("Book updated successfully.");
		} catch (SQLException e) {
			System.out.println("Book not updated, Error : "+e.getMessage());
		}
	}
	public static Book getBook(String isbn){

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		
		cols.add("isbn");
		vals.add(isbn);
		
		try{
			ResultSet result = model.select("BOOK", cols, vals);
			
		}catch(SQLException e){
			System.out.println("ERROR finding the book :"+e.getMessage());
		}
		return null;
	}
}
