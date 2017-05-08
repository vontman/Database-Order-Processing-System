package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label errorMsgLbl;

    @FXML
    private Button logInBtn;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private TextField passwordTF;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField userNameTF;


    @FXML
    void initialize() {
        assert errorMsgLbl != null : "fx:id=\"errorMsgLbl\" was not injected: check your FXML file 'UserLoginScene.fxml'.";
        assert logInBtn != null : "fx:id=\"logInBtn\" was not injected: check your FXML file 'UserLoginScene.fxml'.";
        assert passwordPF != null : "fx:id=\"passwordPF\" was not injected: check your FXML file 'UserLoginScene.fxml'.";
        assert passwordTF != null : "fx:id=\"passwordTF\" was not injected: check your FXML file 'UserLoginScene.fxml'.";
        assert signUpBtn != null : "fx:id=\"signUpBtn\" was not injected: check your FXML file 'UserLoginScene.fxml'.";
        assert userNameTF != null : "fx:id=\"userNameTF\" was not injected: check your FXML file 'UserLoginScene.fxml'.";
    }
    
    @FXML
    protected void handleSignUpBtnAction(ActionEvent e) {
        
    }
    
    @FXML
    protected void handleSignUpBtnAction(ActionEvent e) {
        
    }

}
