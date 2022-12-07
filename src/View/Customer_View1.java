/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import DataBase.DatabaseCustomer;
import DataBase.DatabaseRoom;
import DataBase.connect;
import Object.Customer;
import Object.DateLabelFormatter;
import Object.Room;
import com.toedter.calendar.JDateChooser;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author admin
 */
public class Customer_View1 extends javax.swing.JInternalFrame{

    /**
     * Creates new form Driver_View
     */
    // create List<Customer> list = new ArrayList<>();
    private DatabaseCustomer dbc = new DatabaseCustomer();
    private DatabaseRoom dbr = new DatabaseRoom();
    private ButtonGroup btn;
    
    public Customer_View1() {
        initComponents();
        // colunm's size of table
//        jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
//        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        
        // Button Group for gender.
        btn = new ButtonGroup();
        btn.add(jRadioButton1);
        btn.add(jRadioButton2);
//        
//        DateModel modelDate = new UtilDateModel();
//        modelDate.setSelected(true);
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//
//        JPanel datePanel = new JDatePanelImpl(modelDate, p);
//        JDatePicker datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }
    // not done
    
    private void ResetTable(){
        ResultSet res = (ResultSet)dbc.showCustomer();
        jTable1.setModel(DbUtils.resultSetToTableModel(res));
    }
    private void Customer_Update(){
        try {
            String id = jTextField1.getText();
            String name = jTextField2.getText();
            String phone = jTextField3.getText();
            String address = jTextField4.getText();
            String gender = null;
            if(jRadioButton1.isSelected()){
                gender = "Male";
            }
            else if(jRadioButton2.isSelected()){
                gender = "Female";
            }
            
            String dateStart  = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
            String dateEnd  = ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
            String id_room = jTextField5.getText();
            String checked_in_out = "";
            if(gender == null || id.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty() || dateStart.isEmpty() || dateEnd.isEmpty() || id_room.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill in all blanks");
                return;
            }
            ResultSet ress = dbc.showCustomer();
            List<Customer> listcustomer = new ArrayList<>();
            while(ress.next()){
                listcustomer.add(new Customer(ress.getString("ID"), ress.getString("Name"), ress.getString("Phone_Number"), ress.getString("Address"),
                ress.getString("Gender"), ress.getString("DateStart"), ress.getString("DateEnd"), ress.getString("Checked"), ress.getString("ID_Room"),
                ress.getString("Type"), ress.getInt("Bed")));
            }
            for (Customer cuss : listcustomer){
                if(cuss.getId().compareTo(id) == 0){
                    checked_in_out = cuss.getChecked();
                }
            }
            ResultSet rs = dbc.showCustomer();
            String pre_id_room = "";
            while(rs.next()){
                if(rs.getString("ID").compareTo(id) == 0){
                    pre_id_room = rs.getString("ID_Room");
                    break;
                }
                //listcustomer.add(new Customer(rs.getString("ID"), rs.getString("Name"), rs.getString("Phone_Number"), rs.getString("Address"), rs.getString("Gender"), rs.getString("DateStart"), rs.getString("DateEnd"), rs.getString("Checked"), rs.getString("ID_Room"), rs.getString("Type"), rs.getInt("Bed")));
            }
            
            ResultSet res = dbr.showRoom();
            List<Room> listroom = new ArrayList<>();
            while(res.next()){
                listroom.add(new Room(res.getString("ID"), res.getString("Type"), res.getString("Status"), res.getInt("Cost"), res.getInt("Bed")));
            }
            
            // Pre != now
            if(pre_id_room.compareTo(id_room) != 0){
                for(Room m : listroom){
                    if(m.getId().compareTo(pre_id_room) == 0){
                        dbr.updateRoomStatus(new Room(m.getId(), m.getType(), "Available", m.getCost(), m.getBed()));
                        break;
                    }
                }
                boolean ok = false;
                for(Room m: listroom){
                    if(m.getId().compareTo(id_room) == 0 && m.getStatus().compareTo("Available") == 0){
                        Customer customer = new Customer(id, name, phone, address, gender, dateStart, dateEnd, checked_in_out, m.getId(), m.getType(), m.getBed());
                        dbc.updateCustomer(customer);
                        dbr.updateRoom_non_identify(new Room(m.getId(), m.getType(), "Occupied", m.getCost(), m.getBed()));
                        ok = true;
                        break;
                    }
                }
                if(!ok){
                    JOptionPane.showMessageDialog(null, "Room has been Occupied!");
                    return;
                }
            // pre = now
            }else{
                for(Room m: listroom){
                    if(m.getId().compareTo(pre_id_room) == 0 && checked_in_out.compareTo("Checked-In") == 0){
                        Customer customer = new Customer(id, name, phone, address, gender, dateStart, dateEnd, checked_in_out, m.getId(), m.getType(), m.getBed());
                        dbc.updateCustomer(customer);
                    }
                }
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(Customer_View1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Customer_Create() throws ParseException{
        try {
            String id = jTextField1.getText();
            String name = jTextField2.getText();
            String phone = jTextField3.getText();
            String address = jTextField4.getText();
            String gender = null;
            if(jRadioButton1.isSelected()){
                gender = "Male";
            }
            else if(jRadioButton2.isSelected()){
                gender = "Female";
            }
            
            String dateStart  = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
            String dateEnd  = ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
            String checked_in_out = "Checked-In";
            
            String id_room = jTextField5.getText();
 
            
            if(gender == null || id.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty() || dateStart.isEmpty() || dateEnd.isEmpty() || id_room.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill in all blanks");
                return;
            }
            
            ResultSet ress = dbc.showCustomer();
            List<Customer> listcustomer = new ArrayList<>();
            while(ress.next()){
                listcustomer.add(new Customer(ress.getString("ID"), ress.getString("Name"), ress.getString("Phone_Number"), ress.getString("Address"),
                ress.getString("Gender"), ress.getString("DateStart"), ress.getString("DateEnd"), ress.getString("Checked"), ress.getString("ID_Room"),
                ress.getString("Type"), ress.getInt("Bed")));
            }
            for (Customer cuss : listcustomer){
                if(cuss.getId().compareTo(id) == 0){
                    JOptionPane.showMessageDialog(null, "Customer's ID has been set up!");
                    return;
                }
            }
                   
            ResultSet res = dbr.showRoom();
            List<Room> listroom = new ArrayList<>();
            while(res.next()){
                listroom.add(new Room(res.getString("ID"), res.getString("Type"), res.getString("Status"), res.getInt("Cost"), res.getInt("Bed")));
            }
            boolean ok = false;
            for(Room m: listroom){
                if(m.getId().compareTo(id_room) == 0 && m.getStatus().compareTo("Available") == 0){
                    Customer customer = new Customer(id, name, phone, address, gender, dateStart, dateEnd, checked_in_out, m.getId(), m.getType(), m.getBed());
                    dbc.insertCustomer(customer);
                    dbr.updateRoom_non_identify(new Room(m.getId(), m.getType(), "Occupied", m.getCost(), m.getBed()));
                    ok = true;
                    break;
                }
            }
            if(!ok){
                JOptionPane.showMessageDialog(null, "Room has been Occupied!");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_View1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void Customer_Reset(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jDateChooser1.setCalendar(null);
        jDateChooser2.setCalendar(null);
    }
    
    private void Customer_Delete(){
        try{
            String id = jTextField1.getText();
            String name = jTextField2.getText();
            String phone = jTextField3.getText();
            String address = jTextField4.getText();
            String gender = null;
            if(jRadioButton1.isSelected()){
                gender = "Male";
            }
            else if(jRadioButton2.isSelected()){
                gender = "Female";
            }
            String dateStart  = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
            String dateEnd  = ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
            String checked_in_out = "Checked-In";
            String id_room = jTextField5.getText();
            
            if(id.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill in ID!");
                return;
            
            }
            ResultSet res = dbr.showRoom();
            List<Room> listroom = new ArrayList<>();
            while(res.next()){
                listroom.add(new Room(res.getString("ID"), res.getString("Type"), res.getString("Status"), res.getInt("Cost"), res.getInt("Bed")));
            }
            
            boolean ok = false;
            for(Room m: listroom){
                if(m.getId().compareTo(id_room) == 0){
                    Customer customer = new Customer(id, name, phone, address, gender, dateStart, dateEnd, checked_in_out, m.getId(), m.getType(), m.getBed());
                    dbc.deleteCustomer(customer);
                    dbr.updateRoomStatus(new Room(m.getId(), m.getType(), "Available", m.getCost(), m.getBed()));
                    ok = true;
                    break;
                }
            }
            if(!ok){
                JOptionPane.showMessageDialog(null, "Customer does not exist!");
                return;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Customer_View1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        loaddata_button = new javax.swing.JButton();
        name_search = new javax.swing.JTextField();
        search_label = new javax.swing.JLabel();
        add_button = new javax.swing.JButton();
        reset_button1 = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        update_button = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setIconifiable(true);
        setTitle("Customer Options");
        setPreferredSize(new java.awt.Dimension(1188, 621));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Phone_Number", "Address", "Gender", "DateStart", "DateEnd", "Checked", "ID_Room", "Type", "Bed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        loaddata_button.setText("Load Data");
        loaddata_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loaddata_buttonActionPerformed(evt);
            }
        });

        search_label.setText("Search by name: ");

        add_button.setText("Add");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        reset_button1.setText("Reset");
        reset_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_button1ActionPerformed(evt);
            }
        });

        delete_button.setText("Delete");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        jLabel1.setText("ID: ");

        jLabel2.setText("Name: ");

        jLabel3.setText("Phone:");

        jLabel4.setText("Address:");

        jLabel5.setText("DateStart:");

        jLabel6.setText("Gender: ");

        jLabel7.setText("DateEnd:");

        jRadioButton1.setText("Male");

        jRadioButton2.setText("Female");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Customer Form");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Name", "DateStart", "DateEnd", "ID" }));

        update_button.setText("Update");
        update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_buttonActionPerformed(evt);
            }
        });

        jLabel9.setText("ID Room:");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField1)
                                            .addComponent(jTextField2)
                                            .addComponent(jTextField3)
                                            .addComponent(jTextField4)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 35, Short.MAX_VALUE))
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(reset_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)))
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loaddata_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
                        .addComponent(search_label)
                        .addGap(18, 18, 18)
                        .addComponent(name_search, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loaddata_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(delete_button)
                        .addGap(18, 18, 18)
                        .addComponent(reset_button1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(add_button)
                        .addGap(18, 18, 18)
                        .addComponent(update_button)))
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loaddata_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loaddata_buttonActionPerformed
        // TODO add your handling code here:
        ResetTable();
    }//GEN-LAST:event_loaddata_buttonActionPerformed

    private void update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_buttonActionPerformed
        // TODO add your handling code here:
        Customer_Update();
