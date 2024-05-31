package nba;
/*  Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author Yah Rou Jun
 */
public class ContractExtension extends javax.swing.JFrame {
    private Queue<Player> contractExtensionQueue;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/nba";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root123NBA.";
    

    private Connection conn;
    private PreparedStatement pstmt;
    /**
     * Creates new form ContractExtension
     */
 

public ContractExtension() {
    connectDatabase();
    createContractTable();
    initComponents();
    transferPointsData();
    if (connectDatabase()) {
        contractExtensionQueue = new PriorityQueue<>((p1, p2) -> p2.getPerformanceRating());
        loadContracts();
    } else {
        JOptionPane.showMessageDialog(this, "Failed to connect to the database. Please check your connection settings.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private boolean connectDatabase() {
    try {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connected to database!");
        return true;
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Failed to connect to database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

private void exportToPDF() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");
    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".pdf")) {
            filePath += ".pdf";
        }
        
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            PdfPTable pdfTable = new PdfPTable(jTable1.getColumnCount());
            // Add column headers
            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                pdfTable.addCell(jTable1.getColumnName(i));
            }
            // Add rows data
            for (int rows = 0; rows < jTable1.getRowCount(); rows++) {
                for (int cols = 0; cols < jTable1.getColumnCount(); cols++) {
                    Object value = jTable1.getModel().getValueAt(rows, cols);
                    pdfTable.addCell(value != null ? value.toString() : ""); // Handle null values
                }
            }

            document.add(new Paragraph("Data Exported from Injury Player Roster"));
            document.add(new Paragraph(" "));
            document.add(pdfTable);
            document.close();
            JOptionPane.showMessageDialog(this, "PDF was created successfully at " + filePath);
        } catch (DocumentException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Error creating PDF: " + ex.getMessage(), "PDF Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void createContractTable() {
    String createTableSQL = "CREATE TABLE IF NOT EXISTS contract ("
            + "ranking INT NOT NULL, "
            + "Player_ID VARCHAR(255) PRIMARY KEY, "
            + "Player_Name VARCHAR(255) NOT NULL, "
            + "Salary INT NOT NULL, "
            + "Points INT NOT NULL DEFAULT 0, "
            + "Status VARCHAR(255) NOT NULL)";
     try (Statement stmt = conn.createStatement()) {
        stmt.execute(createTableSQL);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Failed to create contract table: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateFirstContract() {
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Database connection is not available.", "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    int rowCount = model.getRowCount();
    if (rowCount > 0) {
        String playerID = model.getValueAt(0, 1).toString();
        String playerName = model.getValueAt(0, 2).toString();

        try {
            String sql = "UPDATE contract SET Player_Name = ?, Status = 'Updated' WHERE Player_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playerName);
            pstmt.setString(2, playerID);
            pstmt.executeUpdate();
            System.out.println("Contract updated in database!");
            pstmt.close();
            loadContracts(); // Refresh contracts after update
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to update contract in database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No players to update.", "Update Error", JOptionPane.WARNING_MESSAGE);
    }
}


private boolean isContractInDatabase(int ranking, String contractID, String contractName) {
        boolean exists = false;
        try {
            String sql = "SELECT 1 FROM contract WHERE ranking = ? OR Player_ID = ? OR Player_Name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ranking);
            pstmt.setString(2, contractID);
            pstmt.setString(3, contractName);
            ResultSet rs = pstmt.executeQuery();
            exists = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to check contract in database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return exists;
    
    }
     

    private void addToQueue(Player player) {
        contractExtensionQueue.offer(player);
    }

    private Player removeFromQueue() {
        return contractExtensionQueue.poll();
    }
    
private void insertContract(int ranking, String playerID, String playerName) {
    if (!isContractInDatabase(ranking, playerID, playerName)) {
        try {
            String sql = "INSERT INTO contract (ranking, Player_ID, Player_Name, Salary, Points, Status) "
                    + "SELECT ranking, Player_ID, Player_Name, Salary, Points, 'In Progress' FROM ranking WHERE Player_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerID);
                pstmt.executeUpdate();
                System.out.println("Contract added to database!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add contract to database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        System.out.println("Contract already exists in the database.");
    }
}

private boolean checkIfContractExists(String contractID) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean exists = false;

    try {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String sql = "SELECT 1 FROM contract WHERE Player_ID = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, contractID);
        rs = pstmt.executeQuery();
        exists = rs.next();
    } catch (SQLException ex) {
        System.out.println("SQL error: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            System.out.println("SQL error during close: " + ex.getMessage());
        }
    }
    return exists;
}


   private void transferPointsData() {
    try {
        String transferDataSQL = "UPDATE contract c " +
                                 "JOIN ranking r ON c.Player_ID = r.Player_ID " +
                                 "SET c.Points = r.Points";
        try (Statement stmt = conn.createStatement()) {
            int rowsUpdated = stmt.executeUpdate(transferDataSQL);
            System.out.println(rowsUpdated + " rows updated in contract table for Points.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to transfer Points data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


    // Method to prioritize negotiations
    private void prioritizeNegotiations() {
        // Example: Display the next player in line for negotiations
        if (!contractExtensionQueue.isEmpty()) {
            Player nextPlayer = contractExtensionQueue.peek();
            System.out.println("Next player for negotiation: " + nextPlayer.getName());
        } else {
            System.out.println("No players in the contract extension queue.");
        }
    }
    
    // Player class representing a player with name and performance rating
    private static class Player {
        private String name;
        private int performanceRating;

        public Player(String name, int performanceRating) {
            this.name = name;
            this.performanceRating = performanceRating;
        }

        public String getName() {
            return name;
        }

        public int getPerformanceRating() {
            return performanceRating;
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

        ContractPDF = new javax.swing.JButton();
        ContractHome = new javax.swing.JButton();
        ContractUpdate = new javax.swing.JButton();
        ContractTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 255));
        setMinimumSize(new java.awt.Dimension(969, 790));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ContractPDF.setBackground(new java.awt.Color(255, 255, 255));
        ContractPDF.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        ContractPDF.setText("Explore PDF");
        ContractPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContractPDFActionPerformed(evt);
            }
        });
        getContentPane().add(ContractPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 700, 150, 30));

        ContractHome.setBackground(new java.awt.Color(255, 255, 255));
        ContractHome.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        ContractHome.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ziyua\\OneDrive\\Documents\\NetBeansProjects\\NBA_GManager\\NBA\\NBA\\src\\icons\\home (2).png")); // NOI18N
        ContractHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContractHomeActionPerformed(evt);
            }
        });
        getContentPane().add(ContractHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        ContractUpdate.setBackground(new java.awt.Color(255, 255, 255));
        ContractUpdate.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        ContractUpdate.setText("Update");
        ContractUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContractUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(ContractUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 620, 110, 30));

        ContractTable.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ranking", "Player_ID", "Player_Name", "Salary", "Points", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ContractTable.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        getContentPane().add(ContractTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 790, 350));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setToolTipText("");
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 36)); // NOI18N
        jPanel2.setName(""); // NOI18N
        jPanel2.setNextFocusableComponent(jPanel2);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 36)); // NOI18N
        jLabel1.setText("Contract Extension Queue");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 70));
        jLabel1.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 850, 100));
        jPanel2.getAccessibleContext().setAccessibleName("Contract Extension");
        jPanel2.getAccessibleContext().setAccessibleParent(jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 0, 153));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 850, 790));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 41, 110, 190));

        jPanel1.setBackground(new java.awt.Color(255, 10, 10));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean isValidPlayer(Connection conn, String playerID, String playerName) {
    PreparedStatement pstmt = null;
    try {
        // Query the table in the database containing player IDs and names
        String sql = "SELECT COUNT(*) FROM ranking WHERE Player_ID = ? AND Player_Name = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(2, playerID);
        pstmt.setString(3, playerName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0; // If count > 0, player exists in the table
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); // Handle database error
    } finally {
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle database error
        }
    }
    return false;
}
     
    private void updateContractsFromRanking() {
    connectDatabase(); // Ensure database connection

    try {
        String updateSql = "INSERT INTO contract (ranking, Player_ID, Player_Name, Salary, Points, Status) " +
                           "SELECT ranking, Player_ID, Player_Name, Salary, Points, 'In Progress' FROM ranking";
        PreparedStatement pstmt = conn.prepareStatement(updateSql);
        pstmt.executeUpdate();
        pstmt.close();
        displayContractTable(); // Refresh the contract table
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to update contracts from Ranking: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateContractStatus(String contractID, String newStatus) {
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Database connection is not available.", "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        String sql = "UPDATE contract SET Status = ? WHERE Player_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setString(2, contractID);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Contract status updated successfully!");
            } else {
                System.out.println("No contract found with the given ID.");
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Failed to update contract status in database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void ContractUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContractUpdateActionPerformed
          updateFirstContract();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Database connection is not available.", "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        String playerID = model.getValueAt(selectedRow, 1).toString();
        String playerName = model.getValueAt(selectedRow, 2).toString();

        updateFirstContract();
    } else {
        JOptionPane.showMessageDialog(this, "No player selected for update.", "Update Error", JOptionPane.WARNING_MESSAGE);
    }

    }//GEN-LAST:event_ContractUpdateActionPerformed

    private void ContractPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContractPDFActionPerformed

        exportToPDF();
    }//GEN-LAST:event_ContractPDFActionPerformed

    private void ContractHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContractHomeActionPerformed
      jMainMenu main = new jMainMenu();
        main.setVisible(true);
        this.dispose();  
    }//GEN-LAST:event_ContractHomeActionPerformed

