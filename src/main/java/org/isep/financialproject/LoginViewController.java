package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    public void clearInfo(){
        usernameField.clear();
        passwordField.clear();
    }

}
