package data;

import java.util.Properties;

public class Order extends Properties{
	public String getBookIsbn(){
		return getProperty("book_isbn");
	}
	public int getOrderedCopies(){
		return Integer.parseInt(getProperty("copies"));
	}
}
