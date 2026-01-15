package org.isep.financialproject;

import javafx.scene.control.MenuButton;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String userFullName;
    public String userEmail;
    public String userPassword;
    public Currency preferredCurrency;
    public static List<User> allUsers = null;
    private final List<Portfolio> portfolios;

    public User(String userFullName, String userEmail, String userPassword, Currency preferredCurrency) {
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.preferredCurrency = preferredCurrency;
        this.portfolios = new ArrayList<>();
    }

    //Getters
    public String getUserFullName() {
        return userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Currency getPreferredCurrency() {
        return preferredCurrency;
    }

    public void setPreferredCurrency(Currency preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    //Portfolio management methods
    public void addPortfolio(Portfolio portfolio) {
        portfolios.add(portfolio);
    }

    public void removePortfolio(String portfolioName) {

    }

    public void getPortfolio(String portfolioName) {

    }

    //Authentication management methods
    public static User checkEmail(String userEmail) {
        for (User user : allUsers) {
            if (user.getUserEmail().equalsIgnoreCase(userEmail)) {
                return user;
            }
        }
        return null;
    }

    public static boolean emailExists(String userEmail) {
        return checkEmail(userEmail) != null;
    }

    public static boolean register(String userFullName, String userEmail, String userPassword, Currency preferredCurrency) {
        if (emailExists(userEmail)) {
            System.out.println("User email already exists");
            return false;
        }
        User newUser = new User(userFullName, userEmail, userPassword, preferredCurrency);
        allUsers.add(newUser);
        User.saveAllUsers(allUsers, "users.csv");
        return true;
    }

    public static boolean authenticate(String userEmail, String userPassword) {
        User user = checkEmail(userEmail);
        if (user != null && user.getUserPassword().equals(userPassword)) {
            return true;
        }
        return false;
    }


    //CSV methods
    public String toCSV() {
        return userFullName + "," + userEmail + "," + userPassword + "," + preferredCurrency;
    }

    public static User fromCSV(String csvLine) {
        String[] objects = csvLine.split(",");

        //preferred currency
        Currency currency;
        if (objects.length > 3) {
            currency = Currency.valueOf(objects[3]);
        } else {
            currency = Currency.EUR;
        }

        return new User(objects[0], objects[1], objects[2], currency);
    }

    //saving new user to csv file
    public static void saveAllUsers(List<User> users, String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv"))) {
            for (User a : users) {
                writer.write(a.toCSV());
                writer.newLine();
            }
            System.out.println("Saved to file");
        } catch (IOException e) {
            System.out.println("Error saving users" + e.getMessage());
        }
    }

    public static List<User> loadAllUsers(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    users.add(fromCSV(line));
                }
            }
            System.out.println("Users loaded");
        } catch (IOException e) {
            System.out.println("Error loading users" + e.getMessage());
        }
        return users;
    }

    //To change user details in csv when changed in settings view
    public static void newUserDetails(String oldEmail, String changedFullName, String changedEmail, String changedPassword, String changedCurrency) {
        List<String> details = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(oldEmail)) {
                    data[0] = changedFullName;
                    data[1] = changedEmail;
                    data[2] = changedPassword;
                    data[3] = changedCurrency;
                }
                details.add(String.join(",", data));
            }
            System.out.println("Users details changed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv"))) {
            for (String d : details) {
                writer.write(d);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



//    public double getNetWorth() {
//        double total = 0;
//        for (Portfolio p : portfolios) {
//            total += p.getValue();
//        }
//        return total;
//    }

