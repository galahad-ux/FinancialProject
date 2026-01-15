package org.isep.financialproject;

public class SavingsAccount extends BankAccount {
    private final double withdrawLimit;
    private final double interestRate;

    public SavingsAccount(String name, String description, Currency refCurrency, String accNum, double initialAmount, double withdrawLimit, double interestRate) {
        super(name, description, refCurrency, accNum, initialAmount);
        this.withdrawLimit = withdrawLimit;
        this.interestRate = interestRate;
    }


    @Override
    public void withdraw(double value) {
        if (value > withdrawLimit) {
            throw new IllegalArgumentException("Exceeds savings withdraw limit");
        }
        super.withdraw(value);
    }

    public void applyInterest() {
        amount += amount * (interestRate / 100);
    }

}
