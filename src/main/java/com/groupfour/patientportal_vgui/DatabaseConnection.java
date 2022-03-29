/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Angie
 */
public class DatabaseConnection {
    public static Connection connectDB() {
        String user = "pportal";
        String pwd = "admin";
        try{
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //cant be cipher
             Connection con = DriverManager.getConnection("jdbc:sqlserver://24.189.211.114:1433;"
                        + "databaseName=PatientPortal;encrypt=true;trustServerCertificate=true;", user, pwd);
             //JOptionPane.showMessageDialog(null, "Connection established successfully");
             return con;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
    }
    
    //code for table view
    
    
     
}
