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
 * @author Angie
 */
public class DocDeleteRecordController implements Initializable {

    /**
     * Initializes the controller class.
     */
  @FXML
    private TextField textField_recorddeleteID;

    @FXML
    void handleButton_recordclear() {
        this.textField_recorddeleteID.clear();

    }

    @FXML
    void handleButton_recorddelete() {
        Connection con = DatabaseConnection.connectDB();
         getDelQuery();
          JOptionPane.showMessageDialog(null,"Medical record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);    
         //JOptionPane.showMessageDialog(null,"Medical Record Deleted"); 
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
            //System.out.println("Connection Success!");
            Connection con = DatabaseConnection.connectDB();
            Statement delquery = con.createStatement();
        
                int ID = Integer.parseInt(textField_recorddeleteID.getText());
            
            String query = "DELETE FROM MEDICALRECORDS WHERE RecordID = " + ID;
            
            System.out.println(query);            
            delquery.executeQuery(query);            
            JOptionPane.showMessageDialog(null,"Medical record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);    
            
        }catch (Exception e) {}
    
    } 
    
}
