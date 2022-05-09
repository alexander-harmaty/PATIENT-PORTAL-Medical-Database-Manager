/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yasin
 */
public class DocDeletePatientController implements Initializable {
    
    @FXML
    private TextField textField_patientdeleteID;

    @FXML
    void handleButton_partientclear() {
        this.textField_patientdeleteID.clear();

    }

    @FXML
    void handleButton_patientdelete() {
        Connection con = DatabaseConnection.connectDB();
         getDelQuery();
         JOptionPane.showMessageDialog(null,"Patient Removed"); 
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void getDelQuery() {
        try {
            System.out.println("Connection Success!");
            Connection con = DatabaseConnection.connectDB();
            Statement delquery = con.createStatement();
        
                int ID = Integer.parseInt(textField_patientdeleteID.getText());
            
            String query = "DELETE FROM PATIENT WHERE PatientID = " + ID;
            
            System.out.println(query);            
            delquery.executeQuery(query);            
               JOptionPane.showMessageDialog(null,"Patient removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);    
            
        }catch (Exception e) {}
    
    }
}
