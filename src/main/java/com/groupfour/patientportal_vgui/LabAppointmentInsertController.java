/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AlexH
 */
public class LabAppointmentInsertController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MFXButton button_clear, button_createApp;
    
    @FXML
    private TextArea textArea_appReason;

    @FXML
    private TextField textField_date, textField_time, textField_labID, textField_patientID;;
    
    @FXML
    void handleButton_clear()
    {
        this.textArea_appReason.clear();
        this.textField_date.clear();
        this.textField_time.clear();
        this.textField_labID.clear();
    }
    
    Connection con = null;
    Statement stmt = null;
    
    @FXML
    void handleButton_createApp()
    {
        try {
            System.out.println("Insert Proccess has Started!");
            con = DatabaseConnection.connectDB();
            stmt = con.createStatement();
                
            String reason = textArea_appReason.getText();
            String date = textField_date.getText();
            String time = textField_time.getText();
            String labID = textField_labID.getText();
            String patientID = textField_patientID.getText();
                
            String addRecord = "INSERT INTO APPOINTMENT (Reason, Date, Time, PatientID, LabID)"
                    + " VALUES ('" + reason + "','" +date+ "','" +time+ "'," +patientID+ "," +labID+ ")";
            
        System.out.println(addRecord);            
        stmt.execute(addRecord); 
        System.out.println("Insert Proccess has finished!");
        JOptionPane.showMessageDialog(null,"New Record Added!");
        
        } catch (Exception e) {JOptionPane.showMessageDialog(null,"Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);}
    }
    
    
    
    @FXML
    void handleButton_getAppData() {
        
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        switch (currentUserType) {
            case "Patient":
                textField_patientID.setText(currentUserID);
                break;
            case "Doctor":
                JOptionPane.showMessageDialog(null,"Current User data is unnecessary.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Service":
                textField_labID.setText(currentUserID);
                break;
            default:
                JOptionPane.showMessageDialog(null,"Unable to get current user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
