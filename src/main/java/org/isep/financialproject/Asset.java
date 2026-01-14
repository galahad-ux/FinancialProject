package org.isep.financialproject;

import java.util.Date;

public class Asset {
    private String symbol;
    private double quantity;
    private double purchasePrice;
    private Date purchaseDate;

    public Asset(String symbol,double quantity,double purchasePrice,Date purchaseDate){
        this.symbol=symbol;
        this.quantity=quantity;
        this.purchasePrice=purchasePrice;
        this.purchaseDate=purchaseDate;
    }

    //get set
    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}

    public double getQuantity() {return quantity;}
    public void setQuantity(double quantity) {this.quantity = quantity;}

    public double getPurchasePrice() {return purchasePrice;}
    public void setPurchasePrice(double purchasePrice) {this.purchasePrice = purchasePrice;}

    public Date getPurchaseDate() {return purchaseDate;}
    public void setPurchaseDate(Date purchaseDate) {this.purchaseDate = purchaseDate;}

}
