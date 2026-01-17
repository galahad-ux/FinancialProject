package org.isep.financialproject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class InvestmentViewController {

    @FXML
    public Label TotalAmount;
    @FXML
    private TableView<AssetRow> assetTable;
    @FXML
    private TableColumn<AssetRow, String> NameTable;
    @FXML
    private TableColumn<AssetRow, String> TypeTable;
    @FXML
    private TableColumn<AssetRow, Double> QuantityTable;
    @FXML
    private TableColumn<AssetRow, Double> AmoutTable;

    private ObservableList<AssetRow> rows = FXCollections.observableArrayList();
    public static InvestmentViewController instance;

    @FXML
    public void initialize() {
        instance = this;
        NameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        TypeTable.setCellValueFactory(new PropertyValueFactory<>("type"));
        QuantityTable.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        AmoutTable.setCellValueFactory(new PropertyValueFactory<>("amount"));
        assetTable.setItems(rows);


        refresh();
    }

    private void refresh() {
        rows.clear();

        Investment inv = LoggedInUser.investment;
        if (inv == null) {
            TotalAmount.setText("0");
            return;
        }

        ArrayList<AssetRow> temp = new ArrayList<>();

        for (AssetTransaction tx : inv.getTransactions()) {
            String symbol = extractSymbol(tx.getDescription());
            String type = extractType(tx.getDescription());

            double qty = tx.getQuantity();
            double price = tx.getAmount()/tx.getQuantity();

            AssetRow row = findRow(temp, symbol);

            if (row == null) {
                double q = (tx.getType() == TransactionType.SELL)? -qty : qty;

                temp.add(new AssetRow(symbol, type, q, q * price));
            } else {
                if (tx.getType() == TransactionType.SELL) {

                    row.quantity -= qty;

                } else {
                    row.quantity += qty;

                }


                row.amount = row.quantity * price;
            }
        }



        double total = 0;
        for (AssetRow r : temp) {
            if (r.quantity > 0) {
                rows.add(r);
                total += r.amount;

            }
        }
        TotalAmount.setText(String.format("%.2f",total));
    }


    public static void refreshView() {
        if (instance != null) {
            instance.refresh();
        }
    }

    private AssetRow findRow(ArrayList<AssetRow> list, String name) {
        for (AssetRow r : list) {

            if (r.name.equals(name)) {
                return r;
            }

        }


        return null;
    }

    private String extractSymbol(String desc) {

        String[] p = desc.split(":");

        return p[p.length - 1];

    }

    private String extractType(String desc) {

        if (desc.contains("Stock")) {
            return "Stock";
        }
        if (desc.contains("Token")) {
            return "Token";
        }

        return "Unknown";
    }

    public static class AssetRow {
        private String name;
        private String type;
        private Double quantity;
        private Double amount;

        public AssetRow(String name, String type
                , Double quantity, Double amount) {
            this.name = name;
            this.type = type;
            this.quantity = quantity;
            this.amount = amount;

        }

        public String getName() {
            return name;
        }
        public String getType() {
            return type;
        }
        public Double getQuantity() {
            return quantity;
        }
        public Double getAmount() {
            return amount;
        }
    }
}
