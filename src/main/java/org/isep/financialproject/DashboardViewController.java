package org.isep.financialproject;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


import javafx.stage.Stage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class DashboardViewController{
    @FXML
    private Button APButton;

    @FXML
    private Button goToInvestment;

    @FXML
    private Label totalAssetLabel;

    @FXML
    private TableView<InvRow> investmentTable;

    @FXML
    private TableColumn<InvRow, String> Investment;

    @FXML
    private TableColumn<InvRow, Double> InvestmentAmount;


    private final ObservableList<InvRow> invRows = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        Investment.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvestmentAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        investmentTable.setItems(invRows);

        refreshDashboard();
    }

    private void refreshDashboard() {
        invRows.clear();

        if (LoggedInUser.investment == null) {
            totalAssetLabel.setText("0");
            return;
        }

        totalAssetLabel.setText("" + LoggedInUser.investment.getTotalAmount());
        ArrayList<InvRow> temp = new ArrayList<>();

        for (AssetTransaction AT : LoggedInUser.investment.getTransactions()) {
            String symbol = extractSymbol(AT.getDescription());

            double qty = AT.getQuantity();
            double price = AT.getAmount() / AT.getQuantity();

            InvRow row = findRow(temp, symbol);

            if (row == null) {
                double q = (AT.getType() == TransactionType.SELL) ? -qty : qty;
                temp.add(new InvRow(symbol, q * price));
            } else {
                double q = (AT.getType() == TransactionType.SELL) ? -qty : qty;
                row.amount = row.amount + (q * price);
            }
        }

        for (InvRow Ir : temp) {
            if (Ir.amount > 0) {
                invRows.add(Ir);
            }
        }
    }

    private String extractSymbol(String desc) {
        String[] p = desc.split(":");
        return p[p.length - 1].trim();
    }

    private InvRow findRow(ArrayList<InvRow> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equals(name)) return list.get(i);
        }
        return null;
    }




    public static class InvRow {
        private String name;
        private Double amount;

        public InvRow(String name, Double amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() { return name; }
        public Double getAmount() { return amount; }
    }





    public void openAPV() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPortfolio.fxml"));
        Parent root = fxmlLoader.load();

        AddPortofolioController controller = fxmlLoader.getController();
        controller.setInvestment(LoggedInUser.investment);

        Stage stage = new Stage();
        stage.setTitle("Add Portfolio");
        stage.setScene(new Scene(root));
        stage.show();


    }


}




