    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package nba;

/**
 *
 * @author samyee
 */
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

public class SearchingActivePlayerRoster extends javax.swing.JFrame {
    Connection con;
    /**
     * Creates new form SearchingActivePlayerRoster
     */
    public SearchingActivePlayerRoster() {
        initComponents();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "root123NBA.");
            System.out.println("Connected");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NBA_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        position1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlayerList = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 0, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(969, 760));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\home (2).png")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 20, 40, 40));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setText("is just a setup for a");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, 23));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setText("Your current setback");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 249, -1, 23));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setText("positive and focused");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 364, -1, 23));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel11.setText("comeback. Stay");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 331, -1, 23));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel12.setText("during your recovery!");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 405, -1, 23));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\800px-Back-Arrowsvg-1.png")); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 40, 40));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 900));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 48)); // NOI18N
        jLabel4.setText("Player Roster Searching");
        jPanel9.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 750, 70));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 840, 90));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Enter Player ID :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 238, -1, -1));

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idKeyTyped(evt);
            }
        });
        jPanel3.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 241, 121, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Position :");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(687, 231, 77, 34));

        position1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guard", "Center", "Forward" }));
        position1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                position1ActionPerformed(evt);
            }
        });
        jPanel3.add(position1, new org.netbeans.lib.awtextra.AbsoluteConstraints(768, 241, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Player List");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 367, 86, 25));

        tbPlayerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Age", "Height", "Weight", "Position", "Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbPlayerList);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 398, 760, 440));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\icons8-search-50 (1).png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 323, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jMainMenu mp = new jMainMenu();
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DynamicSearchingPage dy=new DynamicSearchingPage();
        dy.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyTyped

    }//GEN-LAST:event_idKeyTyped

    private void position1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_position1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_position1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFrame frame = new JFrame("Position Check"); // search button
        String sql1 = "SELECT * FROM san_antonio WHERE Player_ID= ? AND Position LIKE ?";

        try {
            DefaultTableModel model = (DefaultTableModel) tbPlayerList.getModel();
            model.setRowCount(0);

            String idd = id.getText().trim();

            // Check for empty input
            if (idd.isEmpty()) {
                JOptionPane.showMessageDialog(frame.getRootPane(), "Empty Height");
                return;
            }
            

            // Parse the input values
            int iddd = Integer.parseInt(idd);


            // Get selected items from combo boxes
            String pos1 = (String) position1.getSelectedItem();

            

            // Prepare and execute the query based on the selected positions
            PreparedStatement stmt = null;
            if (pos1.equals("Center")) {
                // position1 is not null and position2 is none
                JOptionPane.showMessageDialog(frame.getRootPane(), "Find attributes : Player ID = " + iddd + " Position =" + pos1);
                String position1Mapped = mapPosition(pos1);
                stmt = con.prepareStatement(sql1);
                stmt.setInt(1, iddd);
                stmt.setString(2, "%" + position1Mapped + "%");
            } else if (pos1.equals("Forward")) {
                // position1 is not null and position2 is none
                JOptionPane.showMessageDialog(frame.getRootPane(), "Find attributes : Player ID = " + iddd + " Position =" + pos1);
                String position1Mapped = mapPosition(pos1);
                stmt = con.prepareStatement(sql1);
                stmt.setInt(1, iddd);
                stmt.setString(2, "%" + position1Mapped + "%");
            }
            else if (pos1.equals("Guard")) {
                // position1 is not null and position2 is none
                JOptionPane.showMessageDialog(frame.getRootPane(), "Find attributes : Player ID = " + iddd + " Position =" + pos1);
                String position1Mapped = mapPosition(pos1);
                stmt = con.prepareStatement(sql1);
                stmt.setInt(1, iddd);
                stmt.setString(2, "%" + position1Mapped + "%");
            }
            else
            {
                 JOptionPane.showMessageDialog(frame.getRootPane(), "Select a Position1");
            }

            // Execute the query and update the table model
            if (stmt != null) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("Player_ID"),
                        rs.getString("Player_Name"),
                        rs.getInt("Age"),
                        rs.getString("Height"),
                        rs.getInt("Weight"),
                        rs.getString("Position"),
                        rs.getInt("Salary")
                    });
                }
            }
        } catch (NumberFormatException e) {
            // Handle the exception if parsing fails
            JOptionPane.showMessageDialog(frame.getRootPane(), "Please enter valid numbers for ID and position.");
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(SearchingActivePlayerRoster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchingActivePlayerRoster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchingActivePlayerRoster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchingActivePlayerRoster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchingActivePlayerRoster().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> position1;
    private javax.swing.JTable tbPlayerList;
    // End of variables declaration//GEN-END:variables
}
