import java.sql.SQLException;
import java.util.ArrayList;

import model.BookModel;
import model.UserModel;

public class Main {

	public static void main(String[] args) throws SQLException {
		System.out.println(BookModel.getBook("1"));
		System.out.println(BookModel.getAuthors("1"));
		System.out.println(BookModel.getBooks(new ArrayList<String>(), new ArrayList<String>(), null, null).size());
//		System.out.println(BookModel.addAuthor("dont_hurt_me"));
//		System.out.println(BookModel.removeAuthor("dont_hurt_me"));
//		System.out.println(BookModel.removeBook("4"));
		System.out.println(BookModel.getOrders().size());
		
		ArrayList<String>keys = new ArrayList<String>();
		ArrayList<String>vals = new ArrayList<String>();
		keys.add("title");
		vals.add("Competetive");
		System.out.println(BookModel.getBooks(keys, vals, null, null));
		keys.clear();
		vals.clear();
		System.out.println(BookModel.getBooks(keys, vals, "programming", null));
		System.out.println(UserModel.getUsers());
		System.out.println(UserModel.getUser("zezo"));
		System.out.println(UserModel.checkUser("zezo", "zezopass"));
		System.out.println(UserModel.checkUser("zezo", "zezopasss"));
//		System.out.println(UserModel.removeUser("zezo8"));
		
	}

}
