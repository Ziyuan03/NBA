/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samyee
 */
public class CreateRankingTable 
{
    Connection con;    
    
    //Testing
    public void createConnection() {
        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "root123NBA.");
            
            System.out.println("Connected");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NBA_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void createTable() {
        createConnection();
        
       try {
    Statement stmt = con.createStatement();
    
    // Step 1: Create the POINTS table with composite_score
    stmt.execute("CREATE TABLE POINTS_TEMP AS "
            + "SELECT SAN_ANTONIO.Player_ID, SAN_ANTONIO.Player_Name, SAN_ANTONIO.Age, SAN_ANTONIO.Height, SAN_ANTONIO.Weight, SAN_ANTONIO.Position, SAN_ANTONIO.Salary, "
            + "AVERAGESTATS.PTS AS Points, AVERAGESTATS.REB AS Rebounds, AVERAGESTATS.AST AS Assists, AVERAGESTATS.STL AS Steals, AVERAGESTATS.BLK AS Blocks, "
            + "CASE "
            + "WHEN SAN_ANTONIO.Position = 'F' THEN (AVERAGESTATS.PTS * 0.18 + AVERAGESTATS.REB * 0.3 + AVERAGESTATS.AST * 0.3 + AVERAGESTATS.BLK * 0.1 + AVERAGESTATS.STL * 0.1) * 10 "
            + "WHEN SAN_ANTONIO.Position = 'C' THEN (AVERAGESTATS.PTS * 0.18 + AVERAGESTATS.REB * 0.3 + AVERAGESTATS.AST * 0.1 + AVERAGESTATS.BLK * 0.3 + AVERAGESTATS.STL * 0.1) * 10 "
            + "WHEN SAN_ANTONIO.Position = 'G' THEN (AVERAGESTATS.PTS * 0.18 + AVERAGESTATS.REB * 0.1 + AVERAGESTATS.AST * 0.3 + AVERAGESTATS.BLK * 0.1 + AVERAGESTATS.STL * 0.3) * 10 "
            + "ELSE 0 END AS composite_score "
            + "FROM SAN_ANTONIO "
            + "INNER JOIN AVERAGESTATS ON SAN_ANTONIO.Player_ID = AVERAGESTATS.Player_ID;");
    
    // Step 2: Add the ranking column
    stmt.execute("ALTER TABLE POINTS_TEMP ADD COLUMN ranking INT;");
    
    // Step 3: Update the ranking column based on the composite_score
    stmt.execute("SET @rank = 0;");
    stmt.execute("UPDATE POINTS_TEMP SET ranking = (@rank := @rank + 1) ORDER BY composite_score DESC;");
    
    // Step 4: Create the final POINTS table with ranking as the primary key
    stmt.execute("CREATE TABLE RANKING ("
            + "ranking INT PRIMARY KEY, "
            + "composite_score DOUBLE, "
            + "Player_ID INT, "
            + "Player_Name VARCHAR(255), "
            + "Age INT, "
            + "Height VARCHAR(255), "
            + "Weight DOUBLE, "
            + "Position VARCHAR(10), "
            + "Salary DOUBLE, "
            + "Points DOUBLE, "
            + "Rebounds DOUBLE, "
            + "Assists DOUBLE, "
            + "Steals DOUBLE, "
            + "Blocks DOUBLE);");
    
    // Step 5: Insert data from POINTS_TEMP into POINTS
    stmt.execute("INSERT INTO RANKING (ranking, composite_score, Player_ID, Player_Name, Age, Height, Weight, Position, Salary, Points, Rebounds, Assists, Steals, Blocks) "
            + "SELECT ranking, composite_score, Player_ID, Player_Name, Age, Height, Weight, Position, Salary, Points, Rebounds, Assists, Steals, Blocks FROM POINTS_TEMP;");
    
    // Step 6: Drop the temporary table
    stmt.execute("DROP TABLE POINTS_TEMP;");
    
    stmt.close();   
    System.out.println("POINTS Table Created with Composite Score and Ranking as Primary Key");
    
    } catch (SQLException ex) {
    Logger.getLogger(NBA_SQL.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public static void main(String[]ags)
    {
        CreateRankingTable crt = new CreateRankingTable();
        crt.createTable();
    }
}
