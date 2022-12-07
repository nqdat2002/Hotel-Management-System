/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import Object.Bill;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class DatabaseBill {
    private connect conn;
    public DatabaseBill(){
        conn = new connect();
    }
    
    public void insertBill(Bill bill){
        try{
            String str = "INSERT INTO checkoutlist_noservice VALUES('"+bill.getId()+"', '"
                    +bill.getCus().getId()+"' ,'"+bill.getRm().getId()+"', '"+bill.getCus().getName()+"', '"
                    +bill.getRm().getType()+"' ,'"+bill.getRm().getCost()+"', '"+bill.getCus().getDateStart()+"', '"
                    +bill.getCus().getDateEnd()+"' ,'"+bill.getCus().getDayCount()+"', '"+bill.getPrice()+"', '"
                    +bill.getDiscount()+"', '"+bill.getTotal()+"')";
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New Bill has been added successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet showBill(){
        ResultSet rs = null;
        try {				
        String str = "select * from checkoutlist_noservice";				
        rs = conn.s.executeQuery(str); 
        }catch(Exception e) {
                System.out.println(e);
        }
        return rs;
    }
    
//    public void updateRoom(Bill bill){
//        try{
//            String str = "UPDATE checkoutlist_noservice SET Type = '"+bill.getType()+"', Bed = '"+room.getBed()+"', Cost = '"+room.getCost()+"', Status = '"+room.getStatus()+"'where ID = '"+room.getId()+"'"; 
//            conn.s.executeUpdate(str);
//            JOptionPane.showMessageDialog(null, "This Bill has been updated successfully!");
//        }catch(Exception e) {
//            System.out.println(e);
//        }
//    }
    
    
    public void deleteRoom(Bill bill){
        try{
            String str = "DELETE FROM checkoutlist_noservice WHERE ID = '"+bill.getId()+"'"; 
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "This Bill has been deleted successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
