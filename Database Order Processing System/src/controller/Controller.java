package controller;

import java.util.LinkedList;
import java.util.List;

import data.Book;
import data.Cart;
import data.Order;
import data.User;
import view.Main;

public class Controller {

    private User dummyUser, dummyMngr;
    private User currUser;
    private Cart currCart;

    private Main mainWindow;
    private DashBoardController udController;

    private Controller() {
        dummyUser = new User();
        dummyMngr = new User();
        dummyUser.setProperty("username", "u");
        dummyMngr.setProperty("username", "m");
        dummyUser.setProperty("password", "123");
        dummyMngr.setProperty("password", "012");
        dummyMngr.setProperty("manager", "1");
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

    public Cart getCart() {
        return this.currCart;
    }

    /**
     * @return User instance if exists or null if not exists
     */
    public User userLogin(String userName, String password) throws Exception {
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
    public User userSignup(User user) throws Exception {
        return user;
    }

    public List<Book> bookSearch(Book book) {
        // TODO use model to search the book and return list of results
        return new LinkedList<Book>();
    }

    public boolean addBook(Book book) throws Exception {
        // TODO Auto-generated method stub
        // don't forget to handle category number
        return false;
    }

    public boolean updateBook(Book book) throws Exception {
        // TODO Auto-generated method stub
        // don't forget to handle category number
        return false;
    }

    public boolean placeOrder(Order order) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean confirmOrder(Order order) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean addToCart(Book book) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @throws Exception
     *             with error message to be shown in case of failure
     */
    public void checkOutCart() throws Exception {
        // TODO Auto-generated method stub
    }

    /**
     * @throws Exception
     *             with error message to be shown in case of failure
     */
    public void promoteUser(String userName) throws Exception {
        // TODO
    }

    /**
     * 
     * */
    public User updateUser(User user) throws RuntimeException {
        // TODO Update user in model if updated successfully return the user
        // object else throw exception with error message to display
        return user;
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
            if (user != null) {
                mainWindow.switchToMain(user);
                this.currUser = user;
                this.currCart = new Cart();
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

    public void viewCart() {
        // TODO
    }

    public void showErrorDialogue(String string, String message) {
        // TODO Auto-generated method stub
    }

    public void showErrorDialogue(String message) {
        showErrorDialogue("Error", message);
    }
}
