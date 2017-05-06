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
	public static void createUser(User user){
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
		cols.add("created");
		vals.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		try {
			model.insert("USER", cols, vals);
			System.out.println("User added successfully.");
		} catch (SQLException e) {
			System.out.println("User not added, Error : "+e.getMessage());
		}
	}
	
	public static void updateUser(User user, String userName){
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
		} catch (SQLException e) {
			System.out.println("User not updated, Error : "+e.getMessage());
		}
	}
	public static User getUser(String userName){

		List<String>cols = new ArrayList<String>();
		List<String>vals= new ArrayList<String>();
		
		cols.add("username");
		vals.add(userName);
		
		try{
			ResultSet result = model.select("USER", cols, vals);
			
		}catch(SQLException e){
			System.out.println("ERROR finding user :"+e.getMessage());
		}
		return null;
	}
}
