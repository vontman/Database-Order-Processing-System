package data;

import java.util.Properties;

public class Order extends Properties{
	public Book getBook(){
		return null;
	}
	public int getOrderedCopies(){
		return Integer.parseInt(getProperty("copies"));
	}
}
