package org.isep.financialproject;

import java.io.*;
import java.util.Date;

public class InvestmentStorage {
    private static String fileName(String email) {

        String safe = email.replace("@", "_").replace(".", "_");
        return "transactions_" + safe + ".csv";
    }

    public static void save(String email, Investment investment) {
        String file = fileName(email);
        try {
            FileWriter fw= new FileWriter(file);
            BufferedWriter bw= new BufferedWriter(fw);


            for (AssetTransaction tx : investment.getTransactions()) {
                bw.write(tx.getId() + "," + tx.getType() + "," + tx.getQuantity() + ","
                        + tx.getAmount() + "," + tx.getDate().getTime() + "," +
                        tx.getDescription());
                bw.newLine();
            }


            bw.close();
        } catch (IOException e) {

            System.out.println("ERrror:" +e.getMessage());
        }
    }

    public static Investment load(String email) {
        Investment inv = new Investment();
        String file = fileName(email);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] p = line.split(",",6);
                String id =p[0];

                TransactionType type =TransactionType.valueOf(p[1]);

                double quantity = Double.parseDouble(p[2]);
                double amount = Double.parseDouble(p[3]);

                Date date = new Date(Long.parseLong(p[4]));

                String desc =p[5];

                inv.getTransactions().add(new AssetTransaction(id, date, amount, desc, type, quantity));
            }
            br.close();

        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        return inv;
    }
}
