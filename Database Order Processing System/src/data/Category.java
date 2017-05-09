package data;

import java.util.Properties;

public class Category extends Properties{
	public int getId(){
		return Integer.parseInt(getProperty("id"));
	}
	public String getType(){
		return getProperty("type");
	}
	@Override
	public String toString(){
		return getType();
	}
}
