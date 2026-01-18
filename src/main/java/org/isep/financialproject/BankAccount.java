package org.isep.financialproject;

import java.util.Date;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount extends Portfolio {
    protected String accNum;
    protected double amount;

    protected final List<BankTransaction> transactions = new ArrayList<>();

    protected BankAccount(String name, String description, Currency refCurrency, String accNum, double initialAmount) {
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

    public List<BankTransaction> getTransactions() {
        return List.copyOf(transactions);
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

    private String generateTransactionId(User user) {
        Objects.requireNonNull(user, "user cannot be null");
        return user.getUserEmail() + "-" + System.currentTimeMillis();
    }

    public void deposit(double value, String description, String recipient, User user) {
        deposit(value);
        transactions.add(new BankTransaction(generateTransactionId(user), new Date(), value, description, TransactionType.DEPOSIT, recipient));
    }

    public void withdraw(double value, String description, String recipient, User user) {
        withdraw(value);
        transactions.add(new BankTransaction(generateTransactionId(user), new Date(), value, description, TransactionType.WITHDRAW, recipient));
    }

    public void transfer(double value, String description, String recipient, User user) {
        withdraw(value);
        transactions.add(new BankTransaction(generateTransactionId(user), new Date(), value, description, TransactionType.TRANSFER, recipient));
    }

    public void cancelWithdrawal(User user) {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            BankTransaction t = transactions.get(i);
            if (t.getType() == TransactionType.WITHDRAW) {
                amount += t.getAmount();

                transactions.add(new BankTransaction(generateTransactionId(user), new Date(), t.getAmount(), "Cancellation of withdrawal: " + t.getDescription(), TransactionType.DEPOSIT, t.getRecipient()));
                return;
            }
        }
        throw new IllegalStateException("There is no withdrawal transaction to cancel");
    }

    public void addLoadedTransaction(BankTransaction bt){
        transactions.add(bt);
    }
}
