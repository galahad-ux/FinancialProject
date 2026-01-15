package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionViewController {
    @FXML
    private Button ATButton;
    @FXML
    private ListView<AssetTransaction> txListView;
    private final ObservableList<AssetTransaction> txItems = FXCollections.observableArrayList();

    private final Investment sharedInvestment = new Investment();

    public void openAHT() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTransaction.fxml"));
        Parent root = fxmlLoader.load();

        AddTransactionController controller = fxmlLoader.getController();
        controller.setInvestment(sharedInvestment);

        Stage stage = new Stage();
        stage.setTitle("Add Transaction");
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnHidden(e -> refreshTransactions());


    }

    @FXML
    private void initialize() {
        txListView.setItems(txItems);
        refreshTransactions(); // 初次加载
    }

    private void refreshTransactions() {
        txItems.setAll(sharedInvestment.getTransactions());
    }



}
