package data;

public class UserFactory {
    private User user = new User();
    
    public UserFactory() {
        // default values
        setIsManager(false);
    }
    
    public void setUserName(String val) {
        user.setProperty("username", val);
    }

    public void setPassword(String val) {
        user.setProperty("password", val);
    }

    public void setFirstName(String val) {
        user.setProperty("firstname", val);
    }

    public void setLastName(String val) {
        user.setProperty("lastname", val);
    }

    public void setEmail(String val) {
        user.setProperty("email", val);
    }

    public void setPhone(String val) {
        user.setProperty("phone", val);
    }

    public void setAddress(String val) {
        user.setProperty("address", val);
    }

    public void setCreated(String val) {
        user.setProperty("created", val);
    }

    public void setIsManager(boolean val) {
        setIsManager(val ? 1 : 0);
    }

    public void setIsManager(int val) {
        user.setProperty("manager", Integer.toString(val));
    }
    
    public User getUser() {
        return user;
    }
}
