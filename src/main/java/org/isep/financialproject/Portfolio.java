package org.isep.financialproject;

public abstract class Portfolio {
    protected String name;

    public Portfolio(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public abstract double getValue();
}
