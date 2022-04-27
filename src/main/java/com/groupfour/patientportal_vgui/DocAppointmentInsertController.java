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
    private TextField textField_date, textField_doctorID, textField_officeID, 
            textField_patientID, textField_time;
    
    @FXML
    void handleButton_clear()
    {
        this.textField_date.clear();
        this.textField_doctorID.clear();
        this.textField_officeID.clear();
        this.textField_patientID.clear();
        this.textField_time.clear();
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
        
                String patientID = textField_patientID.getText();
                String date = textField_date.getText();
                String time = textField_time.getText();
                String officeID = textField_officeID.getText();
                String doctorID = textField_doctorID.getText();
                
            String addRecord = "INSERT INTO APPOINTMENTS (Date, Time, PatientID, OfficeID, LabID, DoctorID)"
                    + " VALUES (" + patientID + ",'" +date+ "','" +time+ "','" +officeID+ "','" +doctorID+ "')";
            
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
