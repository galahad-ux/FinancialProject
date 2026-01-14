package org.isep.financialproject;
import java.util.Date;

public class Stock extends Asset{
    private String CompanyName;

    public Stock(String symbol,double quantity,double purchasePrice,Date purshaseDate,String companyName){
        super(symbol,quantity,purchasePrice,purshaseDate);
        this.CompanyName=companyName;
    }

    public String getCompanyName() {return CompanyName;}
    public void setCompanyName(String companyName) {CompanyName = companyName;}
}
