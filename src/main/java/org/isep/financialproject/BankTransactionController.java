package org.isep.financialproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.time.LocalDate;

public class BankTransactionController {

    @FXML
    private TextField AmountField;

    @FXML
    private ComboBox<BankAccount> selectAccount;

    @FXML
    private Label msg;

    @FXML
    public void initialize() {
        selectAccount.getItems().clear();

        //loading bank accounts
        for (Portfolio p: LoggedInUser.currentUser.getPortfolios()){
            if (p instanceof BankAccount account){
                selectAccount.getItems().add(account);
            }
        }

        //showing balance in combobox
        selectAccount.setCellFactory(cb ->new ListCell<>() {
            @Override
            protected void updateItem(BankAccount bankAccount, boolean empty) {
                super.updateItem(bankAccount, empty);
                if (empty || bankAccount == null) setText(null);
                else setText(bankAccount.name + " - " + "Balance: " + String.format("%.2f", bankAccount.getBalance()));
            }
        });

        //after selection
        selectAccount.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(BankAccount bankAccount, boolean empty) {
                super.updateItem(bankAccount, empty);
                if (empty || bankAccount == null) setText(null);
                else setText(bankAccount.name);
            }
        });
    }

    @FXML
    private void deposit(){
        BankAccount account = selectAccount.getValue();
        if (account == null){
            msg.setText("Please select a bank account");
            return;
        }

        String amount = AmountField.getText();

        if (amount.isEmpty()){
            msg.setText("Please write the amount for transaction");
            return;
        }

        double amt;
        try{
            amt = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            msg.setText("Amount must be number");
            throw new RuntimeException(e);
        }

        if (amt <= 0){
            msg.setText("Amount must be greater than 0");
        }

        try{
            account.deposit(amt,"Deposit", "Self", LoggedInUser.currentUser);

            bankAccountCSV.updateBalance(LoggedInUser.currentUserEmail,account.getAccNum(),account.getBalance());
            BankTransaction bankTransaction = account.getTransactions().get(account.getTransactions().size() - 1);
            BankTransactionStorage.saveTransaction(LoggedInUser.currentUserEmail, account.getAccNum(),bankTransaction);
            StoreNotifications.add(String.format("%.2f", amt)+ " deposited to " + account.getName());
            msg.setText("Deposit successful");
            AmountField.clear();

        } catch (RuntimeException e) {
            msg.setText(e.getMessage());
        }
    }

    @FXML
    private void withdraw(){
        BankAccount account = selectAccount.getValue();
        if (account == null){
            msg.setText("Please select a bank account");
            return;
        }

        String amount = AmountField.getText();

        if (amount.isEmpty()){
            msg.setText("Please write the amount for transaction");
            return;
        }

        double amt;
        try{
            amt = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            msg.setText("Amount must be number");
            throw new RuntimeException(e);
        }

        if (amt <= 0){
            msg.setText("Amount must be greater than 0");
        }

        if (amt > account.getBalance()){
            msg.setText("Insufficient money");
        }

        try{
            account.withdraw(amt,"Withdraw", "ATM", LoggedInUser.currentUser);

            bankAccountCSV.updateBalance(LoggedInUser.currentUserEmail,account.getAccNum(),account.getBalance());
            BankTransaction bankTransaction = account.getTransactions().get(account.getTransactions().size() - 1);
            BankTransactionStorage.saveTransaction(LoggedInUser.currentUserEmail, account.getAccNum(),bankTransaction);
            StoreNotifications.add(String.format("%.2f", amt)+ " withdrew from " + account.getName());
            msg.setText("Withdraw successful");
            AmountField.clear();

        } catch (RuntimeException e) {
            msg.setText(e.getMessage());
        }
    }
}

