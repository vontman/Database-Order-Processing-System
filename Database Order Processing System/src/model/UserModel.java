package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.User;

public class UserModel {
	/*
	 * 
	 * testing
	 * DONE : craeteuser
	 * DONE : craete credit
	 * DONE : get user
	 * DONE: get usrss
	 * DONE check User
	 * DONE : check card
	 * DONE: update user
	 * DONE :update credit
	 * DONE: remove user
	 */
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
		cols.add("email");
		vals.add(user.getEmail());
		cols.add("phone");
		vals.add(user.getPhone());
		cols.add("address");
		vals.add(user.getAddress());
		cols.add("created");
		vals.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			
		model.insert("USER", cols, vals);
		System.out.println("User added successfully.");
		return true;
	}
	public static boolean createCreditCard(String cardNum, String passCode, String username, String expDate, int balance) throws SQLException {
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("number");
		vals.add(cardNum);
		cols.add("pin");
		vals.add(passCode);
		cols.add("username");
		vals.add(username);
		cols.add("balance");
		vals.add(""+balance);
		cols.add("expirationdate");
		vals.add(expDate);
		model.insert("creditcard", cols, vals);
		System.out.println("card added successfully.");
		return true;
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
			user.setProperty("email", result.getString("email"));
			user.setProperty("manager", result.getString("manager"));
			user.setProperty("phone", result.getString("phone"));
			user.setProperty("address", result.getString("address"));
			user.setProperty("created", result.getString("created"));
			return user;
		}else{
			throw new SQLException("User not found");
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
			user.setProperty("email", result.getString("email"));
			user.setProperty("phone", result.getString("phone"));
			user.setProperty("address", result.getString("address"));
			user.setProperty("created", result.getString("created"));
			users.add(user);
		}
		return users;
		
	}
	public static boolean checkUser(String username, String password) throws SQLException{
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		
		cols.add("username");
		vals.add(username);
		cols.add("password");
		vals.add(password);
		ResultSet result = model.select("USER", cols, vals);
		if(result.next()){
			return true;
		}else{
			return false;
		}
	}
	public static int checkCreditCard (String number, int price, String username) throws SQLException {
		List<String> cols = new ArrayList<String>();
		List<String> vals = new ArrayList<String>();
		cols.add("Number");
		vals.add(number);
		cols.add("username");
		vals.add(username);
		ResultSet result = model.select("creditCard", cols, vals);
		if(!result.next()){
			throw new SQLException("Card not found.");
		}
		int balance = Integer.parseInt(result.getString("Balance"));
		if (balance < price) {
			throw new SQLException("Balance not enough.");
		}
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(result.getString("expirationdate").compareTo(date) < 0){
			throw new SQLException("Card is expired.");
		}
		return result.getInt("balance");
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
		cols.add("manager");
		vals.add(""+user.isManager());
		cols.add("email");
		vals.add(user.getEmail());
		cols.add("phone");
		vals.add(user.getPhone());
		cols.add("address");
		vals.add(user.getAddress());
		
		try {
			model.update("USER", cols, vals, "username", userName);
			System.out.println("User updated successfully.");
			return true;
		} catch (SQLException e) {
			System.out.println("User not updated, Error : "+e.getMessage());
			return false;
		}
	}
	public static boolean updateCreditCard(String cardNum, int newBalance) throws SQLException {
		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		cols.add("balance");
		vals.add(""+newBalance);
		model.update("creditcard", cols, vals, "number", cardNum);
		System.out.println("Card updated successfully.");
		return true;
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