private void deleteContract(String playerID) {
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Database connection is not available.", "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        String sql = "DELETE FROM contract WHERE Player_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playerID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Contract deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No contract found with the given Player ID.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to delete contract: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void transferDataBetweenTables() {
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Database connection is not available.", "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        conn.setAutoCommit(false); // Start transaction

        String selectQuery = "SELECT ranking, Player_ID, Player_Name, Salary, Points FROM ranking";
        PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
        ResultSet rs = selectStmt.executeQuery();

        String insertQuery = "INSERT INTO contract (ranking, Player_ID, Player_Name, Salary, Points, Status) VALUES (?, ?, ?, ?, ?, 'In Progress')";
        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

        String checkQuery = "SELECT 1 FROM contract WHERE Player_ID = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);

        while (rs.next()) {
            int ranking = rs.getInt("ranking");
            String playerID = rs.getString("Player_ID");
            String playerName = rs.getString("Player_Name");
            int salary = rs.getInt("Salary");
            int points = rs.getInt("Points");

            // Debugging output
            System.out.println("Fetching data: ranking = " + ranking + ", playerID = " + playerID + ", playerName = " + playerName + ", salary = " + salary + ", points = " + points);

            // Check if the Player_ID already exists in the contract table
            checkStmt.setString(1, playerID);
            ResultSet checkRs = checkStmt.executeQuery();
            if (!checkRs.next()) {
                // Debugging output
                System.out.println("Inserting data: ranking = " + ranking + ", playerID = " + playerID);

                insertStmt.setInt(1, ranking);
                insertStmt.setString(2, playerID);
                insertStmt.setString(3, playerName);
                insertStmt.setInt(4, salary);
                insertStmt.setInt(5, points);
                insertStmt.executeUpdate();
            } else {
                System.out.println("Player_ID " + playerID + " already exists in the contract table.");
            }
            checkRs.close();
        }

        rs.close();
        selectStmt.close();
        insertStmt.close();
        checkStmt.close();

        conn.commit(); // Commit transaction
        JOptionPane.showMessageDialog(this, "Data transferred successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
        try {
            if (conn != null && !conn.getAutoCommit()) {
                conn.rollback(); // Rollback transaction on error
            }
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Failed to transfer data between tables: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (conn != null) {
                conn.setAutoCommit(true); // Restore default auto-commit mode
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


private void transferSalaryData() {
    connectDatabase(); // Ensure database connection

    try {

        String updateSql = "UPDATE contract c " +
                       "JOIN ranking sa ON c.Player_ID = sa.Player_ID " +
                       "SET c.Salary = sa.Salary, c.Status = 'In Progress'";
        PreparedStatement pstmt = conn.prepareStatement(updateSql);
        pstmt.executeUpdate();
        pstmt.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to transfer salary data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void transferSalariesFromRanking() {
    connectDatabase(); // Ensure database connection

    try {
        String updateSql = "UPDATE contract c SET c.Salary = (SELECT sa.Salary FROM ranking sa WHERE sa.Player_ID = c.Player_ID)";
        PreparedStatement pstmt = conn.prepareStatement(updateSql);
        pstmt.executeUpdate();
        pstmt.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to update salaries from Ranking: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}



public void synchronizeData() {
    try {
        String fetchQuery = "SELECT ranking, Player_ID, Player_Name FROM ranking";
        PreparedStatement fetchStmt = conn.prepareStatement(fetchQuery);
        ResultSet rs = fetchStmt.executeQuery();

        // Insert fetched data into the contract table
        String insertQuery = "INSERT INTO contract (ranking, Player_ID, Player_Name) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

        // Loop through the result set and insert data into the contract table
        while (rs.next()) {
            // Get data from the result set
            int ranking= rs.getInt("ranking");
            String playerId = rs.getString("Player_ID");
            String playerName = rs.getString("Player_Name");

            // Set parameters for the insert statement
            insertStmt.setInt(1,ranking);
            insertStmt.setString(2, playerId);
            insertStmt.setString(3, playerName);

            // Execute the insert statement
            insertStmt.executeUpdate();
        }

        // Close resources
        rs.close();
        fetchStmt.close();
        insertStmt.close();
    } catch (SQLException ex) {
        // Handle any SQL exceptions
        ex.printStackTrace();
    }
}

private void loadContracts() {
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Database connection is not available.", "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        String sql = "SELECT ranking, Player_ID, Player_Name, Salary, Points, Status FROM contract WHERE Status != 'Updated' ORDER BY ranking";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            int ranking = rs.getInt("ranking");
            String playerID = rs.getString("Player_ID");
            String playerName = rs.getString("Player_Name");
            int salary = rs.getInt("Salary");
            int points = rs.getInt("Points");
            String status = rs.getString("Status");

            Vector<Object> row = new Vector<>();
            row.add(ranking);
            row.add(playerID);
            row.add(playerName);
            row.add(salary);
            row.add(points);
            row.add(status);
            model.addRow(row);
        }

        rs.close();
        pstmt.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Failed to load contracts: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


private void displayContractTable() {
    try {
        String sql = "SELECT ranking, Player_ID, Player_Name, Salary, Points, Status FROM contract"; // Include 'ranking' column
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        int guiRowNumber = 1;

        while (rs.next()) {
            int ranking = rs.getInt("ranking");
            String contractID = rs.getString("Player_ID");
            String contractName = rs.getString("Player_Name");
            int salary = rs.getInt("Salary");
            double points = rs.getDouble("Points");
            String contractStatus = rs.getString("Status");

            model.addRow(new Object[]{guiRowNumber++, ranking, contractID, contractName, salary, points, contractStatus}); // Include 'ranking' in row data
        }

        rs.close();
        pstmt.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Failed to fetch contracts from database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            ContractExtension contractExtension = new ContractExtension();
            contractExtension.setVisible(true);
            contractExtension.loadContracts(); // Load and display contracts
            contractExtension.transferDataBetweenTables(); // Ensure transfer of data
 
        }
    });
}




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ContractHome;
    private javax.swing.JButton ContractPDF;
    private javax.swing.JScrollPane ContractTable;
    private javax.swing.JButton ContractUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
