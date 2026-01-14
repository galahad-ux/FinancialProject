package org.isep.financialproject;
import java.util.Date;

public class AssetTransaction extends Transaction{
    private TransactionType type;
    //private Asset asset;
    private double quantity;

    public AssetTransaction(String id,Date date,double amount,String description,
                            TransactionType type,double quantity){
        super(id, date, amount, description);
        this.type=type;
        this.quantity=quantity;

    }
    //----------------------get set----------------------------------
    public void setType(TransactionType type) {this.type = type;}
    public TransactionType getType() {return type;}

    public double getQuantity() {return quantity;}
    public void setQuantity(double quantity) {this.quantity = quantity;}


    //public Currency getPrice(Currency){
    //    return Currency;
    //}

    public String toString() {
        return "AssetTransaction{" + getId() + ", type=" + type +
                ", quantity=" + quantity + ", amount=" + getAmount() +
                ", date=" + getDate() + ", description='" + getDescription()+ '}';
    }


}
