/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import DataBase.DatabaseRoom;
import DataBase.connect;
import Object.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author admin
 */
public class Room_View extends javax.swing.JInternalFrame{

    /**
     * Creates new form Driver_View
     */
    // create List<Customer> list = new ArrayList<>();
    final private String [] room_type = {"A", "B", "C"};
    final private String [] bed_count = {"1", "2"};
    final private String [] stt_type = {"Available", "Occupied"};
    DatabaseRoom dbr = new DatabaseRoom();
//    DatabaseUser dbu = new DatabaseUser();
//    DatabaseCustoemr dbc = new DatabaseCustomer();
    public Room_View() {
        initComponents();
        ResetField();
    }
    
    private void ResetField(){
        jTextField5.setText("");
        jTextField6.setText("");
    }

    private void ResetTable(){
        ResultSet res = (ResultSet)dbr.showRoom();
        jTable1.setModel(DbUtils.resultSetToTableModel(res));
    }
    
    private void Update_Room(){
        String id = jTextField5.getText();
        String status = (String)jComboBox2.getSelectedItem();
        String type = (String)jComboBox3.getSelectedItem();
        String css = jTextField6.getText();
        int bed = Integer.valueOf((String)jComboBox4.getSelectedItem());
        if(id.isEmpty() || css.isEmpty()){
            JOptionPane.showMessageDialog(null, "Fill in blanks");
            return;
        }
        int cost = Integer.valueOf(css);
        List<Room> listroom = new ArrayList<>();
        ResultSet res = dbr.showRoom();
        try {
            while(res.next()){
                listroom.add(new Room(res.getString("ID"), res.getString("Type"), res.getString("Status"), res.getInt("Cost"), res.getInt("Bed")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room_View.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean ok = true;
        for(Room rm: listroom){
            if(rm.getId().compareTo(id) == 0){
                dbr.updateRoom(new Room(id, type, status, cost, bed));
                ok = false;
                break;
            }
        }
        if(ok){
            JOptionPane.showMessageDialog(null, "This id room does not exist!");
        }
    }
    
    private void Add_Room(){
//        if(evt.getSource() == add_button){
        String id_room = jTextField5.getText();
        String css = jTextField6.getText();
        String status = (String)jComboBox2.getSelectedItem();
        String type = (String)jComboBox3.getSelectedItem();
        int bed = Integer.parseInt((String)jComboBox4.getSelectedItem());
        if(id_room.isEmpty() || css.isEmpty()){
            System.out.println("Error!");
            JOptionPane.showMessageDialog(null, "Fill in all blanks");
            return;
        }
        int cost = Integer.parseInt(css);
        List<Room> listroom = new ArrayList<>();
        ResultSet res = dbr.showRoom();
        try {
            while(res.next()){
                listroom.add(new Room(res.getString("ID"), res.getString("Type"), res.getString("Status"), res.getInt("Cost"), res.getInt("Bed")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room_View.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Room rm: listroom){
            if(rm.getId().compareTo(id_room) == 0){
                JOptionPane.showMessageDialog(null, "ID room has been used!");
                return;
            }
        }
        Room room = new Room(id_room, type, status, cost, bed);
        dbr.insertRoom(room);
    }
    
    private void Delete_Room(){
        String id = (String)jTextField5.getText();
        String status = (String)jComboBox2.getSelectedItem();
        String type = (String)jComboBox3.getSelectedItem();
        int bed = Integer.valueOf((String)jComboBox4.getSelectedItem());
        String css = (String)jTextField6.getText();
        if(id.isEmpty() || css.isEmpty()){
            JOptionPane.showMessageDialog(null, "Fill in Id");
            return;
        }
        int cost = Integer.parseInt(css);
        Room room = new Room(id, type, status, cost, bed);
        dbr.deleteRoom(room);
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
        reset_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        update_button = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Room Options");
        setPreferredSize(new java.awt.Dimension(1188, 621));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Bed", "Type", "Cost", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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

        search_label.setText("Search by ID room:");

        add_button.setText("Add");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        reset_button.setText("Reset");
        reset_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_buttonActionPerformed(evt);
            }
        });

        delete_button.setText("Delete");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Room Form");

        update_button.setText("Update");
        update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_buttonActionPerformed(evt);
            }
        });

        jLabel9.setText("ID Room:");

        jTextField5.setToolTipText("");

        jLabel10.setText("Bed: ");

        jLabel11.setText("Type:");

        jLabel12.setText("Price/Day:");

        jTextField6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel13.setText("Status:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Occupied" }));
        jComboBox2.setKeySelectionManager(null);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));
        jComboBox3.setKeySelectionManager(null);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));
        jComboBox4.setKeySelectionManager(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loaddata_button)
                        .addGap(45, 45, 45)
                        .addComponent(search_label, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(name_search, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(386, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loaddata_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(add_button)
                        .addGap(18, 18, 18)
                        .addComponent(update_button))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(delete_button)
                        .addGap(18, 18, 18)
                        .addComponent(reset_button)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loaddata_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loaddata_buttonActionPerformed
        // TODO add your handling code here:
        ResetTable();
    }//GEN-LAST:event_loaddata_buttonActionPerformed

    private void update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_buttonActionPerformed
        // TODO add your handling code here:
        Update_Room();
//        ResetTable();
        ResetField();
    }//GEN-LAST:event_update_buttonActionPerformed

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        // TODO add your handling code here:
        Add_Room();
