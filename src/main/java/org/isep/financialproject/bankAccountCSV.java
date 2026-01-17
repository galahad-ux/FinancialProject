package org.isep.financialproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class bankAccountCSV {
    private static final String FILE = "bankAccounts.csv";

    public static void saveAccount(String userEmail, BankAccount acc) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {

            String type = (acc instanceof CheckingAccount) ? "CHECKING" : "SAVINGS";
            double withdrawLimit = 999999;
            double spendLimit = 10000;
            double interestRate = 0;

            String line = userEmail + "," +
                    type + "," +
                    acc.getAccNum() + "," +
                    acc.getBalance() + "," +
                    acc.getRefCurrency().name() + "," +
                    withdrawLimit + "," +
                    spendLimit + "," +
                    interestRate;

            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Error saving bank account: " + e.getMessage());
        }
    }

    public static List<BankAccount> loadAccountsForUser(String userEmail) {
        List<BankAccount> result = new ArrayList<>();

        File f = new File(FILE);
        if (!f.exists()) return result;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length < 8) continue;

                if (!data[0].equalsIgnoreCase(userEmail)) continue;

                String type = data[1];
                String accNum = data[2];
                double balance = Double.parseDouble(data[3]);
                Currency currency = Currency.valueOf(data[4]);

                double withdrawLimit = Double.parseDouble(data[5]);
                double spendLimit = Double.parseDouble(data[6]);
                double interestRate = Double.parseDouble(data[7]);

                BankAccount acc;

                if ("CHECKING".equalsIgnoreCase(type)) {
                    acc = new CheckingAccount(
                            "Checking",
                            "Loaded from CSV",
                            currency,
                            accNum,
                            balance,
                            withdrawLimit,
                            spendLimit
                    );
                } else {
                    acc = new SavingsAccount(
                            "Savings",
                            "Loaded from CSV",
                            currency,
                            accNum,
                            balance,
                            withdrawLimit,
                            interestRate
                    );
                }

                result.add(acc);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error loading accounts: " + e.getMessage());
        }

        return result;
    }

    //for bank transactions balance update
    public static void updateBalance(String userEmail, String accNo, double newBalance) {
        List<String> lines = new ArrayList<>();

        File f = new File(FILE);
        if (!f.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
//                if (data.length < 8) {
//                    lines.add(line);
//                    continue;
//                }

                if (data[0].equalsIgnoreCase(userEmail) && data[2].equals(accNo)) {
                    data[3] = String.valueOf(newBalance);
                    line = String.join(",", data);
                }
                lines.add(line);

            }
        } catch (IOException e){
            throw new RuntimeException();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
