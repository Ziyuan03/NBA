/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package nba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;

/**
 *
 * @author Ziyua
 */
public class InjuryPlayerAdd extends javax.swing.JFrame {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root123NBA.";
    private static final String DATA_CON = "jdbc:mysql://localhost:3306/nba";
    private BtnInjuryOrRoster btnInjuryRoster = new BtnInjuryOrRoster();

    /**
     * Creates new form InjuryPlayerAddDelete
     */
    public InjuryPlayerAdd() {
        initComponents();
        loadInjuryData();
        initializeDB();
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(190);
    }

    private void initializeDB() {
        String createTableSQL = """
        CREATE TABLE IF NOT EXISTS injuryplayerroster (
            Player_ID INT PRIMARY KEY,
            Player_Name VARCHAR(45) NOT NULL,
            Injury_Status VARCHAR(45) NOT NULL,
            Injury_Date VARCHAR(45) NOT NULL,
            Age INT NOT NULL,
            Height VARCHAR(45) NOT NULL,
            Weight INT NOT NULL,
            Position VARCHAR(45) NOT NULL,
            insertion_time TIMESTAMP NOT NULL
        );
        """;

        try (Connection con = DriverManager.getConnection(DATA_CON, USERNAME, PASSWORD); Statement stmt = con.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table checked/created successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database initialization error: " + ex.getMessage());
        }
    }

    private void loadInjuryData() {
        System.out.println("Loading injury data...");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        String query = "SELECT Player_ID, Player_Name, Age, Height, Weight, Position FROM san_antonio "
                + "WHERE Player_ID NOT IN (SELECT Player_ID FROM injuryplayerroster)";

        try (Connection con = DriverManager.getConnection(DATA_CON, USERNAME, PASSWORD); PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Player_ID"),
                    rs.getString("Player_Name"),
                    rs.getInt("Age"),
                    rs.getString("Height"),
                    rs.getInt("Weight"),
                    rs.getString("Position")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
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
        back = new javax.swing.JButton();
        activeRoster = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        view = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        InjuryDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        Refresh = new javax.swing.JButton();
        InjuryStatus = new javax.swing.JTextField();
        ADD = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(180, 760));

        back.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\home (2).png")); // NOI18N
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        activeRoster.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\icons8-basketball-48_1.png")); // NOI18N
        activeRoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeRosterActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Active Player Roster");

        view.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\eyes (1).png")); // NOI18N
        view.setPreferredSize(new java.awt.Dimension(81, 57));
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("View Injury Roster");

        delete.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\8673507_ic_fluent_person_delete_filled_icon (1).png")); // NOI18N
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Delete Player");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(view, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(activeRoster, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(115, 115, 115)))
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(back)
                .addGap(139, 139, 139)
                .addComponent(activeRoster, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(14, 14, 14)
                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 760));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Player ID", "Player Name ", "Age", "Height", "Weight", "Position"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 720, 330));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 640, 70, 30));
        jPanel1.add(InjuryDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 650, 100, 30));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Injury Date: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 650, -1, -1));
        jPanel1.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 650, 100, 30));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 570, -1, -1));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Injury Status: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 570, -1, -1));
        jPanel1.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 570, 100, 30));

        Refresh.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\8665833_rotate_refresh_icon (1).png")); // NOI18N
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });
        jPanel1.add(Refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 490, 50, 30));
        jPanel1.add(InjuryStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 570, 100, 30));

        ADD.setText("ADD");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });
        jPanel1.add(ADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 600, 60, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Copperplate Gothic Bold", 2, 48)); // NOI18N
        jLabel5.setText("Active Player Roster");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 750, 90));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        InjuryPlayerMainPage mp = new InjuryPlayerMainPage();
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            ID.setText(model.getValueAt(selectedRow, 0).toString());
            Name.setText(model.getValueAt(selectedRow, 1).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
        // TODO add your handling code here
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a player from the table.");
            return;
        }

        String id = ID.getText();
        String name = Name.getText();
        String injuryStatus = InjuryStatus.getText();
        String injuryDate = InjuryDate.getText();

        // Validate input here if necessary
        if (id.isEmpty() || name.isEmpty() || injuryStatus.isEmpty() || injuryDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Insert data into database
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "root123NBA."); PreparedStatement pst = con.prepareStatement("INSERT INTO injuryplayerroster (Player_ID, Player_Name, Injury_Status, Injury_Date, Age, Height, Weight, Position, insertion_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, injuryStatus);
            pst.setString(4, injuryDate);
            pst.setInt(5, Integer.parseInt(jTable1.getValueAt(selectedRow, 2).toString())); // Age
            pst.setString(6, jTable1.getValueAt(selectedRow, 3).toString()); // Height
            pst.setInt(7, Integer.parseInt(jTable1.getValueAt(selectedRow, 4).toString())); // Weight
            pst.setString(8, jTable1.getValueAt(selectedRow, 5).toString()); // Position
            pst.setTimestamp(9, new java.sql.Timestamp(new java.util.Date().getTime()));

            int affectedRows = pst.executeUpdate();

            //Modify the Injury_Status in SAN_ANTONIO table
            Statement stmt = con.createStatement();
            stmt.execute("UPDATE SAN_ANTONIO SET Injury_Status = '" + injuryStatus + "' WHERE Player_ID = " + id);

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Record added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add record.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
        loadInjuryData();
    }//GEN-LAST:event_ADDActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        // TODO add your handling code here:
        loadInjuryData(); // Reload data to reflect current database status
        JOptionPane.showMessageDialog(this, "Table refreshed. Injured players are now excluded.");
    }//GEN-LAST:event_RefreshActionPerformed

    private void activeRosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeRosterActionPerformed
        //Set btn Stored to go back to injury player main page after view active roster
        btnInjuryRoster.setBtnInjuryRoster("Injury_Add");

        jViewRoster ar = new jViewRoster(btnInjuryRoster);
        ar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_activeRosterActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        InjuryPlayerDelete dlt = new InjuryPlayerDelete();
        dlt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_deleteActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        // TODO add your handling code here:
        InjuryPlayerView view = new InjuryPlayerView();
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_viewActionPerformed

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
            java.util.logging.Logger.getLogger(InjuryPlayerAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InjuryPlayerAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InjuryPlayerAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InjuryPlayerAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InjuryPlayerAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField InjuryDate;
    private javax.swing.JTextField InjuryStatus;
    private javax.swing.JTextField Name;
    private javax.swing.JButton Refresh;
    private javax.swing.JButton activeRoster;
    private javax.swing.JButton back;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
