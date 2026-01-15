package org.isep.financialproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateSavingsController {
    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private TextField accNumField;
    @FXML private TextField initialAmountField;
    @FXML private TextField withdrawLimitField;
    @FXML private Label messageLabel;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

//    @FXML
//    private void handleCreate(ActionEvent event) {
//        try {
//            if (user == null) {
//                messageLabel.setText("Error: no logged-in user.");
//                return;
//            }
//
//            String name = nameField.getText();
//            String description = descriptionField.getText();
//            String accNum = accNumField.getText();
//
//            double initialAmount = Double.parseDouble(initialAmountField.getText());
//            double withdrawLimit = Double.parseDouble(withdrawLimitField.getText());
//
//            user.createSavingsAccount(name, description, accNum, initialAmount, withdrawLimit);
//
//            messageLabel.setText("Savings account created!");
//
//
//        } catch (NumberFormatException e) {
//            messageLabel.setText("Please enter valid numbers.");
//        } catch (Exception e) {
//            messageLabel.setText("Error: " + e.getMessage());
//        }
//    }
}
