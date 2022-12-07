/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import Object.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class DataBaseFood {
    private connect conn;
    public DataBaseFood(){
        conn = new connect();
    }
    
    public void insertBaseFood(Food food){
        try{
            String str = "INSERT INTO food VALUES('"+food.getFood_id()+"', '"+food.getName()+"', '"+food.getPrice()+"')";
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New Food has been added successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet showFood(){
        ResultSet rs = null;
        try {				
            String str = "select * from food";				
            rs = conn.s.executeQuery(str); 
            }catch(Exception e) {
                    System.out.println(e);
            }
        return rs;
    }
    
    public void updateFood(Food food){
        try{
            String str = "UPDATE food SET Name = '"+food.getName()+"', Price = '"+food.getPrice()+"'where ID = '"+food.getFood_id()+"'";
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "This food has been updated successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    
    public void deleteFood(Food food){
        try{
            String str = "DELETE FROM food WHERE ID = '"+food.getFood_id()+"'"; 
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "This food has been deleted successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
