package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupErrorController {

    @FXML
    private Label signUpError;

    @FXML
    private Label fillIn;

    @FXML
    public void switchToSignUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUp-view.fxml"));
        Parent root = fxmlLoader.load();

        SignUpViewController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonDesign.css").toExternalForm());
        Stage stage = (Stage) fillIn.getScene().getWindow();
        stage.setTitle("Sign UP");
        stage.setScene(scene);

    }

    @FXML
    public void fill(String message){
        fillIn.setText(message);
    }
}
