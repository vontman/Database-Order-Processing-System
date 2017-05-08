import java.sql.SQLException;

import model.BookModel;

public class Main {

	public static void main(String[] args) throws SQLException {
		System.out.println(BookModel.getBook("1").values());
	}

}
