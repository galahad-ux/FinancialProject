package org.isep.financialproject;

public class CheckingAccount {

    private double balance;

    public CheckingAccount(String name, double initialBalance) {
        super(name);
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    //adding money
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    //spending
    public void spend(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Spending amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
    }

    public double getValue() {
        return balance;
    }
}
