/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Customer {
    private String id, name, phone, gender, address, dateStart, dateEnd, checked;
    private String id_room, type;
    private int bed;

    public Customer() {
        this.id = this.name = this.phone = this.gender = this.address = this.dateStart = this.dateEnd = this.checked = "";
        this.id_room = this.type = "";
        this.bed = 0;
    }

    public Customer(String id, String name, String phone, String address, String gender, String dateStart, 
            String dateEnd, String checked, String id_room, String type, int bed) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.checked = checked;
        this.id_room = id_room;
        this.type = type;
        this.bed = bed;
    }

    public String getId_room() {
        return id_room;
    }

    public String getType() {
        return type;
    }

    public int getBed() {
        return bed;
    }

    public void setId_room(String id_room) {
        this.id_room = id_room;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getChecked() {
        return checked;
    }
    
    public long getDayCount(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long getDiff = 0;
        try {
            String startDate = getDateStart();
            String endDate = getDateEnd();
            Date date1 = (Date) simpleDateFormat.parse(startDate);
            Date date2 = (Date) simpleDateFormat.parse(endDate);
            getDiff = (long) (date2.getTime() - date1.getTime());
            getDiff /= (long)(24 * 60 * 60 * 1000);
            if(getDiff == 0) getDiff = 1;
            return getDiff;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    
    @Override
    public String toString(){
        return id + " " + name + " " + phone + " ";
    }
    
//    public static void main(String[] args) {
//        Customer x = new Customer("A", "A", "A", "A", "Male", "2022-11-14", "2022-11-14", "Checked-In", "101", "C", 1);
//        System.out.println(x.getDayCount());
//    }
}
