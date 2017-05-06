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
	public Cart getCart(){
		return null;
	}
	public boolean isManager(){
		return Boolean.parseBoolean(getProperty("manager"));
	}
}
