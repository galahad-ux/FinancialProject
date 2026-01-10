package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginErrorController {
    @FXML
    private Label LoginError;

    @FXML
    private Label fillIn;

    @FXML
    public void fill(String message){
        fillIn.setText(message);
    }
}
