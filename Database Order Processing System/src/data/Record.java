package data;

import java.util.Properties;

public abstract class Record {
	private Properties props = new Properties();
	
	protected final String getProperty(String propery){
		return props.getProperty(propery);
	}
	protected final void setProperty(String property, String value){
		props.setProperty(property, value);
	}
}
