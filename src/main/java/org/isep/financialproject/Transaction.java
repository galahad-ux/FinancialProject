package org.isep.financialproject;
import java.util.Date;

public class Transaction {
    private String id;
    private Date date;
    private double amount;
    private String description;

    public Transaction(String id,Date date,double amount,String description){
        this.id=id;
        this.date=date;
        this.amount=amount;
        this.description=description;
    }

    //----------------------get set----------------------------------
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public Date getDate() {
        return date;
    }
    public void setDate(Date date){this.date=date;}

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description;}

    public void setAmount(double amount) {this.amount = amount;}
    public double getAmount(){
        return amount;
    }





}
