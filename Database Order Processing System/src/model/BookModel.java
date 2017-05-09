package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.Book;
import data.Category;
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
      DONE : checkout
      TODO : get
      
      Credit Card:
      DONE : check card validity
      
      
      TESTING
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
 
      Purchaces:
      DONE : checkout
      
  */
	private static Model model = Model.instance;
	
	public static boolean createBook(Book book) throws SQLException {
		try{

			model.setAutoCommit(false);
			List<String>cols = new ArrayList<String>();
			List<String>vals= new ArrayList<String>();
			cols.add("isbn");
			vals.add(book.getIsbn());
			cols.add("title");
			vals.add(book.getTitle());
			cols.add("copies");
			vals.add("" + book.getCopies());	
			cols.add("publication_year");
			vals.add(book.getPublishYear());
			cols.add("category_id");
			vals.add("" + book.getCategoryId());
			cols.add("publisher_name");
			vals.add(book.getPublisher());
			cols.add("threshold");
			vals.add("" + book.getThreshold());
			cols.add("price");
			vals.add("" + book.getPrice());
			model.insert("BOOK", cols, vals);
			try{
				addPublisher(book.getPublisher());
			}catch(SQLException ex){
				
			}
			for (String author : book.getAuthors()) {
				try{
					addAuthor(author);
				}catch(Exception ex){
					
				}
				addAuthorRelation(book.getIsbn(), author);
			}
			model.commit();
			System.out.println("Book added successfully.");
		}catch(Exception e){
			model.rollBack();
			throw e;
		}finally{
			model.setAutoCommit(true);
		}
		return true;
	}	
	public static void addAuthorRelation (String book_isbn, String authors_name) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("book_isbn");
		vals.add(book_isbn);
		cols.add("authors_name");
		vals.add(authors_name);
		model.insert("BOOK_HAS_AUTHORS", cols, vals);
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
	public static boolean addPublisher(String publisher) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("Name");
		vals.add(publisher);
		cols.add("Address");
		vals.add("dummy address");
		cols.add("Phone_number");
		vals.add("012");
		
		model.insert("publisher", cols, vals);
		System.out.println("publisher added successfully.");
		return true;
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
	private static boolean addPurchases(List<String>bookIsbn, List<Integer>copies, String username) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("username");
		vals.add(username);
		String id = null;
		ResultSet rs = model.insert("Purchases", cols, vals);
		if(rs.next()){
			id = ""+rs.getInt(1);
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
		vals.add(isbn);
		cols.add("Purchases_ID");
		vals.add(purchaceId);
		cols.add("Quantity");
		vals.add(""+copies);
		
		model.insert("BOOK_HAS_PURCHASES", cols, vals);
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
			book.setProperty("threshold", ""+result.getInt("threshold"));
		}else{
			System.out.println("Book not found");
			return null;
		}
		book.setProperty("category", ""+getCategory(book.getCategoryId()));
		book.setProperty("authors", getAuthors(isbn));
		return book;
		
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
			book.setProperty("threshold", ""+result.getInt("threshold"));
        	ret.add(book);
		}
       	for(Book book :ret){
			book.setProperty("category", getCategory(book.getCategoryId()));
			book.setProperty("authors", getAuthors(book.getIsbn()));
       	}
      	return ret;
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
	
    public static List<Category> getCategories() throws SQLException {

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		
		ResultSet result = model.select("category", cols, vals);
		List<Category>ret = new ArrayList<Category>();
		while(result.next()){
			Category category = new Category();
			category.setProperty("type", result.getString("type"));
			category.setProperty("id", result.getString("id"));
			ret.add(category);
		}
		return ret;
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
		throw new SQLException("Category not found.");
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
	
	public static boolean updateBook(Book book, String isbn) throws SQLException {
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("isbn");
		vals.add(book.getIsbn());
		cols.add("title");
		vals.add(book.getTitle());
		cols.add("copies");
		vals.add("" + book.getCopies());	
		cols.add("publication_year");
		vals.add(book.getPublishYear());
		cols.add("category_id");
		vals.add("" + book.getCategoryId());
		cols.add("publisher_name");
		vals.add(book.getPublisher());
		cols.add("threshold");
		vals.add("" + book.getThreshold());
		cols.add("price");
		vals.add("" + book.getPrice());
		model.executeUpdate("DELETE from book_has_authors where book_isbn='"+book.getIsbn()+"'");
		try{
			addPublisher(book.getPublisher());
		}catch(SQLException ex){
			
		}
		for (String author : book.getAuthors()) {
			try{
				addAuthor(author);
			}catch(Exception ex){
				
			}
			addAuthorRelation(book.getIsbn(), author);
		}
		
		model.update("BOOK", cols, vals, "isbn", isbn);
		System.out.println("Book updated successfully.");
		return true;
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
	public static boolean removeAuthor(String name) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("name");
		vals.add(name);
		
		model.delete("AUTHORS", cols, vals);
		System.out.println("Author deleted successfully.");
		return true;
	}
	
	public static boolean checkOut(List<String>bookIsbn, List<Integer>copies, String username, String cardNum, String pin) throws SQLException{
		try{
			int totPrice= 0;
			model.setAutoCommit(false);
			for(int i = 0 ;  i < bookIsbn.size() ; i ++){
				Book book = getBook(bookIsbn.get(i));
				totPrice += book.getPrice()*copies.get(i);
				book.setProperty("copies", ""+(book.getCopies()-copies.get(i)));
				updateBook(book, bookIsbn.get(i));
			}
			int balance = UserModel.checkCreditCard(cardNum, pin, totPrice, username);
			UserModel.updateCreditCard(cardNum, balance-totPrice);
			addPurchases(bookIsbn, copies, username);
			model.commit();
		}catch(Exception e){
			model.rollBack();
			throw e;
		}finally{
			model.setAutoCommit(true);
		}
		
		return true;
	}
	
}
