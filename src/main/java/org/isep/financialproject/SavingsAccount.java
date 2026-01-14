package org.isep.financialproject;

public class SavingsAccount extends BankAccount {
    private final double withdrawLimit;

    public SavingsAccount(
            String name,
            String description,
            Currency refCurrency,
            String accNum,
            double initialAmount,
            double withdrawLimit
    ) {
        super(name, description, refCurrency, accNum, initialAmount);
        this.withdrawLimit = withdrawLimit;
    }

    @Override
    public void withdraw(double value) {
        if (value > withdrawLimit) {
            throw new IllegalArgumentException("Exceeds savings withdraw limit");
        }
        super.withdraw(value);
    }

}
