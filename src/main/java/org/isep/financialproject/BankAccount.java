package org.isep.financialproject;

import java.util.Date;
import java.util.Objects;

public abstract class BankAccount extends Portfolio {

    protected String accNum;
    protected double amount;

    protected BankAccount(
            String name,
            String description,
            Currency refCurrency,
            String accNum,
            double initialAmount
    ) {
        super(name, description, refCurrency, new Date());
        this.accNum = Objects.requireNonNull(accNum, "accNum cannot be null");

        if (initialAmount < 0) {
            throw new IllegalArgumentException("Initial amount cannot be negative");
        }
        this.amount = initialAmount;
    }

    public String getAccNum() {
        return accNum;
    }

    public double getBalance() {
        return amount;
    }


    public void deposit(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        amount += value;
    }


    public void withdraw(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (value > amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        amount -= value;
    }

    @Override
    public double getValue() {
        return amount;
    }
}

