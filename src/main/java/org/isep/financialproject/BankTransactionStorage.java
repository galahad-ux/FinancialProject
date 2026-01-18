package org.isep.financialproject;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankTransactionStorage {
    private static final String FILE = "bankTransactions.csv";

    public static void saveTransaction(String userEmail, String accNum, BankTransaction bt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {

            String line = userEmail + "," +
                    accNum + "," +
                    bt.getId() + "," +
                    bt.getType().name() + "," +
                    bt.getAmount() + "," +
                    bt.getDate().getTime() + "," +
                    bt.getDescription() + ","+
                    bt.getRecipient();

            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Error saving bank transaction: " + e.getMessage());
        }
    }

    public static void loadTransactionsForUser(String userEmail, List<BankAccount> accountList) {
        File f = new File(FILE);
        if (!f.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",", 8);
                if (data.length < 8) continue;

                if (!data[0].equalsIgnoreCase(userEmail)) continue;

                String accNum = data[1];
                String id = data[2];
                TransactionType type = TransactionType.valueOf(data[3]);
                double amount = Double.parseDouble(data[4]);
                Date date = new Date(Long.parseLong(data[5]));
                String description = data[6];
                String recipient = data[7];

                BankAccount acc = findAccount(accountList, accNum);
                if (acc != null) {
                    acc.addLoadedTransaction(new BankTransaction(id, date, amount, description, type, recipient));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error loading transactions: " + e.getMessage());
        }
    }
    private static BankAccount findAccount(List<BankAccount> accounts, String accNum){
        for (BankAccount a : accounts){
            if (a.getAccNum().equals(accNum))
                return a;
        }
        return null;
    }
}
