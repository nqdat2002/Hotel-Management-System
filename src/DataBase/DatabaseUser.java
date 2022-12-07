/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import Object.User;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class DatabaseUser {
    private connect conn;
    public DatabaseUser(){
        conn = new connect();
    }
    
    public void insertUser(User user){
        try{
            String str = "INSERT INTO user VALUES('"+user.getUsername()+"', '"+user.getPassword()+"')";
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New User has been added successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet showUser(){
        ResultSet rs = null;
        try {				
        String str = "select * from user";				
        rs = conn.s.executeQuery(str); 
        }catch(Exception e) {
                System.out.println(e);
        }
        return rs;
    }
    
    public void updateUser(User user){
        try{
            String str = "UPDATE user SET password = '"+user.getPassword()+"'where username = '"+user.getUsername()+"'"; 
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "This user has been updated successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateUser_non_identify(User user){
        try{
            String str = "UPDATE user SET password = '"+user.getPassword()+"'where username = '"+user.getUsername()+"'"; 
            conn.s.executeUpdate(str);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteUser(User user){
        try{
            String str = "DELETE FROM user WHERE username = '"+user.getUsername()+"'"; 
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "This User has been deleted successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
