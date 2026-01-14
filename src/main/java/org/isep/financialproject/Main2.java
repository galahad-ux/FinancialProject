package org.isep.financialproject;

public class Main2 {
    public static void main(String[] args) {

        User user = new User(
                "Milla",
                "milla@isep.fr",
                "pswrd",
                Currency.EUR
        );

        CheckingAccount checking = new CheckingAccount(
                "Checking",
                "Daily account",
                Currency.EUR,
                "CHK-001",
                4321,
                500,
                300
        );

        SavingsAccount savings = new SavingsAccount(
                "Savings",
                "Long term savings",
                Currency.EUR,
                "SAV-001",
                54321,
                1000
        );

        checking.deposit(200, "salary", "employer", user);
        checking.spend(100, "groceries", "Monoprix", user);

        savings.deposit(300, "transfer to savings", "from checking", user);


        System.out.println("Checking balance: " + checking.getBalance());
        System.out.println("Savings balance: " + savings.getBalance());

        System.out.println("Checking transactions: " + checking.getTransactions().size());
        System.out.println("Savings transactions: " + savings.getTransactions().size());
    }
}
