package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    public void clearInfo(){
        emailField.clear();
        passwordField.clear();
    }

    //Opening sign up Window
    @FXML
    public void switchToSignUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup-view.fxml"));
        Parent root = fxmlLoader.load();

        SignUpViewController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonDesign.css").toExternalForm());
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.setTitle("Sign UP");
        stage.setScene(scene);
    }

    @FXML
    public void openPortfolioWindow(String userEmail) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Portfolio-view.fxml"));
        Parent root = fxmlLoader.load();

        PortfolioViewController controller = fxmlLoader.getController();
        User user = User.checkEmail(userEmail);
        if(user != null){
            controller.setCurrentUserName(user);
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.setTitle("Your Account");
        stage.setScene(scene);
    }

    @FXML
    public void showError(String msg) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginError.fxml"));
        Parent root = fxmlLoader.load();

        LoginErrorController controller = fxmlLoader.getController();
        controller.fill(msg);

        Stage stage = new Stage();
        stage.setTitle("Error");
        stage.setScene(new Scene(root));

        stage.show();
    }

    @FXML
    private void login(){
        String userEmail = emailField.getText();
        String userPassword = passwordField.getText();

        if(userEmail.isEmpty() || userPassword.isEmpty()){
            try{
                showError("Please fill in all the fields");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (!User.authenticate(userEmail,userPassword)) {
            try {
                showError("Login failed! Please try again");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            try{
                openPortfolioWindow(userEmail);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
