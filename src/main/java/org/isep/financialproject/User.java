package org.isep.financialproject;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Portfolio> portfolios = new ArrayList<>();

    public void addPortfolio(Portfolio portfolio) {
        portfolios.add(portfolio);
    }

    public List<Portfolio> getPortfolios() {
        return List.copyOf(portfolios); // protects internal list
    }

    public double getNetWorth() {
        double total = 0;
        for (Portfolio p : portfolios) {
            total += p.getValue();
        }
        return total;
    }
}
