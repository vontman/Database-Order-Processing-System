package controller;

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

    private Scene dashboardScene, cartScene;

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
                    .load(getClass().getResource("../view/fx/UserLoginScene.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Book Shopping Login");
        }
    }

    public void switchToSignUp() throws Exception {
        if (this.primaryStage != null) {
            Parent root = FXMLLoader
                    .load(getClass().getResource("../view/fx/UserSignUpScene.fxml"));
            primaryStage.setTitle("Book Shopping Sign up");
            primaryStage.setScene(new Scene(root));
        }
    }

    public void switchToMain(User user) throws Exception {
        if (this.primaryStage != null) {
            if (dashboardScene == null) {
                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource("../view/fx/UserDashboardScene.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                dashboardScene = new Scene(root);
                DashBoardController udController = fxmlLoader
                        .<DashBoardController> getController();
                Controller.getInstance().setDashBoardController(udController);
            }
            System.out.println(user.isManager());
            if (user.isManager() == 1)
                primaryStage.setTitle("Book Shopping - Manager!");
            else
                primaryStage.setTitle("Book Shopping!");
            primaryStage.setScene(dashboardScene);
        }
    }

    public void switchToBookEdit() throws Exception {
        if (this.primaryStage != null) {
            Parent root = FXMLLoader.load(getClass()
                    .getResource("../view/fx/ManagerBookDashboardScene.fxml"));
            primaryStage.setTitle("Book Manging Dashboard");
            primaryStage.setScene(new Scene(root));
        }
    }
    
    public void switchToCart() throws Exception {
        if (this.primaryStage != null) {
            if (cartScene == null) {
                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource("../view/fx/CartScene.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                cartScene = new Scene(root);
                CartController cartController = fxmlLoader
                        .<CartController> getController();
                Controller.getInstance().cartController = cartController;
            }
            primaryStage.setTitle("Book Shopping!");
            primaryStage.setScene(cartScene);
        }
    }
    
    public void switchToOrders() throws Exception {
        if (this.primaryStage != null) {
            Parent root = FXMLLoader.load(getClass()
                    .getResource("../view/fx/OrdersScene.fxml"));
            primaryStage.setTitle("Book Orders Dashboard");
            primaryStage.setScene(new Scene(root));
        }
    } 
    public void switchToCard() throws Exception{

        if (this.primaryStage != null) {
            Parent root = FXMLLoader.load(getClass()
                    .getResource("../view/fx/CardScene.fxml"));
            primaryStage.setTitle("Credit Cards Dashboard");
            primaryStage.setScene(new Scene(root));
        }
    }
}