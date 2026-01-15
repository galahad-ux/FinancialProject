package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CreateCheckingController {
    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private TextField accNumField;
    @FXML private TextField initialAmountField;
    @FXML private TextField withdrawLimitField;
    @FXML private TextField spendLimitField;
    @FXML private Label messageLabel;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void handleCreate() {
        try {
            if (user == null) {
                messageLabel.setText("Error: no logged-in user.");
                return;
            }

            String name = nameField.getText();
            String description = descriptionField.getText();
            String accNum = accNumField.getText();

            double initialAmount = Double.parseDouble(initialAmountField.getText());
            double withdrawLimit = Double.parseDouble(withdrawLimitField.getText());
            double spendLimit = Double.parseDouble(spendLimitField.getText());

            user.createCheckingAccount(name, description, accNum, initialAmount, withdrawLimit, spendLimit);

            goToBankView();

        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter valid numbers.");
        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/isep/financialproject/ChooseBankAccount.fxml"));
            Parent root = loader.load();

            ChooseBankAccountController controller = loader.getController();
            controller.setUser(user);

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }

    private void goToBankView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/isep/financialproject/Bank-view.fxml"));
        Parent root = loader.load();

        BankViewController controller = loader.getController();
        controller.setUser(user);

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
