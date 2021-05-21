package sms.data;

import java.text.DecimalFormat;

/**
 *
 * This Class builds the ingredients, base of our programa
 */

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

    /**
     *
     * Constructor for the Class. It calculates the unit price on an automatic way
     */
    public PrimeGood(String type, int numID, double price, int amountPerPack, String name) {
        this.type = type;
        this.numID = numID;
        this.price = price;
        this.amountPerPack = amountPerPack;
        this.name = name;
        pricePerUnit = price / amountPerPack;
    }

    public int getNumID() { return numID; }

    /**
     *
     * @return price per unit with just 3 decimals
     */

    public String getPricePerUnit() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        return df.format(pricePerUnit);
    }

    /**
     *
     * @return the way to print the different objects on the memory file
     */
    @Override
    public String toString() {
        return type + ";" + numID + ";" + price + ";" + amountPerPack + ";" + name;
    }

}