package data;

import java.util.Properties;

public class User extends Properties{
	public User() {
		
	}
	public String getUserName(){
		return getProperty("username");
	}
	public String getPassword(){
		return getProperty("password");
	}
	public String getFirstName(){
		return getProperty("firstname");
	}
	public String getLastName(){
		return getProperty("lastname");
	}
	public String getEmail(){
		return getProperty("email");
	}
	public String getPhone(){
		return getProperty("phone");
	}
	public String getAddress(){
		return getProperty("address");
	}
	public String getCreated(){
		return getProperty("created");
	}
	public Cart getCart(){
		return null;
	}
	public int isManager(){
		return Integer.parseInt(getProperty("manager"));
	}
}
