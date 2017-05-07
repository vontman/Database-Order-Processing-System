package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.User;

public class UserModel {
	private static Model model = Model.instance;
	public static boolean createUser(User user) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("firstname");
		vals.add(user.getFirstName());
		cols.add("lastname");
		vals.add(user.getLastName());
		cols.add("username");
		vals.add(user.getUserName());
		cols.add("password");
		vals.add(user.getPassword());
		cols.add("manager");
		vals.add("0");
		cols.add("created");
		vals.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			
		model.insert("USER", cols, vals);
		System.out.println("User added successfully.");
		return true;
	}
	
	public static boolean updateUser(User user, String userName){
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("firstname");
		vals.add(user.getFirstName());
		cols.add("lastname");
		vals.add(user.getLastName());
		cols.add("username");
		vals.add(user.getUserName());
		cols.add("password");
		vals.add(user.getPassword());
		
		try {
			model.update("USER", cols, vals, "username", userName);
			System.out.println("User updated successfully.");
			return true;
		} catch (SQLException e) {
			System.out.println("User not updated, Error : "+e.getMessage());
			return false;
		}
	}
	public static User getUser(String userName) throws SQLException{

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		
		cols.add("username");
		vals.add(userName);
		User user = new User();
		ResultSet result = model.select("USER", cols, vals);
		if(result.next()){
			user.setProperty("username", result.getString("username"));
			user.setProperty("firstname", result.getString("firstname"));
			user.setProperty("lastname", result.getString("lastname"));
			user.setProperty("password", result.getString("password"));
			return user;
		}else{
			System.out.println("User not found");
			return null;
		}
	}
	public static List<User> getUsers() throws SQLException{

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		
		ResultSet result = model.select("USER", cols, vals);
		List<User>users = new ArrayList<User>();
		while(result.next()){
			User user = new User();
			user.setProperty("username", result.getString("username"));
			user.setProperty("firstname", result.getString("firstname"));
			user.setProperty("lastname", result.getString("lastname"));
			user.setProperty("password", result.getString("password"));
			users.add(user);
		}
		return users;
		
	}

	public static boolean removeUser(String username) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("username");
		vals.add(username);
		
		model.delete("USER", cols, vals);
		System.out.println("user deleted successfully.");
		return true;
	}
}
