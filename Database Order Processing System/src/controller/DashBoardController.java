package controller;

import data.User;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class DashBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBkBtn;

    @FXML
    private TextArea addressTF;

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
        assert addressTF != null : "fx:id=\"addressTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
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

    }

    private User currUser;

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
        addressTF.setText(user.getProperty("address") == null ? ""
                : user.getProperty("address"));

        mngrPanel.setExpanded(user.isManager());
        mngrPanel.setVisible(user.isManager());
    }

}
