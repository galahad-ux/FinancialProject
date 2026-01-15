package org.isep.financialproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;


public class BankViewController {
    @FXML private Label totalBalanceLabel;

    @FXML private TableView<AccountRow> accountsTable;
    @FXML private TableColumn<AccountRow, String> typeColumn;
    @FXML private TableColumn<AccountRow, Double> amountColumn;

    private User user;

    public void setUser(User user) {
        this.user = user;
        setupTable();
        refresh();
    }

    private void setupTable() {
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void refresh() {
        if (user == null) return;

        List<AccountRow> rows = new ArrayList<>();
        double total = 0;

        for (BankAccount acc : user.getBankAccounts()) {
            String type = (acc instanceof CheckingAccount) ? "Checking" : "Savings";
            double balance = acc.getBalance();

            rows.add(new AccountRow(type, balance));
            total += balance;
        }

        accountsTable.setItems(FXCollections.observableArrayList(rows));
        totalBalanceLabel.setText(String.valueOf(total));
    }

}
