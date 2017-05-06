package data;

public class Order extends Record{
	public Book getBook(){
		return null;
	}
	public int getOrderedCopies(){
		return Integer.parseInt(getProperty("copies"));
	}
}
