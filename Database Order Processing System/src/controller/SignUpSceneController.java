package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Validator.ValidationException;
import data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SignUpSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea addressTA;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField userNameTF;

    @FXML
    private Label errorMsgLbl;

    private Controller ctrl;

    @FXML
    void initialize() {
        assert addressTA != null : "fx:id=\"addressTA\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert emailTF != null : "fx:id=\"emailTF\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert firstNameTF != null : "fx:id=\"firstNameTF\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert lastNameTF != null : "fx:id=\"lastNameTF\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert passwordPF != null : "fx:id=\"passwordPF\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert passwordTF != null : "fx:id=\"passwordTF\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert phoneTF != null : "fx:id=\"phoneTF\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert signUpBtn != null : "fx:id=\"signUpBtn\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";
        assert userNameTF != null : "fx:id=\"userNameTF\" was not injected: check your FXML file 'UserSignUpScene.fxml'.";

        errorMsgLbl.setText("");
        ctrl = Controller.getInstance();
    }

    @FXML
    protected void handleSignUpBtnAction(ActionEvent e) {
        try {
            User user = new User();
            user.setProperty("username", userNameTF.getText());
            user.setProperty("password", passwordPF.getText());
            user.setProperty("email", emailTF.getText());
            user.setProperty("phone", phoneTF.getText());
            user.setProperty("firstname", firstNameTF.getText());
            user.setProperty("lastname", lastNameTF.getText());
            user.setProperty("address", addressTA.getText());
            
            Validator.validateUserName(user.getUserName());
            Validator.validatePassword(user.getPassword());
            Validator.validateEmail(user.getEmail());
            if(!phone.isEmpty())
                Validator.validatePhone(phone);

            
            User user = ctrl.userSignup();
            if (user == null) {
                errorMsgLbl.setText("This user name already exists!");
            } else {
                ctrl.viewUserDashBoard(user);
            }
        } catch (ValidationException ex) {
            errorMsgLbl.setText(ex.getMessage());
        }
    }

    @FXML
    protected void handleLogInBtnAction(ActionEvent e) {
        try {
            ctrl.viewUserLogin();
        } catch (Exception ex) {

        }
    }
}
