package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import data.Book;
import data.BookFactory;
import data.User;
import data.UserFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import view.BookView;

public class DashBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BookView> bookTableView;
    
    @FXML
    private TableColumn<BookView, String> titleCol, isbnCol, authorsCol, publisherCol, yearCol, categoryCol;
    
    @FXML
    private TableColumn<BookView, Integer> priceCol;

    @FXML
    private Button addBkBtn;

    @FXML
    private TextArea addressTA;

    @FXML
    private Button checkoutBtn;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField firstNameTF;

    @FXML
    private Button generateReportsBtn;

    @FXML
    private TextField itemsTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private Button logOutBtn;

    @FXML
    private TitledPane mngrPanel;

    @FXML
    private TextField newBkAuthorTF;

    @FXML
    private TextField newBkCategoryTF;

    @FXML
    private TextField newBkCopiesTF;

    @FXML
    private TextField newBkISBNTF;

    @FXML
    private TextField newBkPriceTF;

    @FXML
    private TextField newBkPublicationYearTF;

    @FXML
    private TextField newBkPublisherTF;

    @FXML
    private TextField newBkThresholdTF;

    @FXML
    private TextField newBkTitleTF;

    @FXML
    private Button ordersBtn;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField priceTF;

    @FXML
    private Button promotUserBtn;

    @FXML
    private TextField promotUserNameTF;

    @FXML
    private Button showCartBtn;

    @FXML
    private TextField srchBkAuthorTF;

    @FXML
    private ChoiceBox<?> srchBkCategoryCB;

    @FXML
    private TextField srchBkISBNTF;

    @FXML
    private TextField srchBkPublicationYearTF;

    @FXML
    private TextField srchBkPublisherTF;

    @FXML
    private TextField srchBkTitleTF;

    @FXML
    private Button srchBtn;

    @FXML
    private Label userNameLbl;

    @FXML
    private Button userSaveBtn;

    @FXML
    void initialize() {
        assert addBkBtn != null : "fx:id=\"addBkBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert addressTA != null : "fx:id=\"addressTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert checkoutBtn != null : "fx:id=\"checkoutBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert emailTF != null : "fx:id=\"emailTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert firstNameTF != null : "fx:id=\"firstNameTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert generateReportsBtn != null : "fx:id=\"generateReportsBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert itemsTF != null : "fx:id=\"itemsTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert lastNameTF != null : "fx:id=\"lastNameTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert mngrPanel != null : "fx:id=\"mngrPanel\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkAuthorTF != null : "fx:id=\"newBkAuthorTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkCategoryTF != null : "fx:id=\"newBkCategoryTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkCopiesTF != null : "fx:id=\"newBkCopiesTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkISBNTF != null : "fx:id=\"newBkISBNTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkPriceTF != null : "fx:id=\"newBkPriceTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkPublicationYearTF != null : "fx:id=\"newBkPublicationYearTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkPublisherTF != null : "fx:id=\"newBkPublisherTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkThresholdTF != null : "fx:id=\"newBkThresholdTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkTitleTF != null : "fx:id=\"newBkTitleTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert ordersBtn != null : "fx:id=\"ordersBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert passwordPF != null : "fx:id=\"passwordPF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert phoneTF != null : "fx:id=\"phoneTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert priceTF != null : "fx:id=\"priceTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert promotUserBtn != null : "fx:id=\"promotUserBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert promotUserNameTF != null : "fx:id=\"promotUserNameTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert showCartBtn != null : "fx:id=\"showCartBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkAuthorTF != null : "fx:id=\"srchBkAuthorTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkCategoryCB != null : "fx:id=\"srchBkCategoryCB\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkISBNTF != null : "fx:id=\"srchBkISBNTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkPublicationYearTF != null : "fx:id=\"srchBkPublicationYearTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkPublisherTF != null : "fx:id=\"srchBkPublisherTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkTitleTF != null : "fx:id=\"srchBkTitleTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBtn != null : "fx:id=\"srchBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert userNameLbl != null : "fx:id=\"userNameTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert userSaveBtn != null : "fx:id=\"userSaveBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";

        ctrl = Controller.getInstance();
    }

    private User currUser;
    private Controller ctrl;
    private ObservableList<BookView> books;

    public void setUser(User user) {
        this.currUser = user;
        userNameLbl.setText(user.getUserName());
        passwordPF.setText(user.getPassword());
        emailTF.setText(user.getProperty("email") == null ? ""
                : user.getProperty("email"));
        phoneTF.setText(user.getProperty("phone") == null ? ""
                : user.getProperty("phone"));
        firstNameTF.setText(
                user.getFirstName() == null ? "" : user.getFirstName());
        lastNameTF
                .setText(user.getLastName() == null ? "" : user.getLastName());
        lastNameTF
                .setText(user.getLastName() == null ? "" : user.getLastName());
        addressTA.setText(user.getProperty("address") == null ? ""
                : user.getProperty("address"));

        setManagerView(user.isManager() == 1);
        
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getTitleProb());
        isbnCol.setCellValueFactory(cellData -> cellData.getValue().getIsbnProb());
        authorsCol.setCellValueFactory(cellData -> cellData.getValue().getAuthorsProb());
        publisherCol.setCellValueFactory(cellData -> cellData.getValue().getPublisherProb());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().getYearProb());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProb());
        bookTableView.setItems(books);
    }

    public void setManagerView(boolean isManager) {
        mngrPanel.setExpanded(isManager);
        mngrPanel.setVisible(isManager);

        // TODO remove copies, threshold ... from table
        // TODO make table editable for update
    }

    public void showBooks(List<Book> books) {
        // TODO show list of book inside table view
    }

    @FXML
    protected void handleLogOutAction(ActionEvent e) {
        this.ctrl.viewUserLogin();
    }

    @FXML
    protected void handleSaveUserAction(ActionEvent e) {
        try {
            UserFactory userFactroy = new UserFactory();
            userFactroy.setUserName(currUser.getUserName());
            userFactroy.setPassword(passwordPF.getText());
            userFactroy.setEmail(emailTF.getText());
            userFactroy.setPhone(phoneTF.getText());
            userFactroy.setFirstName(firstNameTF.getText());
            userFactroy.setLastName(lastNameTF.getText());
            userFactroy.setAddress(addressTA.getText());

            User newUser = userFactroy.getUser();
            Validator.validateUserName(newUser.getUserName());
            Validator.validatePassword(newUser.getPassword());
            Validator.validateEmail(newUser.getProperty("email"));
            if (!newUser.getProperty("phone").isEmpty())
                Validator.validatePhone(newUser.getProperty("phone"));

            newUser = this.ctrl.updateUser(newUser);
            this.currUser = newUser;
        } catch (Exception ex) {
            ctrl.showErrorDialogue("Error", ex.getMessage());
        }
    }

    @FXML
    protected void handleBkSearchBtnAction(ActionEvent e) {
        BookFactory bkFactory = new BookFactory();
        bkFactory.setIsbn(srchBkISBNTF.getText());
        bkFactory.setTitle(srchBkISBNTF.getText());
        bkFactory.setAuthors(srchBkAuthorTF.getText());
        System.out.println(srchBkCategoryCB.getValue().toString());
        bkFactory.setCategory(srchBkCategoryCB.getValue().toString());
        bkFactory.setPublisher(srchBkPublisherTF.getText());
        bkFactory.setPublishYear(srchBkPublicationYearTF.getText());

        showBooks(this.ctrl.bookSearch(bkFactory.getBook()));
    }

    @FXML
    protected void handleShowCartBtnAction(ActionEvent e) {
        ctrl.viewCart();
    }

    @FXML
    protected void handleCheckoutBtnAction(ActionEvent e) {
        try {
            // TODO credit card
            ctrl.checkOutCart("");
            // TODO success
        } catch (Exception ex) {
            // TODO failure
            ctrl.showErrorDialogue("Error", ex.getMessage());
        }
    }

    @FXML
    protected void handlePromotUserBtnAction(ActionEvent e) {
        try {
            ctrl.promoteUser(promotUserNameTF.getText());
        } catch (Exception ex) {
            ctrl.showErrorDialogue(ex.getMessage());
        }
    }

    @FXML
    protected void handleOrdersBtnAction(ActionEvent e) {
        try {
            ctrl.viewOrders();
        } catch (Exception ex) {
            ctrl.showErrorDialogue(ex.getMessage());
        }
    }

    @FXML
    protected void handleGenerateReportsBtnAction(ActionEvent e) {
        try {
            ctrl.viewOrders();
        } catch (Exception ex) {
            ctrl.showErrorDialogue(ex.getMessage());
        }
    }

    @FXML
    protected void handleAddBkBtnAction(ActionEvent e) {
        try {
            BookFactory bkFactory = new BookFactory();
            bkFactory.setTitle(newBkTitleTF.getText());
            bkFactory.setAuthors(newBkAuthorTF.getText());
            bkFactory.setCategory(newBkCategoryTF.getText());
            bkFactory.setPublisher(newBkPublisherTF.getText());
            bkFactory.setPublishYear(newBkPublicationYearTF.getText());
            bkFactory.setIsbn(newBkISBNTF.getText());
            bkFactory.setPrice(newBkPriceTF.getText());
            bkFactory.setCopies(newBkCopiesTF.getText());

            ctrl.addBook(bkFactory.getBook());
        } catch (Exception ex) {
            ctrl.showErrorDialogue(ex.getMessage());
        }
    }
}
