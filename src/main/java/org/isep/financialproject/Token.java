package org.isep.financialproject;

import java.util.Date;

public class Token extends Asset{
    private String tokenName;

    public Token(String symbol, double quantity, double purchasePrice, Date purshaseDate, String tokenName){
        super(symbol,quantity,purchasePrice,purshaseDate);
        this.tokenName=tokenName;
    }

    public String getTokenName() {return tokenName;}
    public void setTokenName(String tokenName) {this.tokenName = tokenName;}
}
