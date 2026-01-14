package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.isep.financialproject.User.emailExists;

public class SignUpViewController {

    @FXML
    private TextField fullName;

    @FXML
    private TextField email;

    @FXML
    private MenuButton preferredCurrency;
    private Currency selectedCurrency;

    @FXML
    private TextField password;

    @FXML
    private TextField retypedPw;

    public void clearInfo(){
        fullName.clear();
        email.clear();
        preferredCurrency = null;
        password.clear();
        retypedPw.clear();
    }

    //Error popup window
    @FXML
    public void signUpError(String message) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignupError.fxml"));
        Parent root = fxmlLoader.load();

        SignupErrorController controller = fxmlLoader.getController();
        controller.fill(message);

        Stage stage = new Stage();
        stage.setTitle("Error");
        stage.setScene(new Scene(root));

        stage.show();
    }

    //Currency initializing
    @FXML
    public void initialize(){
        preferredCurrency.getItems().clear();
        for (Currency c: Currency.values()){
            MenuItem option = new MenuItem(c.name());

            option.setOnAction(actionEvent -> {
                preferredCurrency.setText(c.name());
                this.selectedCurrency = c;
            });

            preferredCurrency.getItems().add(option);
        }
    }

    //Sign up authentication
    @FXML
    private void signUp(){
        String userFullName = fullName.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();
        String userConfirmPassword = retypedPw.getText();

        if (userFullName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()){
            try {
                signUpError("Please fill in all the fields");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (!userPassword.equals(userConfirmPassword)){
            try {
                signUpError("Passwords don't match");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (emailExists(userEmail)){
            try {
                signUpError("Email already exists!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (User.register(userFullName,userEmail,userPassword, selectedCurrency)){
            //success alert
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Successful");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Success! You may proceed to Login");
            successAlert.show();

            try {
                BackToLogin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //switching back to log in button
    @FXML
    public void BackToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Parent root = fxmlLoader.load();

        LoginViewController controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonDesign.css").toExternalForm());
        Stage stage = (Stage) fullName.getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);

    }
}
