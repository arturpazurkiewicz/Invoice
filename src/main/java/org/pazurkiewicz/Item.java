package org.pazurkiewicz;

public class Item {
    private final String name;
    private final Float netAmount;
    private final TaxManager.taxType taxType;

    public Item(String name, Float netAmount, TaxManager.taxType taxType) {
        this.name = name;
        this.netAmount = netAmount;
        this.taxType = taxType;
    }

    public String getName() {
        return name;
    }

    public Float getNetAmount() {
        return netAmount;
    }

    public TaxManager.taxType getTaxType() {
        return taxType;
    }
}
