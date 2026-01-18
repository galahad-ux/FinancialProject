package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionViewController {

    @FXML
    private ListView<String> txListView;
    private final ObservableList<String> txItems = FXCollections.observableArrayList();
    private Investment sharedInvestment;

    @FXML
    public void openAHT() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTransaction.fxml"));
        Parent root = fxmlLoader.load();

        AddTransactionController controller = fxmlLoader.getController();
        if (sharedInvestment == null) {
            System.out.println("Error: sharedInvestment is null");
            return;
        }
        controller.setInvestment(sharedInvestment);

        Stage stage = new Stage();
        stage.setTitle("Add Transaction");
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnHidden(e -> refreshTransactions());


    }

    @FXML
    private void initialize() {
        sharedInvestment = LoggedInUser.investment;
        txListView.setItems(txItems);
        refreshTransactions();
    }

    private void refreshTransactions() {
        txItems.clear();

        //bank transactions
        for(Portfolio p: LoggedInUser.currentUser.getPortfolios()){
            if (p instanceof BankAccount account){
                for (BankTransaction bt: account.getTransactions()){
                    txItems.add(0, formatBankT(account,bt));
                }
            }
        }
        //investments
        if (sharedInvestment != null) {
            for (AssetTransaction aT: sharedInvestment.getTransactions()){
                txItems.add(0,aT.toString());
            }
        }
    }

    private String formatBankT(BankAccount account, BankTransaction bt) {
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH-mm").format(bt.getDate());
        return date + " | Bank Transaction | " + account.getName() +" | " + bt.getType() + " | " + String.format("%.2f",bt.getAmount());
    }


}
