package data;

import java.util.ArrayList;
import java.util.List;

public class Book extends Record{
	public String getIsbn(){
		return getProperty("isbn");
	}
	public String getTitle(){
		return getProperty("title");
	}
	public int getPrice(){
		return Integer.parseInt(getProperty("price"));
	}
	public int getCopies(){
		return Integer.parseInt(getProperty("copies"));
	}
	public String getPublishYear(){
		return getProperty("publication_year");
	}
	public String getCategory(){
		return getProperty("category");
	}
	public int getThreshold(){
		return Integer.parseInt(getProperty("threshold"));
	}
	public List<String> getAuthors(){
		//TODO
		return new ArrayList<String>();
	}
	public String getPublisher(){
		return getProperty("publisher_name");
	}
}
