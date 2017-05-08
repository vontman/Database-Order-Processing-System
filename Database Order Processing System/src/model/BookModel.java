package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.Book;
import data.Order;

public class BookModel {
  /*
      ORDER:
      DONE : add order
      DONE : view orders
      DONE : delete orders
      
      BOOK:
      DONE : add book	
      DONE : update book
      DONE : search books
      DONE : delete book
      DONE : get category
      
      AUTHORS:
      DONE : get by isbn
      DONE : add new authors
      DONE : get book from author
      DONE :  delete authors
     
      USER:
      DONE :  get by username
      DONE : delete user
      DONE :  add new user
      DONE : update user
      DONE : view users
      DONE : check passwrod validity
      
      Purchaces:
      TODO : new purchace(quantity, book, price, user)
      
      Credit Card:
      TODO : check card validity
      
      
      
  */
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
	
	public static boolean checkCreditCard (String number, int price) throws SQLException {
		List<String> cols = new ArrayList<String>();
		List<String> vals = new ArrayList<String>();
		cols.add("Number");
		vals.add(number);
		ResultSet hamada = model.select("creditCard", cols, vals);
		String string = hamada.getString("ExpirationDate");
		int balance = Integer.parseInt(hamada.getString("Balance"));
		if (balance < price) {
			return false;
		}
		string = new SimpleDateFormat("yyyy-MM-dd").format(string);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return string.compareTo(date) >= 0;
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
		}else{
			System.out.println("Book not found");
			return null;
		}
		book.setProperty("category", ""+getCategory(book.getCategoryId()));
		book.setProperty("authors", getAuthors(isbn));
		return book;
		
	}
	
	public static String getAuthors(String isbn) throws SQLException {

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		cols.add("BOOK_ISBN");
		vals.add(isbn);
		
		ResultSet result = model.select("BOOK_HAS_AUTHORS ", cols, vals);
		while(result.next()){
			if(builder.length() > 0)
				builder.append(",");
			builder.append(result.getString("authors_name"));
		}
		return builder.toString();
	}	
	public static String getCategory(int id) throws SQLException {

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("id");
		vals.add(""+id);
		
		ResultSet result = model.select("category", cols, vals);
		if(result.next()){
			return result.getString("type");
		}
		return null;
	}
	
	public static List<Book> getBooks(List<String> cols, List<String> vals, String category, String author) throws SQLException {
        String condition = "";
        String cmnd = "SELECT * FROM BOOK ";
        for(int i = 0 ; i < vals.size() ; i ++){
        	vals.set(i, "'"+vals.get(i)+"'");
        }
        if(author != null || category != null){
          cmnd += "JOIN"; 
        }
        if(category != null){
            cmnd += " Category ON type='"+category+"'";
        	cols.add("Category.ID");
          	vals.add("category_id");
        }
        if(author != null){
         	 if(category != null)
				cmnd +=",";
            cmnd += " BOOK_HAS_AUTHORS ON authors_name='"+author+"'";
        	cols.add("Book_ISBN");
          	vals.add("ISBN");
        }
        for(int i = 0 ;  i < cols.size() ; i ++){
            if(i > 0)
              condition += " AND ";
            condition += cols.get(i)+"="+vals.get(i)+" ";
        }
      	if(cols.size() > 0)
      		cmnd += " WHERE ("+condition+") ";
      	ResultSet result = model.executeCommand(cmnd);
      	List<Book>ret = new ArrayList<Book>();
       	while(result.next()){
          	Book book = new Book();
          	book.setProperty("isbn", result.getString("isbn"));	
			book.setProperty("title", result.getString("title"));	
			book.setProperty("price", ""+result.getInt("price"));
			book.setProperty("copies", ""+result.getInt("copies"));	
			book.setProperty("publication_year", result.getString("publication_year"));	
			book.setProperty("category_id", result.getString("category_id"));	
			book.setProperty("publisher_name", result.getString("publisher_name"));	
			book.setProperty("threshold", result.getString("threshold"));
        	ret.add(book);
		}
       	for(Book book :ret){
			book.setProperty("category", getCategory(book.getCategoryId()));
			book.setProperty("authors", getAuthors(book.getIsbn()));
       	}
      	return ret;
	}
    public static boolean addOrder(Order order) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("book_isbn");
		vals.add(order.getBookIsbn());
		cols.add("copies");
		vals.add(""+order.getOrderedCopies());
		cols.add("created");
		vals.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		model.insert("ORDERS", cols, vals);
		System.out.println("Order added successfully.");
		return true;
	}

	public static List<Order> getOrders() throws SQLException{

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		List<Order>orders = new ArrayList<Order>();
		ResultSet result = model.select("ORDERS", cols, vals);
		while(result.next()){
			Order order = new Order();
			order.setProperty("book_isbn", result.getString("book_isbn"));
			order.setProperty("copies", result.getString("copies"));
			order.setProperty("created", result.getString("created"));
			orders.add(order);
		}
		return orders;
	}
	public static boolean removeOrder(String book_isbn) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("book_isbn");
		vals.add(book_isbn);
		
		model.delete("ORDERS", cols, vals);
		System.out.println("Order deleted successfully.");
		return true;
	}
      
	public static boolean removeBook(String book_isbn) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("isbn");
		vals.add(book_isbn);
		
		model.delete("BOOK", cols, vals);
		System.out.println("Order deleted successfully.");
		return true;
	}
      
	public static boolean addAuthor(String authorName) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("name");
		vals.add(authorName);
		
		model.insert("AUTHORS", cols, vals);
		System.out.println("Author added successfully.");
		return true;
	}
      
	public static boolean removeAuthor(String name) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("name");
		vals.add(name);
		
		model.delete("AUTHORS", cols, vals);
		System.out.println("Author deleted successfully.");
		return true;
	}
	public static boolean addPurchase(List<String>bookIsbn, List<Integer>copies, String username) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		
		cols.add("username");
		vals.add(username);
		String id = null;
		ResultSet rs = model.insert("Purchases", cols, vals);
		if(rs.next()){
			id = rs.getString("ID");
		}else{
			return false;
		}
		for(int i = 0  ; i < bookIsbn.size() ; i ++){
			addPurchaseMini(id, bookIsbn.get(i), copies.get(i));
		}
		
		
		return true;
	}
	private static boolean addPurchaseMini(String purchaceId, String isbn, int copies)throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		
		cols.add("ISBN");
		cols.add(isbn);
		cols.add("Purchases_ID");
		vals.add(purchaceId);
		cols.add("Quantity");
		vals.add(""+copies);
		
		model.insert("BOOK_HAS_PURCHASES", cols, vals);
		return true;
	}
      
}