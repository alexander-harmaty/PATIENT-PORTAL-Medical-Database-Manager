/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.sql.Connection;
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
public class DocAppointmentInsertController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MFXButton button_clear, button_createApp;
    
    @FXML
    private TextArea textArea_appReason;

    @FXML
    private TextField textField_date, textField_time, textField_doctorID, textField_officeID;
    
    @FXML
    void handleButton_clear()
    {
        this.textArea_appReason.clear();
        this.textField_date.clear();
        this.textField_time.clear();
        this.textField_doctorID.clear();
        this.textField_officeID.clear();
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
            String doctorID = textField_doctorID.getText();
            String patientID = App.currentUser.getUserID();
            String officeID = textField_officeID.getText();
            String labID = "";
                
            String addRecord = "INSERT INTO APPOINTMENT (Reason, Date, Time, DoctorID, PatientID, OfficeID, LabID)"
                    + " VALUES ('" + reason + "','" +date+ "','" +time+ "'," +doctorID+ "," +patientID+ "," +officeID+ "," +labID+")";
            
        System.out.println(addRecord);            
        stmt.execute(addRecord); 
        System.out.println("Insert Proccess has finished!");
        JOptionPane.showMessageDialog(null,"New Record Added!");
        
        } catch (Exception e) {System.out.println(e);}
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}
