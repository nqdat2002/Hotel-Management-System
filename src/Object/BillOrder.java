/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class BillOrder {
    private List<Order> listorder;
    private String id_order;
    private String id_customer;
    private long totalprice;
    
    public BillOrder(){
        this.id_customer = this.id_order = "";
        this.totalprice = 0;
        this.listorder = new ArrayList<>();
    }

    public BillOrder(String id_order, String id_customer, long total, List<Order> listorder) {
        this.listorder = listorder;
        this.id_order = id_order;
        this.id_customer = id_customer;
        for(Order x: listorder){
            totalprice += x.getTotal();
        }
    }

    public List<Order> getListorder() {
        return listorder;
    }

    public String getId_order() {
        return id_order;
    }

    public String getId_customer() {
        return id_customer;
    }

    public long getTotalprice() {
        return totalprice;
    }

    public void setListorder(List<Order> listorder) {
        this.listorder = listorder;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public void setTotalprice(long totalprice) {
        this.totalprice = totalprice;
    }
    
    
}
