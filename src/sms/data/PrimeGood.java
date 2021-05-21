package sms.data;

import java.text.DecimalFormat;

public class PrimeGood {
    protected String name;

    protected String type;

    protected int numID;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountPerPack() {
        return amountPerPack;
    }

    protected double price;
    protected int amountPerPack;//TODO create gr, ml, units,... to control
    protected double pricePerUnit;//to control the price on every recipe

    public PrimeGood(String type, int numID, double price, int amountPerPack, String name) {
        this.type = type;
        this.numID = numID;
        this.price = price;
        this.amountPerPack = amountPerPack;
        this.name = name;
        pricePerUnit = price / amountPerPack;
    }

    public int getNumID() { return numID; }

    public String getPricePerUnit() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        return df.format(pricePerUnit);
    }

    @Override
    public String toString() {
        return type + ";" + numID + ";" + price + ";" + amountPerPack + ";" + name;
    }

}