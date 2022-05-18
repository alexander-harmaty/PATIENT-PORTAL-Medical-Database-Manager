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
 * FXML Controller class to create a doctors appointment
 *
 * @author Alexander Harmaty
 */
public class DocAppointmentInsertController implements Initializable 
{
    @FXML
    private MFXButton button_clear, button_createApp;
    
    @FXML
    private TextArea textArea_appReason;

    @FXML
    private TextField textField_date, textField_time, textField_doctorID, textField_officeID, textField_patientID;
    
    @FXML
    void handleButton_clear()
    {
        this.textArea_appReason.clear();
        this.textField_date.clear();
        this.textField_time.clear();
        this.textField_doctorID.clear();
        this.textField_officeID.clear();
    }
    
    @FXML
    void handleButton_createApp()
    {
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        String reason = textArea_appReason.getText();
        String date = textField_date.getText();
        String time = textField_time.getText();
        String doctorID = textField_doctorID.getText();
        String patientID = textField_patientID.getText();
        String officeID = textField_officeID.getText();
        
        if ("Patient".equals(currentUserType) && !currentUserID.equals(patientID))
        {
            JOptionPane.showMessageDialog(null,"Patient ID does not match the current user!"
                    + "\nPlease enter the correct ID, and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if ("Doctor".equals(currentUserType) && !currentUserID.equals(doctorID))
        {
            JOptionPane.showMessageDialog(null,"Doctor ID does not match the current user!"
                    + "\nPlease enter the correct ID, and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (("Service".equals(currentUserType) || "Office".equals(currentUserType)) && (!currentUserID.equals(officeID))) 
        {
            JOptionPane.showMessageDialog(null,"Office ID does not match the current user!"
                    + "\nPlease enter the correct ID, and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try 
            {
                System.out.println("Insert Proccess has Started!");
                Connection con = DatabaseConnection.connectDB();
                Statement stmt = con.createStatement();
                
                String addRecord = "INSERT INTO APPOINTMENT (Reason, Date, Time, DoctorID, PatientID, OfficeID)"
                        + " VALUES ('" + reason + "','" +date+ "','" +time+ "'," +doctorID+ "," +patientID+ "," +officeID+ ")";

                System.out.println(addRecord);            
                stmt.execute(addRecord); 
                System.out.println("Insert Proccess has finished!");
                JOptionPane.showMessageDialog(null,"New Record Added!");
            } 
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null,"Please fill out all fields\n"
                        + "and assure all IDs are correct.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    @FXML
    void handleButton_getAppData() 
    {
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        switch (currentUserType) 
        {
            case "Patient":
                textField_patientID.setText(currentUserID);
                break;
            case "Doctor":
                textField_doctorID.setText(currentUserID);
                break;
            case "Service":
                textField_officeID.setText(currentUserID);
                break;
            case "Office":
                textField_officeID.setText(currentUserID);
                break;
            case "Lab":
                JOptionPane.showMessageDialog(null,"Current User data is unnecessary.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Pharmacy":
                JOptionPane.showMessageDialog(null,"Current User data is unnecessary.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null,"Unable to get current user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}
