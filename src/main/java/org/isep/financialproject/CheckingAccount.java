package org.isep.financialproject;

public class CheckingAccount extends BankAccount {
    private final double withdrawLimit;
    private final double spendLimit;

    public CheckingAccount(
            String name,
            String description,
            Currency refCurrency,
            String accNum,
            double initialAmount,
            double withdrawLimit,
            double spendLimit
    ) {
        super(name, description, refCurrency, accNum, initialAmount);
        this.withdrawLimit = withdrawLimit;
        this.spendLimit = spendLimit;
    }

    @Override
    public void withdraw(double value) {
        if (value > withdrawLimit) {
            throw new IllegalArgumentException("Exceeds withdraw limit");
        }
        super.withdraw(value);
    }

    public void spend(double value, String description, String recipient, User user) {
        if (value > spendLimit) {
            throw new IllegalArgumentException("Exceeds spend limit");
        }
        super.withdraw(value, description, recipient, user);
    }
}
