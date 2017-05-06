package data;

public class User extends Record{
	public User() {
		
	}
	public String getUserName(){
		return getProperty("username");
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