//        ResetTable();
    }//GEN-LAST:event_update_buttonActionPerformed

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        try {
            // TODO add your handling code here:
            Customer_Create();
//            ResetTable();
        } catch (ParseException ex) {
            Logger.getLogger(Customer_View1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_add_buttonActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        // TODO add your handling code here:
        Customer_Delete();
//        ResetTable();
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void reset_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_button1ActionPerformed
        // TODO add your handling code here:
        Customer_Reset();
//        ResetTable();
    }//GEN-LAST:event_reset_button1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1 && jTable1.getSelectedRow() != -1){
            int index = jTable1.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            index = jTable1.convertRowIndexToModel(index);
            
            String id = model.getValueAt(index, 0).toString();
            String name = model.getValueAt(index, 1).toString();
            String phone = model.getValueAt(index, 2).toString();
            String address = model.getValueAt(index, 3).toString();
            String gender = model.getValueAt(index, 4).toString();
            
            String dateStart = model.getValueAt(index, 5).toString();
            String dateEnd = model.getValueAt(index, 6).toString();
            String id_room = model.getValueAt(index, 8).toString();
            
            jTextField1.setText(id);
            jTextField2.setText(name);
            jTextField3.setText(phone);
            jTextField4.setText(address);
            
            if(gender.compareTo("Male") == 0){
                jRadioButton1.setSelected(true);
            }else {
                jRadioButton2.setSelected(true);
            }
            
            try {
                jDateChooser1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart));
            } catch (ParseException ex) {
                Logger.getLogger(Customer_View1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                jDateChooser2.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd));
            } catch (ParseException ex) {
                Logger.getLogger(Customer_View1.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTextField5.setText(id_room);
            
            
            
            
            
        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_button;
    private javax.swing.JButton delete_button;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton loaddata_button;
    private javax.swing.JTextField name_search;
    private javax.swing.JButton reset_button1;
    private javax.swing.JLabel search_label;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables

    public void setVisiable(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
