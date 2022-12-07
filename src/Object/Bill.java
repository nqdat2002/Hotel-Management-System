/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

/**
 *
 * @author admin
 */
public class Bill {
    private String id;
    private Customer cus;
    private Room rm;
    private long day;
    private long price;
    private double rate;
    private double discount;
    private double total;
    public Bill(){
        this.id = "";
        this.cus = new Customer();
        this.rm = new Room();
        this.day = 0;
        this.rate = 0.0;
        this.price = 0;
        this.discount = 0;
        this.total = 0;
    }
    public Bill(Customer cus, Room rm) {
        this.id = cus.getId() + rm.getId();
        this.cus = cus;
        this.rm = rm;
        this.day = (long)cus.getDayCount();
        if(day >= 30) rate = 0.06;
        else if(day >= 20) rate = 0.04;
        else if(day >= 10) rate = 0.02;
        else rate = 0.00;
        this.price = (long)(rm.getCost() * day);
        this.discount = (double) (rate * price);
        this.total = (double)(this.price - this.discount);
    }

    public void setDay(long day) {
        this.day = day;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getRate() {
        return rate;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }

    public String getId() {
        return id;
    }

    public Customer getCus() {
        return cus;
    }

    public Room getRm() {
        return rm;
    }

    public long getDay() {
        return day;
    }

    public long getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public void setRm(Room rm) {
        this.rm = rm;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    @Override
    public String toString(){
        return cus + "" ;
    }
}
