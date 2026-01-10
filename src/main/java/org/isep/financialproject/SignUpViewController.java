package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
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
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonDesign.css").toExternalForm());
        Stage stage = (Stage) fullName.getScene().getWindow();
        stage.setTitle("Error");
        stage.setScene(scene);
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

//        if (User.register(userFullName,userEmail,userName,userPassword, preferredCurrency)){
//            try {
//                popUp();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
