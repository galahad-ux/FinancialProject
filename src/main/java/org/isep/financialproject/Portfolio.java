package org.isep.financialproject;

import java.util.Date;
import java.util.Objects;

public abstract class Portfolio {
    protected String name;
    protected String description;
    protected Currency refCurrency;
    protected Date creationDate;

    protected Portfolio(String name, String description, Currency refCurrency, Date creationDate) {
        this.name = Objects.requireNonNull(name);
        this.description = description;
        this.refCurrency = Objects.requireNonNull(refCurrency);
        this.creationDate = new Date(Objects.requireNonNull(creationDate).getTime());
    }

    public String getName() {
        return name;
    }

    public Currency getRefCurrency() {
        return refCurrency;
    }

    public Date getCreationDate() {
        return new Date(creationDate.getTime());
    }

    public abstract double getValue();
}
