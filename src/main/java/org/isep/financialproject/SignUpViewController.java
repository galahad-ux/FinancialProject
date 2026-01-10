package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignUpViewController {

    @FXML
    private TextField fullName;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField password;

    @FXML
    private TextField retypedPw;

    public void clearInfo(){
        fullName.clear();
        userEmail.clear();
        password.clear();
        retypedPw.clear();
    }
}
