package org.isep.financialproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;

public class TransferViewController {

    @FXML
    private ComboBox<CheckingAccount> accountComboBox;

    @FXML
    private TextField beneficiaryNameField;

    @FXML
    private TextField accountNoField;

    @FXML
    private TextField amountField;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField purposeField;

    @FXML
    private Label msg;

    @FXML
    public void initialize() {
        dateLabel.setText(LocalDate.now().toString());

        //only transfer from checkings
        accountComboBox.setItems(FXCollections.observableArrayList());
        for (Portfolio p: LoggedInUser.currentUser.getPortfolios()){
            if (p instanceof CheckingAccount c){
                accountComboBox.getItems().add(c);
            }
        }

        //showing balance in combobox
        accountComboBox.setConverter(new StringConverter<CheckingAccount>() {
            @Override
            public String toString(CheckingAccount checkingAccount) {
                if (checkingAccount == null) return "";
                return "Balance: " + String.format("%.2f",checkingAccount.getBalance());
            }

            @Override
            public CheckingAccount fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    private void transfer(){
        CheckingAccount acc = accountComboBox.getValue();
        if (acc == null){
            msg.setText("Please select a checking account");
            return;
        }

        String beneficiary = beneficiaryNameField.getText();
        String receiverAcc = accountNoField.getText();
        String amt = amountField.getText();

        if (beneficiary.isEmpty() || receiverAcc.isEmpty() || amt.isEmpty()){
            msg.setText("Please fill in all the fields");
            return;
        }

        double amount;
        try{
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            msg.setText("Amount must be number");
            throw new RuntimeException(e);
        }

        if (amount <= 0){
            msg.setText("Amount must be greater than 0");
        }

        if (amount > acc.getBalance()){
            msg.setText("Insufficient money");
        }

        try{
            String description = "Transfer";
            acc.transfer(amount,description,receiverAcc,LoggedInUser.currentUser);

            bankAccountCSV.updateBalance(LoggedInUser.currentUserEmail,acc.getAccNum(),acc.getBalance());
            BankTransaction bankTransaction = acc.getTransactions().get(acc.getTransactions().size() - 1);
            BankTransactionStorage.saveTransaction(LoggedInUser.currentUserEmail, acc.getAccNum(),bankTransaction);
            StoreNotifications.add("Transfer sent to " + beneficiary);
            msg.setText("Transfer successful");
            clearFields();
        } catch (RuntimeException e) {
            msg.setText(e.getMessage());
        }
    }

    private void clearFields(){
        beneficiaryNameField.clear();
        accountNoField.clear();
        amountField.clear();
        purposeField.clear();
    }


}
