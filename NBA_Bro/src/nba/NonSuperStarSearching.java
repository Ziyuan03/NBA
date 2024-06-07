/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package nba;

/**
 *
 * @author samyee
 */
import java.awt.Image;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class NonSuperStarSearching extends javax.swing.JFrame {
    Connection con;
    /**
     * Creates new form NonSuperStarSearching
     */
    public NonSuperStarSearching() {
        initComponents();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "root123NBA.");
            System.out.println("Connected");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NBA_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setImageMethod("src\\icons\\home (2).png", btnHome);
        setImageMethod("src\\icons\\800px-Back-Arrowsvg-1.png", btnBack);
        setImageMethod("src\\icons\\icons8-search-50 (1).png", btnSearch);        
    }
    
    //Method set label image
    public void setImageMethod(String imagePath, JButton labelName) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        labelName.setIcon(scaledIcon);            
    }
    
    public static String convertHeightToFeetInches(double heightMeters) {
        int totalInches = (int) Math.round(heightMeters * 39.37);
        int feet = totalInches / 12;
        int inches = totalInches % 12;
        return String.format("%d-%02d", feet, inches);
    }
    
    public static String mapPosition(String fullPosition) {
        switch (fullPosition.toLowerCase()) {
            case "forward":
                return "F";
            case "guard":
                return "G";
            case "center":
                return "C";
            default:
                return fullPosition;  // Return input if no mapping found
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        minheight = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        position1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        maxweight = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        position2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlayerList = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 0));

        btnHome.setBackground(new java.awt.Color(255, 204, 204));
        btnHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setText("is just a setup for a");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setText("Your current setback");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setText("positive and focused");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel11.setText("comeback. Stay");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel12.setText("during your recovery!");

        btnBack.setBackground(new java.awt.Color(255, 204, 204));
        btnBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(189, 189, 189)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(471, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 48)); // NOI18N
        jLabel5.setText("Non-SuperStar Searching");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(42, 42, 42))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 840, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Enter Height (m) :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 211, -1, -1));

        minheight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minheightActionPerformed(evt);
            }
        });
        minheight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                minheightKeyTyped(evt);
            }
        });
        jPanel1.add(minheight, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 214, 121, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Position1 :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 275, 87, 34));

        position1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guard", "Center", "Forward" }));
        position1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                position1ActionPerformed(evt);
            }
        });
        jPanel1.add(position1, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 285, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Enter Weight (kg) :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(634, 211, 145, -1));

        maxweight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxweightActionPerformed(evt);
            }
        });
        jPanel1.add(maxweight, new org.netbeans.lib.awtextra.AbsoluteConstraints(789, 214, 121, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Position2 :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 275, 96, 34));

        position2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guard", "Center", "Forward", "None" }));
        position2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                position2ActionPerformed(evt);
            }
        });
        jPanel1.add(position2, new org.netbeans.lib.awtextra.AbsoluteConstraints(789, 285, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Player List");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 377, 86, 25));

        tbPlayerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Age", "Height", "Weight", "Position", "Salary", "Points", "Rebounds", "Assists", "Steals", "Blocks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbPlayerList);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 810, 420));

        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        jMainMenu mp = new jMainMenu();
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        SearchingAllPlayers ac=new SearchingAllPlayers();
        ac.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void minheightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minheightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minheightActionPerformed

    private void minheightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_minheightKeyTyped

    }//GEN-LAST:event_minheightKeyTyped

    private void position1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_position1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_position1ActionPerformed

    private void maxweightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxweightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxweightActionPerformed

    private void position2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_position2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_position2ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        JFrame frame = new JFrame("Position Check"); // search button
        String sql2 = "SELECT * FROM candidate_listt WHERE Height >= ? AND Weight <= ? AND (Position LIKE ? AND Position LIKE ?) AND SALARY=1000";
        String sql1 = "SELECT * FROM candidate_listt WHERE Height >= ? AND Weight <= ? AND Position LIKE ? AND SALARY=1000";

        try {
            DefaultTableModel model = (DefaultTableModel) tbPlayerList.getModel();
            model.setRowCount(0);

            String minHeightText = minheight.getText().trim();
            String maxWeightText = maxweight.getText().trim();

            // Check for empty input
            if (minHeightText.isEmpty()) {
                JOptionPane.showMessageDialog(frame.getRootPane(), "Empty Height");
                return;
            }
            if (maxWeightText.isEmpty()) {
                JOptionPane.showMessageDialog(frame.getRootPane(), "Empty Weight");
                return;
            }

            // Parse the input values
            Double height = Double.parseDouble(minHeightText);
            Double weight = Double.parseDouble(maxWeightText);

            // Convert height to feet and inches
            String minHeight = convertHeightToFeetInches(height);
            double maxWeight = weight;

            // Get selected items from combo boxes
            String pos1 = (String) position1.getSelectedItem();
            String pos2 = (String) position2.getSelectedItem();

            // Ensure combo box selections are valid
            if (pos1 == null) {
                JOptionPane.showMessageDialog(frame.getRootPane(), "Select a Position1");
                return;
            }
            if (pos2 == null) {
                JOptionPane.showMessageDialog(frame.getRootPane(), "Select a Position2");
                return;
            }

            // Prepare and execute the query based on the selected positions
            PreparedStatement stmt = null;
            if (pos2.equals("None")) {
                // position1 is not null and position2 is none
                JOptionPane.showMessageDialog(frame.getRootPane(), "Find attributes : Height,>= " + minHeight + " ,Weight,<=," + maxWeight + "kg,Position,-," + pos1);
                String position1Mapped = mapPosition(pos1);
                stmt = con.prepareStatement(sql1);
                stmt.setString(1, minHeight);
                stmt.setDouble(2, maxWeight);
                stmt.setString(3, "%" + position1Mapped + "%");
            } else {
                // position1 is not null and position2 is not none
                JOptionPane.showMessageDialog(frame.getRootPane(), "Find attributes : Height,>= " + minHeight + " ,Weight,<=," + maxWeight + "kg,Position1,-," + pos1 + ",Position2,-," + pos2);
                String position1Mapped = mapPosition(pos1);
                String position2Mapped = mapPosition(pos2);
                stmt = con.prepareStatement(sql2);
                stmt.setString(1, minHeight);
                stmt.setDouble(2, maxWeight);
                stmt.setString(3, "%" + position1Mapped + "%");
                stmt.setString(4, "%" + position2Mapped + "%");
            }

            // Execute the query and update the table model
            if (stmt != null) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("Player_Name"),
                        rs.getInt("Age"),
                        rs.getString("Height"),
                        rs.getDouble("Weight"),
                        rs.getString("Position"),
                        rs.getString("Salary"),
                        rs.getDouble("Points"),
                        rs.getDouble("Rebounds"),
                        rs.getDouble("Assists"),
                        rs.getDouble("Steals"),
                        rs.getDouble("Blocks")
                    });
                }
            }
        } catch (NumberFormatException e) {
            // Handle the exception if parsing fails
            JOptionPane.showMessageDialog(frame.getRootPane(), "Please enter valid numbers for height and weight.");
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NonSuperStarSearching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NonSuperStarSearching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NonSuperStarSearching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NonSuperStarSearching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NonSuperStarSearching().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maxweight;
    private javax.swing.JTextField minheight;
    private javax.swing.JComboBox<String> position1;
    private javax.swing.JComboBox<String> position2;
    private javax.swing.JTable tbPlayerList;
    // End of variables declaration//GEN-END:variables
}