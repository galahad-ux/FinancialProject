package org.isep.financialproject;

public class AccountRow {
    private final String type;
    private final double amount;

    public AccountRow(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}
