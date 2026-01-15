package org.isep.financialproject;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;

public class AddAssetTransactionController {
    @FXML
    private TextField SymbolField;
    @FXML
    private TextField NameField;
    @FXML
    private TextField QuantityField;
    @FXML
    private Button BuyButton;
    @FXML
    private Button SellButton;
    @FXML
    private MenuButton TypeMenu;
    @FXML
    private Label Message;

    private Investment investment;

    public void setInvestment(Investment investment){
        this.investment=investment;
    }

    @FXML
    private void chooseStock(){
        TypeMenu.setText("Stock");
    }
    @FXML
    private void chooseToken(){
        TypeMenu.setText("Token");
    }

    private void clearInputs() {
        SymbolField.clear();
        NameField.clear();
        QuantityField.clear();
        TypeMenu.setText("Choose the type");
    }

    @FXML
    private void Buy() {
        Date date = new Date();

        if (investment == null) {
            Message.setText("Error:Investment not set!");
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(QuantityField.getText().trim());
        } catch (NumberFormatException ex) {
            Message.setText("Error:Quantity must be a number!");
            return;
        }
        if (quantity <= 0) {
            Message.setText("Error:Quantity must be > 0!");
            return;
        }

        String s = SymbolField.getText().trim();
        String n = NameField.getText().trim();
        String t = TypeMenu.getText().trim();


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

        double price;
        try {
            price = AlphaVantageClient.getStockPrice(s);
        } catch (Exception ex) {
            Message.setText("Error:Failed to fetch price for " + s + "\n" + ex.getMessage());
            return;
        }

        if (t.equals("Stock")) {
            investment.buyStock(s, n, quantity, price, date);
        } else {
            investment.buyToken(s, n, quantity, price, date);
        }

        Message.setText(("BUY success:" + s + " qty:" + quantity + " price:" + price+"Date:"+date));

        InvestmentStorage.save(LoggedInUser.currentUserEmail, investment);
        clearInputs();
        return;


    }

    @FXML
    private void Sell(){

        Date date = new Date();

        if (investment == null) {
            Message.setText("Error:Investment not set!");
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(QuantityField.getText().trim());
        } catch (NumberFormatException ex) {
            Message.setText("Error:Quantity must be a number!");
            return;
        }
        if (quantity <= 0) {
            Message.setText("Error:Quantity must be > 0!");
            return;
        }

        String s = SymbolField.getText().trim();
        String n = NameField.getText().trim();
        String t = TypeMenu.getText().trim();


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

        double price;
        try {
            price = AlphaVantageClient.getStockPrice(s);
        } catch (Exception ex) {
            Message.setText("Error:Failed to fetch price for " + s + "\n" + ex.getMessage());
            return;
        }


        investment.sell(s,quantity,price,date);


        Message.setText(("Sell success:" + s + " qty:" + quantity + " price:" + price+"Date:"+date));

        InvestmentStorage.save(LoggedInUser.currentUserEmail, investment);
        clearInputs();
        return;

    }








}