//        ResetTable();
        ResetField();
    }//GEN-LAST:event_add_buttonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1 && jTable1.getSelectedRow() != -1){
            int index = jTable1.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            index = jTable1.convertRowIndexToModel(index);
                    
            String id = model.getValueAt(index, 0).toString();
            String status = model.getValueAt(index, 4).toString();
            String bed = model.getValueAt(index, 1).toString();
            String type = model.getValueAt(index, 2).toString();
            String price = model.getValueAt(index, 3).toString();
            bed += "TEXT";
            type += "TEXT";
            jTextField5.setText(id);
            jTextField6.setText(price);
            if(status.compareTo("Occupied") == 0){
                jComboBox2.setSelectedIndex(1);
            }else {
                jComboBox2.setSelectedIndex(0);
            }
            
            if("2TEXT".equals(bed)){
                jComboBox4.setSelectedIndex(1);
            }else if("1TEXT".equals(bed)){
                jComboBox4.setSelectedIndex(0);
            }
            
            if(!"".equals(type))switch (type) {
                case "CTEXT":
                    jComboBox3.setSelectedIndex(2);
                    break;
                case "BTEXT":
                    jComboBox3.setSelectedIndex(1);
                    break;
                case "ATEXT":
                    jComboBox3.setSelectedIndex(0);
                    break;
                default:
                    break;
            }
//            
//            for(int i = 0; i < stt_type.length; ++i) 
//                if(status.compareTo(stt_type[i]) == 0) {
//                    jComboBox2.setSelectedItem(stt_type[i]);
//                }
//           
//            for(int i = 0; i < bed_count.length; ++i) 
//                if(bed.compareTo(bed_count[i]) == 0) {
//                    jComboBox4.setSelectedItem(bed_count[i]);
//                }
//            
//            for(int i = 0; i < room_type.length; ++i) 
//                if(type.compareTo(room_type[i]) == 0) {
//                    jComboBox3.setSelectedItem(room_type[i]);
//                }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        // TODO add your handling code here:
        Delete_Room();
//        ResetTable();
        ResetField();
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void reset_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_buttonActionPerformed
        // TODO add your handling code here:
        ResetField();
    }//GEN-LAST:event_reset_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_button;
    private javax.swing.JButton delete_button;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton loaddata_button;
    private javax.swing.JTextField name_search;
    private javax.swing.JButton reset_button;
    private javax.swing.JLabel search_label;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables

//    public static void main(String[] args) {
//        JFrame fr = new JFrame();
//        fr.setSize(1200, 800);
//        Room_View rv = new Room_View();
//        fr.add(rv);
//        fr.setVisible(true);
//        rv.show();
//    }
}
