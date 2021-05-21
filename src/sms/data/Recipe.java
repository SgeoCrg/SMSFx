package sms.data;

import java.util.HashMap;
import java.util.Map;

/**
 * the union of one or more Prime goods made a recipe
 */

public class Recipe {
    protected int numID;
    protected String name;
    protected int ingNumber;
    protected HashMap<PrimeGood, Integer> recipe =
            new HashMap<PrimeGood, Integer>();//primeGood & quantity of it on the recipe
    protected double price = 0;

    public Recipe(int numID, String name, int ingNumber) {
        this.numID = numID;
        this.name = name;
        this.ingNumber = ingNumber;
    }

    /**
     * Add an ingredient to our recipe and increase the number of ingredients to build
     * Objects when we read the memory file
     * @param item
     * @param quantity
     */

    public void addPGood(PrimeGood item, int quantity) {
        recipe.put(item, quantity);
        setPrice();
        ingNumber++;
    }

    private void setPrice() {
        price = 0;
        recipe.forEach((k,v) -> price += k.pricePerUnit * v);
        price = (int)(price * 10000)/ 10000.0;
    }

    public String getRecipeName() { return name; }

    public double getPrice() { return price; }

    /**
     *
     * @return text to write on the file to store our recipes on memory
     */
    public String toString() {
        return numID + ";" + name + ";" + ingNumber + mapToString();
    }

    public int getNumID() { return numID; }

    /**
     *
     * @return an string that will be written on the memory file and, once read will
     * conform the list of different ingredientes on a recipe
     */
    private String mapToString() {
        String text = "";
        for (Map.Entry<PrimeGood, Integer> entry: recipe.entrySet()) {
            String key = "" + entry.getKey().getNumID();
            String value = "" + entry.getValue();
            text = text + ";" + key + ";" + value;
        }
        return text;
    }
}
