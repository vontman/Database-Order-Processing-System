package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
	public static Model instance = new Model();

	private static final String url = "localhost";
	private static final String user = "root";
	private static final String pass = "";
	
	private Connection connection = null;
	private Statement statement = null;
	private String error = null;
	private Model() {
		connect(url, user, pass);
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		connection.close();
		statement.close();
	}
	private boolean connect(String url, String user, String pass){
		try {
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
			error = null;
			return true;
		} catch (SQLException e) {
			error = e.getMessage();
			return false;
		}
	}
	public String getError(){
		String tmp = error;
		error = null;
		if(tmp == null)
			return "No Error.";
		return tmp;
	}
	public ResultSet executeCommand(String command) throws SQLException{
		return statement.executeQuery(command);
	}
}
