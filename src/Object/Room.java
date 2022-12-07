/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

/**
 *
 * @author admin
 */
public class Room {
    private String id, type, status;
    private int cost, bed;

    public Room() {
        this.id = this.type = this.status = "";
        this.cost = this.bed = 0;
    }
    
    public Room(String id, String type, String status, int cost, int bed) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.cost = cost;
        this.bed = bed;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public int getCost() {
        return cost;
    }

    public int getBed() {
        return bed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }
    
    @Override
    public String toString(){
        return id + " " + type + " " + bed + " " + cost + " " + status;
    }
}
