package data;

import java.util.Properties;

public class User extends Properties {

    public String getUserName() {
        return getProperty("username");
    }

    public String getPassword() {
        return getProperty("password");
    }

    public String getFirstName() {
        return getProperty("firstname");
    }

    public String getLastName() {
        return getProperty("lastname");
    }

    public String getEmail() {
        return getProperty("email");
    }

    public String getPhone() {
        return getProperty("phone");
    }

    public String getAddress() {
        return getProperty("address");
    }

    public String getCreated() {
        return getProperty("created");
    }

    public int isManager() {
        if (getProperty("manager") == null)
            return 0;
        return Integer.parseInt(getProperty("manager"));
    }
}
