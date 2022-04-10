/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Brian
 */
public class PrintMedicalInfo {
    public static void printData() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        
     
     try{
         
         FileWriter fout = new FileWriter("test.txt", false);
         System.out.println("This was a success");
         Connection con = DatabaseConnection.connectDB();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery("SELECT * FROM MEDICALRECORDS WHERE PatientID="+10002+";");
         
         
         while (rs.next()) {
             fout.write(rs.getString(1) + ", ");
             fout.write(rs.getString(2) + ", ");
             fout.write(rs.getString(3) + ", ");
             fout.write(rs.getString(4) + ", ");
             fout.write(rs.getString(5) + ", ");
             fout.write(rs.getString(6) + ", ");
             fout.write(rs.getString(7) + ", ");
             fout.write(rs.getString(8) + "\n");
             
            }// End of while statement
         fout.close();
        } // End of try statement
     catch (Exception e) {
        System.out.println("An error occurred");
         //if (rs != null)
           //  rs.close();
         //ps.close();
         //con.close();
        } // End of Catch statement
} // End of private static void #2

    private static void fileOut() throws FileNotFoundException{
        
        
        
        
        
    }
}
