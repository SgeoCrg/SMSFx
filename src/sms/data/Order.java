package sms.data;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is made to conform the way you can make orders as a list of meals or products
 */

public class Order {
    protected List<Meal> order = new ArrayList<Meal>();
    protected int orderNumber;
    protected double price = 0;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void addMeal(Meal item) {
        order.add(item);
        price += item.sellPrice;
    }

    public void removeMeal(Meal item) {
        for (int i = 0; i < order.size(); i++)
        {
            if (item.getNumID() == order.get(i).getNumID()) {
                order.remove(i);
                i = order.size();
            }
        }
    }

    public double getPrice() { return price; }

    public int getOrderNumber() {return orderNumber; }

    public void toText() {
        System.out.println(orderNumber);
        for(int i = 0; i < order.size(); i++) {
            System.out.print(i+1);
            System.out.println(order.get(i).name + " " + order.get(i).sellPrice);
        }
        System.out.println("TOTAL: " + price);
    }
}
