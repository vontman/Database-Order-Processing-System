package view;

import controller.Controller;
import controller.DashBoardController;
import data.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Scene dashboardScene;

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Controller.getInstance().setMainWindow(this);
        switchToLogin();
        primaryStage.show();
    }

    public void switchToLogin() throws Exception {
        if (primaryStage != null) {
            Parent root = FXMLLoader
                    .load(getClass().getResource("fx/UserLoginScene.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Book Shopping Login");
        }
    }

    public void switchToSignUp() throws Exception {
        if (this.primaryStage != null) {
            System.out.println("here");
            Parent root = FXMLLoader
                    .load(getClass().getResource("./fx/UserSignUpScene.fxml"));
            primaryStage.setTitle("Book Shopping Sign up");
            primaryStage.setScene(new Scene(root));
        }
    }

    public void switchToMain(User user) throws Exception {
        if (this.primaryStage != null) {
            if (dashboardScene == null) {
                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource("fx/UserDashboardScene.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                dashboardScene = new Scene(root);
                DashBoardController udController = fxmlLoader
                        .<DashBoardController> getController();
                Controller.getInstance().setDashBoardController(udController);
            }
            System.out.println(user.isManager());
            if (user.isManager()==1)
                primaryStage.setTitle("Book Shopping - Manager!");
            else
                primaryStage.setTitle("Book Shopping!");
            primaryStage.setScene(dashboardScene);
        }
    }

}