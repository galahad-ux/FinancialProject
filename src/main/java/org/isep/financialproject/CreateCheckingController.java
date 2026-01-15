package org.isep.financialproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCheckingController {
    @FXML private ChoiceBox<String> typeField;
    @FXML private TextField initialAmountField;

    @FXML
    public void initialize() {
        typeField.getItems().setAll("Checking", "Savings");
        typeField.setValue("Checking");
    }

    @FXML
    private void createAccount() {
        double balance;
        try{
            balance = Double.parseDouble(initialAmountField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Balance must be a number").showAndWait();
            return;
        }

        String type = typeField.getValue();
        Currency c = LoggedInUser.currentUser.getPreferredCurrency();

        BankAccount account;
        if (type.equals("Checking")) {
            account = new CheckingAccount("Checking", "Auto", c, "ACC-" + System.currentTimeMillis(),
                    balance, 999999, 10000);
        } else {
            account = new SavingsAccount("Savings", "Auto", c, "ACC-" + System.currentTimeMillis(),
                    balance, 999999, 0);
        }

        LoggedInUser.currentUser.addPortfolio(account);
        ((Stage) initialAmountField.getScene().getWindow()).close();
    }
}
