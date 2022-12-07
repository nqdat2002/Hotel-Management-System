package DataBase;

import Object.Room;
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
public class DatabaseRoom {
    private connect conn;
    public DatabaseRoom(){
        conn = new connect();
    }
    
    public void insertRoom(Room room){
        try{
            String str = "INSERT INTO room VALUES('"+room.getId()+"', '"+room.getBed()+"' ,'"+room.getType()+"', '"+room.getCost()+"', '"+room.getStatus()+"')";
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New Room has been added successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet showRoom(){
        ResultSet rs = null;
        try {				
        String str = "select * from room";				
        rs = conn.s.executeQuery(str); 
        }catch(Exception e) {
                System.out.println(e);
        }
        return rs;
    }
    
    public void updateRoom(Room room){
        try{
            String str = "UPDATE room SET Bed = '"+room.getBed()+"', Type = '"+room.getType()+"', Cost = '"+room.getCost()+"', Status = '"+room.getStatus()+"'where ID = '"+room.getId()+"'"; 
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "This Room has been updated successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateRoomStatus(Room room){
        try{
            String str = "UPDATE room SET Status = '"+room.getStatus()+"'where ID = '"+room.getId()+"'"; 
            conn.s.executeUpdate(str);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateRoom_non_identify(Room room){
        try{
            String str = "UPDATE room SET Bed = '"+room.getBed()+"', Type = '"+room.getType()+"', Cost = '"+room.getCost()+"', Status = '"+room.getStatus()+"'where ID = '"+room.getId()+"'"; 
            conn.s.executeUpdate(str);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteRoom(Room room){
        try{
            String str = "DELETE FROM room WHERE ID = '"+room.getId()+"'"; 
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "This room has been deleted successfully!");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
