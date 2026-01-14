package org.isep.financialproject;
import java.util.Date;

public class BankTransaction extends Transaction{
    private TransactionType type;
    private String recipient;

    public BankTransaction(String id,Date date,double amount,String description,
                           TransactionType type,String recipient){
        super(id,date,amount,description);
        this.type=type;
        this.recipient=recipient;

    }
    //----------------------get set----------------------------------
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {this.type = type;}

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {this.recipient = recipient;}


}



