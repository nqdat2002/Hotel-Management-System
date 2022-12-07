/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

/**
 *
 * @author admin
 */
public class Order {
    private String date_order;
    private Food food;
    private int quantity;
    private long total;

    public Order(String date_order, Food food, int quantity, long total) {
        this.date_order = date_order;
        this.food = food;
        this.quantity = quantity;
        this.total = food.getPrice() * quantity;
    }

    public Order() {
        this.date_order = "";
        this.food = null;
        this.quantity = 0;
        this.total = 0;
    }

    public String getDate_order() {
        return date_order;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getTotal() {
        return total;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    
    
}

