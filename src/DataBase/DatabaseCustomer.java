package DataBase;

import Object.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class DatabaseCustomer {
    private connect conn;
    public DatabaseCustomer(){
        conn = new connect();
    }
    
    public void insertCustomer(Customer customer){
        try{
            String str = "INSERT INTO customer VALUES('"+customer.getId()+"', '"
                    +customer.getName()+"' ,'"+customer.getPhone()+"', '"+customer.getAddress()+"', '"+customer.getGender()+"', '"
                    +customer.getDateStart()+"', '"+customer.getDateEnd()+"' ,'"+customer.getChecked()+"', '"+customer.getId_room()+"', '"
                    +customer.getType()+"', '"+customer.getBed()+"')";
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New Customer has been added successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet showCustomer(){
        ResultSet rs = null;
        try {				
        String str = "select * from customer";				
        rs = conn.s.executeQuery(str); 
        }catch(Exception e) {
                System.out.println(e);
        }
        return rs;
    }
    
    public ResultSet showCheckedInCustomer(){
        ResultSet rs = null;
        try {				
        String str = "select * from customer where Checked = 'Checked-In'";				
        rs = conn.s.executeQuery(str); 
        }catch(Exception e) {
                System.out.println(e);
        }
        return rs;
    }
    
    public void updateCustomer(Customer customer){
        try{
            String str = "UPDATE customer SET Name = '"+customer.getName()+"',Phone_Number = '"
                    +customer.getPhone()+"', Address = '"+customer.getAddress()+"', Gender = '"
                    +customer.getGender()+"', DateStart = '"+customer.getDateStart()+"', DateEnd = '"
                    +customer.getDateEnd()+"', Checked = '"+customer.getChecked()+"', ID_Room = '"
                    +customer.getId_room()+"', Type = '"+customer.getType()+"', Bed = '"+customer.getBed()+"' where ID = '"+customer.getId()+"'"; 
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New Customer has been updated successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateCustomerCheckedOut(Customer customer){
        try{
            String str = "UPDATE customer SET Checked = '"+"Checked-Out"+"' where ID = '"+customer.getId()+"'"; 
            conn.s.executeUpdate(str);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteCustomer(Customer customer){
        try{
            String str = "DELETE FROM customer WHERE ID = '"+customer.getId()+"'"; 
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "This customer has been deleted successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
