package sms.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class try to build a Meal combining all the differents recipes that the program can create
 *
 */

public class Meal {
    protected int numID;
    protected String name;
    protected List<Recipe> meal = new ArrayList<Recipe>();//1 or more recipes
    protected double costPrice;
    protected double sellPrice;

    public Meal(int numID, String name) {
        this.numID = numID;
        this.name = name;
    }

    public void addRecipe(Recipe rec) {
        meal.add(rec);
        setPrice();
        sellPrice = (int)(400 * costPrice)/100.0;
        do {
            if ((sellPrice * 100) % 5 != 0) {
                sellPrice = (sellPrice*100 + 1)/100;
            }
        } while ((sellPrice * 100) % 5 != 0);
    }

    public void setPrice() {
        costPrice = 0;
        for (int i = 0; i < meal.size(); i++) {
            costPrice += meal.get(i).price;
        }
    }

    public int getNumID() { return numID; }

    public void toText() {
        System.out.println(numID + " " + name);
        for(int i = 0; i < meal.size(); i++) {
            System.out.println(meal.get(i).numID + " " + meal.get(i).name);
        }
        System.out.println("Cost price: " + costPrice +" Sell price: " + sellPrice);
    }
}
