package controller;

import java.sql.SQLException;
import java.util.*;

import data.Book;
import data.Category;
import data.Order;
import data.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.BookModel;
import model.ReportModel;
import model.UserModel;
import view.BookView;

public class Controller {

    // private User dummyUser, dummyMngr;
    private User currUser;

    // cart
    private int totPrice;
    private Main mainWindow;
    private DashBoardController udController;

    ObservableList<BookView> cartBooks = FXCollections.observableArrayList();
    public CartController cartController;

    ObservableList<BookView> books = FXCollections.observableArrayList(); // current
                                                                          // books
                                                                          // in
                                                                          // the
                                                                          // search

    private Controller() {
        // dummyUser = new User();
        // dummyMngr = new User();
        // dummyUser.setProperty("username", "u");
        // dummyMngr.setProperty("username", "m");
        // dummyUser.setProperty("password", "123");
        // dummyMngr.setProperty("password", "012");
        // dummyMngr.setProperty("manager", "1");
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
    public User userLogin(String userName, String password)
            throws SQLException {
        // if (userName.equals(dummyUser.getUserName())
        // && password.equals(dummyUser.getPassword()))
        // return dummyUser;
        // if (userName.equals(dummyMngr.getUserName())
        // && password.equals(dummyMngr.getPassword()))
        // return dummyMngr;
        // return null;
        if (UserModel.checkUser(userName, password))
            return UserModel.getUser(userName);
        throw new SQLException("Invalid Credintials");
    }

    /**
     * @return the new User instance if added successfully or null if not add
     *         (exists or other errors)
     */
    public User userSignup(User user) throws Exception {
        UserModel.createUser(user);
        return user;
    }

    public void removeBook(Book book) throws Exception {
        if (book.getIsbn().isEmpty())
            throw new RuntimeException("Book ISBN must have value");
        try {
            BookModel.removeBook(book.getIsbn());
        } catch (SQLException e) {
            throw new RuntimeException("This book does not exsit");
        }
    }

    public List<Book> bookSearch(Book book) throws SQLException {
        // TODO use model to search the book and return list of results
        // List<Book>ret = new LinkedList<Book>();
        // book.setProperty("price", "100");
        // book.setProperty("copies", "200");
        // ret.add(book);
        // return ret;
        // System.out.println(book);
        List<String> keys = new ArrayList<String>();
        List<String> vals = new ArrayList<String>();
        String category = null;
        String author = null;

        if (book.getProperty("authors") != null)
            author = book.getProperty("authors");
        if (book.getProperty("category") != null)
            category = book.getProperty("category");
        if (book.getTitle() != null) {
            keys.add("title");
            vals.add(book.getTitle());
        }
        if (book.getProperty("publisher_name") != null) {
            keys.add("publisher_name");
            vals.add(book.getPublisher());
        }
        if (book.getProperty("isbn") != null) {
            keys.add("isbn");
            vals.add(book.getIsbn());
        }
        if (book.getProperty("publication_year") != null) {
            keys.add("publication_year");
            vals.add(book.getPublishYear());
        }
        return BookModel.getBooks(keys, vals, category, author);
        // return new ArrayList<Book>();
    }

    private boolean checkIfManager() throws SQLException {
        User user = UserModel.getUser(currUser.getUserName());
        return (user.isManager() == 1);
    }

    public boolean addBook(Book book) throws SQLException {
        // TODO Auto-generated method stub
        // don't forget to handle category number
        if (!checkIfManager())
            throw new SQLException(
                    "User doesn't have the required privileges.");
        return BookModel.createBook(book);
    }

    public boolean updateBook(Book book, String oldBookIsbn)
            throws SQLException {
        // TODO Auto-generated method stub
        // don't forget to handle category number
        if (!checkIfManager())
            throw new SQLException(
                    "User doesn't have the required privileges.");
        return BookModel.updateBook(book, oldBookIsbn);
    }

    public int getCartTotPrice() {
        return totPrice;
    }

    public int getCartSize() {
        return cartBooks.size();
    }

    public boolean placeOrder(Order order) throws SQLException {
        // TODO Auto-generated method stub
        if (!checkIfManager())
            throw new SQLException(
                    "User doesn't have the required privileges.");
        return BookModel.addOrder(order);
    }

    public boolean confirmOrder(Order order) throws SQLException {
        // TODO Auto-generated method stub
        if (!checkIfManager())
            throw new SQLException(
                    "User doesn't have the required privileges.");
        return BookModel.removeOrder(order.getBookIsbn());
    }


    public void removeFromCart(Book book) {
        int numberOfCopies = 0;
        int i = 0;
        for (BookView v : cartBooks) {
            if (v.getIsbnProb().getValue().equals(book.getIsbn())) {
                numberOfCopies = v.getRequestedProb().getValue();
                cartBooks.remove(i);
                break;
            }
            i++;
        }
        totPrice -= numberOfCopies * book.getPrice();
    }

    public boolean addToCart(Book book, int numberOfCopies) {
        BookView bkView = null;
        boolean found = false;
        for (BookView v : cartBooks) {
            if (v.getIsbnProb().getValue().equals(book.getIsbn())) {
                bkView = v;
                found = true;
            }
        }
        if (!found) {
            bkView = new BookView(book);
            cartBooks.add(bkView);
        }
        bkView.getRequestedProb().set(bkView.getRequestedProb().get()+numberOfCopies);
        totPrice += numberOfCopies * book.getPrice();
        return true;
    }

    public List<Category> getCategories() throws SQLException {
        return BookModel.getCategories();
    }
    public boolean addCreditCard(String cardNum, String passCode, String expDate, String balance) throws SQLException{
    	UserModel.createCreditCard(cardNum, passCode, currUser.getUserName(), expDate, balance);
    	return true;
    }
    /**
     * @throws Exception
     *             with error message to be shown in case of failure
     */
    public boolean checkOutCart(String cardNum, String pin) throws SQLException {
        if (cartBooks.size() == 0) {
            throw new SQLException("Cart is empty.");
        }
        List<String> isbns = new LinkedList<>();
        List<Integer> requests = new LinkedList<>();
        for(BookView bkView : cartBooks) {
            isbns.add(bkView.getIsbnProb().getValue());
            requests.add(bkView.getRequestedProb().getValue());
        }
        BookModel.checkOut(isbns, requests, currUser.getUserName(),
                cardNum, pin);
        cartBooks.clear();
        return true;
    }

    /**
     * @throws Exception
     *             with error message to be shown in case of failure
     */
    public void promoteUser(String userName) throws SQLException {
        if (!checkIfManager())
            throw new SQLException(
                    "User doesn't have the required privileges.");
        User user = UserModel.getUser(userName);
        user.setProperty("manager", "1");
        System.out.println(user);
        UserModel.updateUser(user, userName);
    }

    public User updateUser(User user) throws SQLException {
		user.setProperty("manager", ""+this.currUser.isManager());
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

    public void viewBookEdit() throws Exception {
        if (!checkIfManager())
            throw new RuntimeException(
                    "User doesn't have the required privileges.");
        this.mainWindow.switchToBookEdit();
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
                udController.setUser(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewOrders() {
        try {
            mainWindow.switchToOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Properties> getCards() throws SQLException {
        return UserModel.getCards(currUser.getUserName());
    }
    
    public List<Order> getOrders() throws Exception {
        return BookModel.getOrders();
    }
    
    public void addOrder(Order order) throws Exception {
        BookModel.addOrder(order);
    }

    public void viewCart() {
        try {
            mainWindow.switchToCart();
            this.cartController.setCart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showErrorDialogue(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        // alert.setContentText("I have a great message for you!");

        alert.showAndWait();
    }

    public void showConfirmDialogue(String title, String header,
            String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public void showConfirmDialogue(String title, String header) {
        showConfirmDialogue(title, header, "");
    }

    public void showConfirmDialogue(String header) {
        showConfirmDialogue("Confirm", header);
    }

    public void showErrorDialogue(String message) {
        showErrorDialogue("Error", message);
    }
    public void showReports(){
    	ReportModel.prepareReports();
    }

	public void viewCardScene() {
		 try {
            mainWindow.switchToCard();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}