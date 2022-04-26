/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class DocPrintRecordController implements Initializable {

    
    @FXML
    private TextField textField_recordprintID;
    
    
    @FXML
    void handleButton_recordclear() {
        this.textField_recordprintID.clear();
    }
    
    @FXML
    void handleButton_recordprint() {
        Connection con = DatabaseConnection.connectDB();
        getPrintQuery();
        // JOptionPane.showMessageDialog(null,"Patient Removed"); 
    }
    
    /**
     * Initializes the controller class.
     */
    
    public void getPrintQuery() 
    {
        try
        {
            //FileWriter fout = new FileWriter("test.txt", false);
            System.out.println("This was a success");
            Connection con = DatabaseConnection.connectDB();
            Statement st = con.createStatement();
            
            // File file = new File("test.txt");
            //FileWriter fw = new FileWriter("test.txt");
            //BufferedWriter bw = new BufferedWriter(fw);
            
            //SQL
            int ID = Integer.parseInt(textField_recordprintID.getText());
            String sql = "SELECT * FROM MEDICALRECORDS WHERE PatientID = " + ID;
            ResultSet rs = st.executeQuery(sql);
            
            //FileChooser
            FileChooser fc;
            fc = new FileChooser();
            File selectedFile;
            selectedFile = fc.showSaveDialog(null);
            
            if(selectedFile != null)
            {
                System.out.println("\nName & Path Selected!");
                System.out.println(selectedFile);

                PrintStream ps = null;

                try
                {
                    ps = new PrintStream(selectedFile);
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("File Not Found!");
                    //break;
                }

                //cg.report(ps);
                while (rs.next()) 
                {
                    System.out.println("Got here");
                    int ID1 = rs.getInt("RecordID");
                    int ID2 = rs.getInt("PatientID");
                    String dob = rs.getString("DOB");
                    String recorddate = rs.getString("RecordDate");
                    String height = rs.getString("Height");
                    String weight = rs.getString("Weight");
                    String bloodtype = rs.getString("BloodType");
                    String diagnosis = rs.getString("Diagnosis");

                    ps.append("OFFICIAL MEDICAL RECORD" + "\n----------------------" + "\nRecordID: "+ID1+
                    "\n\nPatientID: "+ID2+"\n\nDate of Birth: "+dob+"\n\nRecord Date: "+ recorddate +
                    "\n\nHeight: " + height + "\n\nWeight: " + weight + "\n\nBlood Type: " + bloodtype +
                    "\n\nDiagnosis: " + diagnosis +"\n\n");
                }
                System.out.println("\nReport Written!\n");
            }
            else
            {
                System.out.println("File Chooser Closed!");
            }
            
            rs.close();
            //bw.close();
            // fout.close();
        } // End of try statement
        catch (Exception e) 
        {
            System.out.print(e);
        } // End of Catch statement 
    } // End of private static void #2
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
