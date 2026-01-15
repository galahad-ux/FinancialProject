package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddPortofolioController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
