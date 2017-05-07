package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
		System.out.println("Executing command : "+command);
		return statement.executeQuery(command);
	}
	public ResultSet executeUpdate(String command) throws SQLException{
		System.out.println("Executing update command : "+command);
		statement.executeUpdate(command);
		return statement.getGeneratedKeys();
	}
	public ResultSet insert(String tableName, List<String>cols, List<String>vals) throws SQLException{
		if(cols.size() != vals.size())
			throw new RuntimeException("Invalid call with incompatable cols and vals.");
		String stmnt = "INSERT INTO "+tableName+
				" ("+String.join(",", cols)
				+") VALUES ("+String.join(",", cols)+");";
		return executeUpdate(stmnt);
	}
	public void delete(String tableName, List<String>cols, List<String>vals) throws SQLException{
		if(cols.size() != vals.size())
			throw new RuntimeException("Invalid call with incompatable cols and vals.");
		StringBuilder builder = new StringBuilder( "DELETE FROM "+tableName+" ");
		for(int i = 0 ; i < cols.size() ; i ++){
			if(i > 0)
				builder.append(" AND ");
			else
				builder.append(" WHERE ");
			builder.append(cols.get(i));
			builder.append("='");
			builder.append(vals.get(i)+"'");
		}
		executeUpdate(builder.toString());
	}
	public void update(String tableName, List<String>cols, List<String>vals, String keyName, String keyValue) throws SQLException{
		if(cols.size() != vals.size())
			throw new RuntimeException("Invalid call with incompatable cols and vals.");
		StringBuilder builder = new StringBuilder( "UPDATE "+tableName+" SET ");
		for(int i = 0 ; i < cols.size() ; i ++){
			if(i > 0)
				builder.append(",");
			builder.append(cols.get(i));
			builder.append("='");
			builder.append(vals.get(i)+"'");
		}
		builder.append(" WHERE ");
		builder.append(keyName);
		builder.append("='");
		builder.append(keyValue+"'");
		executeUpdate(builder.toString());
	}
	public ResultSet select(String tableName, List<String>cols, List<String>vals) throws SQLException{
		if(cols.size() != vals.size())
			throw new RuntimeException("Invalid call with incompatable cols and vals.");
		StringBuilder builder = new StringBuilder( "SELECT * FROM "+tableName+" ");
		for(int i = 0 ; i < cols.size() ; i ++){
			if(i > 0)
				builder.append(" AND ");
			else
				builder.append(" WHERE ");
			builder.append(cols.get(i));
			builder.append("='");
			builder.append(vals.get(i)+"'");
		}
		return executeCommand(builder.toString());
	}
}
