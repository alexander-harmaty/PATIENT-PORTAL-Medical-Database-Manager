/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaskh
 */
public class EcdelController implements Initializable {
    private String patientID = App.currentUser.getUserID();

     @FXML
    private TextField textField_fname;

    @FXML
    void handleButton_delete() {
         getDelQuery();
         JOptionPane.showMessageDialog(null,"Contact Deleted"); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    private void getDelQuery() {
        try {
            Connection con = DatabaseConnection.connectDB();
            Statement delquery = con.createStatement();
        
                String fname = textField_fname.getText();
            
            String query = "DELETE FROM EC WHERE ECfirst = '" +fname+ "'AND PatientID = " + patientID;
            
            System.out.println(query);            
            delquery.executeQuery(query);            
            JOptionPane.showMessageDialog(null,"Saved");    
            
        }catch (Exception e) {}
    
    } 
    
}
