package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import data.Book;
import data.Cart;
import data.Category;
import data.Order;
import data.User;
import model.BookModel;
import model.UserModel;
import view.Main;

public class Controller {

    private User dummyUser, dummyMngr;
    private User currUser;
    // cart
    private List<String> currBooks; // stores isbn of each book in the cart
    private List<Integer> currCopies; // stores the number of ordered copies for each book

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
        UserModel.createUser(user);
        return user;
    }
    public List<Book> bookSearch(Book book) {
        // TODO use model to search the book and return list of results
        return new LinkedList<Book>();
    }
    private boolean checkIfManager() throws SQLException{
    	User user = UserModel.getUser(currUser.getUserName());
    	return (user.isManager() == 1);
    }
    public boolean addBook(Book book) throws Exception {
        // TODO Auto-generated method stub
        // don't forget to handle category number
    	if(!checkIfManager())
    		throw new SQLException("User doesn't have the required privileges.");
        return BookModel.createBook(book);
    }

    public boolean updateBook(Book book, String oldBookIsbn) throws SQLException {
        // TODO Auto-generated method stub
        // don't forget to handle category number
    	if(!checkIfManager())
    		throw new SQLException("User doesn't have the required privileges.");
        return BookModel.updateBook(book, oldBookIsbn);
    }

    public boolean placeOrder(Order order) throws SQLException {
        // TODO Auto-generated method stub
    	if(!checkIfManager())
    		throw new SQLException("User doesn't have the required privileges.");
        return BookModel.addOrder(order);
    }
    public boolean confirmOrder(Order order) throws SQLException {
        // TODO Auto-generated method stub
    	if(!checkIfManager())
    		throw new SQLException("User doesn't have the required privileges.");
        return BookModel.removeOrder(order.getBookIsbn());
    }

    public boolean addToCart(Book book, int numberOfCopies) throws SQLException {
        currBooks.add(book.getIsbn());
        currCopies.add(numberOfCopies);
        return true;
    }

	public List<Category> getCategories() throws SQLException{
		return BookModel.getCategories();
	}

    /**
     * @throws Exception
     *             with error message to be shown in case of failure
     */
    public boolean checkOutCart(String cardNum) throws SQLException {
        // TODO Auto-generated method stub
    	BookModel.checkOut(currBooks, currCopies, currUser.getUserName(), cardNum);
    	currBooks.clear();
    	currCopies.clear();
    	return true;
    }

    /**
     * @throws Exception
     *             with error message to be shown in case of failure
     */
    public void promoteUser(String userName) throws SQLException {
        // TODO
    	if(!checkIfManager())
    		throw new SQLException("User doesn't have the required privileges.");
        User user = UserModel.getUser(userName);
        user.setProperty("manager", "1");
        UserModel.updateUser(user, userName);
    }
    public User updateUser(User user) throws SQLException {
        // TODO Update user in model if updated successfully return the user
        // object else throw exception with error message to display
        UserModel.updateUser(user, currUser.getUserName());
        this.currUser = user; // user updated
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
                currBooks = new ArrayList<String>();
                currCopies = new ArrayList<Integer>();
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
