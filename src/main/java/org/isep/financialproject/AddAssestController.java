package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import java.util.Date;

public class AddAssestController {

    @FXML
    private TextField SymbolField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField QuantityField;
    @FXML
    private MenuButton AssestType;
    @FXML
    private Button AddButtom;
    @FXML
    private Label Message;


    private Investment investment;
    public void setInvestment(Investment investment) {
        this.investment = investment;
    }
    @FXML
    private void chooseStock() {
        AssestType.setText("Stock");
    }

    @FXML
    private void chooseToken() {
        AssestType.setText("Token");
    }

    private void clearInputs() {
        SymbolField.clear();
        nameField.clear();
        QuantityField.clear();
        AssestType.setText("AssetType");
    }

    @FXML
    private void addAsset() {
        if (investment == null) {
            Message.setText("Error:Investment not set!");
            return;
        }

        Date date = new Date();
        String s = SymbolField.getText().trim();
        String n = nameField.getText().trim();
        String t = AssestType.getText().trim();
        if (s.isEmpty()){
            Message.setText("Error:Symbol cannot be empty!");
            return;
        }

        if (n.isEmpty()){
            Message.setText("Error:Name cannot be empty!");
            return;
        }

        if (!t.equals("Stock") && !t.equals("Token")) {
            Message.setText("Error:Please choose Asset type");
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(QuantityField.getText().trim());
        } catch (Exception e) {
            Message.setText("Error:Quantity must be a number!");
            return;
        }
        if (quantity <= 0) {
            Message.setText("Error:Quantity must be > 0!");
            return;
        }

        double price;
        try {
            price = AlphaVantageClient.getStockPrice(s);
        } catch (Exception e) {
            Message.setText("Error:Failed to fetch The price for " + s + "\n" + e.getMessage());
            return;
        }


        if (t.equals("Stock")) {
            investment.addStock(s, n, quantity, price, date);
        } else if (t.equals("Token")) {
            investment.addToken(s, n, quantity, price, date);
        }

        UserAssetStorage.save(LoggedInUser.currentUserEmail, investment);
        InvestmentViewController.refreshView();
        Message.setText(("Add success:" + s + " qty:" + quantity + " price:" + price+"Date:"+date));
        clearInputs();
    }


}
