package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class PortfolioViewController {
    @FXML
    private Label welcomeMsg;

    @FXML
    private Label date;

    @FXML
    private User currentUserName;

    public void setCurrentUserName(User userFullName) {
        this.currentUserName = userFullName;
        welcomeMsg.setText("Welcome " + userFullName.getUserFullName());
    }

    @FXML
    public void initialize() {
        date.setText(LocalDate.now().toString());
    }
}
