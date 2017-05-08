package controller;

import java.util.List;

import data.Book;
import data.Cart;
import data.Order;
import data.User;
import view.Main;

public class Controller {

    private User dummyUser, dummyMngr;
    private User currUser;

    private Main mainWindow;
    private DashBoardController udController;

    private Controller() {
        dummyUser = new User();
        dummyMngr = new User();
        dummyUser.setProperty("username", "u");
        dummyMngr.setProperty("username", "m");
        dummyUser.setProperty("password", "123");
        dummyMngr.setProperty("password", "012");
        dummyMngr.setProperty("manager", "true");
    }

    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public void setMainWindow(Main window) {
        this.mainWindow = window;
    }

    public void setDashBoardController(DashBoardController udController) {
        this.udController = udController;
    }

    /**
     * @return User instance if exists or null if not exists
     */
    public User userLogin(String userName, String password) {
        if (userName.equals(dummyUser.getUserName())
                && password.equals(dummyUser.getPassword()))
            return dummyUser;
        if (userName.equals(dummyMngr.getUserName())
                && password.equals(dummyMngr.getPassword()))
            return dummyMngr;
        return null;
    }

    /**
     * @return the new User instance if added successfully or null if not add
     *         (exists or other errors)
     */
    public User userSignup(User user) {
        return user;
    }

    public List<Book> bookSearch(Book book) {
        return null;
    }

    public boolean addBook(Book book) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean updateBook(Book book) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean placeOrder(Order order) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean confirmOrder(Order order) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean addToCart(Book book) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean checkOutCart(Cart cart) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean promoteUser(User user) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * Controller to View part
     */
    public void viewUserLogin() {
        try {
            mainWindow.switchToLogin();
            this.currUser = null;
        } catch (Exception e) {

        }
    }

    public void viewUserSignup() {
        try {
            mainWindow.switchToSignUp();
            this.currUser = null;
        } catch (Exception e) {

        }
    }

    public void viewUserDashBoard() {
        if (this.currUser != null)
            viewUserDashBoard(currUser);
    }

    public void viewUserDashBoard(User user) {
        try {
            if(user != null) {
                mainWindow.switchToMain(user);
                this.currUser = user;
                udController.setUser(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewOrders() {
        // TODO
    }

    public void viewConfirmOrder() {
        // TODO
    }

}
