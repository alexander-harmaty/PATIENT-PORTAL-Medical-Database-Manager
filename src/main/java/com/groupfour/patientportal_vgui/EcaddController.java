/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.net.URL;
import java.sql.*;
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
public class EcaddController implements Initializable {
    //Capture userID
    private String patientID = App.currentUser.getUserID();
    
    @FXML
    private TextField textField_firstName, textField_lastName,textField_phone, textField_relation;

    @FXML
    void handleButton_save() {
         getQuery();
         JOptionPane.showMessageDialog(null,"Contact saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);;
    }
    
    @FXML
    void handleButton_clear() {
        this.textField_firstName.clear();
        this.textField_lastName.clear();
        this.textField_phone.clear();
        this.textField_relation.clear();
    }
    
    private void getQuery() {
        
        try {
        System.out.println("Connection Success!");
        Connection con = DatabaseConnection.connectDB();
        Statement stmt = con.createStatement();
        
            String ID = patientID;
            String fname = textField_firstName.getText();
            String lname = textField_lastName.getText();
            String num =  textField_phone.getText();
            String rel = textField_relation.getText();
            
            String ECquery = "INSERT INTO EC (ECfirst, EClast, ECphone, Relation, PatientID)" 
                    + " VALUES  ('" + fname + "','" +lname+
                    "','" +num+ "','" +rel+ "'," +ID+")";
        
        System.out.println(ECquery);            
        stmt.executeQuery(ECquery);            
           // JOptionPane.showMessageDialog(null,"Contact saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {}
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
