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
 * FXML Controller class to reschedule or cancel any appointment
 *
 * @author Alexander Harmaty
 */
public class AppointmentRescheduleOrCancelController implements Initializable {

    @FXML
    private MFXButton button_clear, button_createApp;
    
    @FXML
    private TextArea textArea_appReason;

    @FXML
    private TextField textField_date, textField_time, textField_doctorID, 
            textField_officeID, textField_labID, textField_appID, textField_patientID;
    
    @FXML
    void handleButton_clear()
    {
        this.textArea_appReason.clear();
        this.textField_date.clear();
        this.textField_time.clear();
        this.textField_doctorID.clear();
        this.textField_officeID.clear();
        this.textField_labID.clear();
    }
    
    @FXML
    void handleButton_getAppData() {
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        String appID = textField_appID.getText();
        
        switch (currentUserType) 
        {
            case "Patient":
                
                try
                {
                    Connection con = DatabaseConnection.connectDB();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM APPOINTMENT WHERE Appid="+appID+" AND PatientID="+currentUserID+";");

                    while (rs.next()) 
                    {   
                        textField_appID.setText(rs.getString("Appid")); 
                        textArea_appReason.setText(rs.getString("Reason")); 
                        textField_date.setText(rs.getString("Date")); 
                        textField_time.setText(rs.getString("Time")); 
                        textField_doctorID.setText(rs.getString("DoctorID")); 
                        textField_officeID.setText(rs.getString("OfficeID")); 
                        textField_labID.setText(rs.getString("LabID")); 
                        textField_patientID.setText(rs.getString("PatientID"));
                    }
                } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Appointment not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
            case "Doctor":
                
                try
                {
                    Connection con = DatabaseConnection.connectDB();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM APPOINTMENT WHERE Appid="+appID+" AND DoctorID="+currentUserID+";");

                    while (rs.next()) 
                    {   
                        textField_appID.setText(rs.getString("Appid")); 
                        textArea_appReason.setText(rs.getString("Reason")); 
                        textField_date.setText(rs.getString("Date")); 
                        textField_time.setText(rs.getString("Time")); 
                        textField_doctorID.setText(rs.getString("DoctorID")); 
                        textField_officeID.setText(rs.getString("OfficeID")); 
                        textField_labID.setText(rs.getString("LabID")); 
                        textField_patientID.setText(rs.getString("PatientID"));
                    }
                } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Appointment not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
            case "Office":
                
                try
                {
                    Connection con = DatabaseConnection.connectDB();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM APPOINTMENT WHERE Appid="+appID+" AND OfficeID="+currentUserID+";");

                    while (rs.next()) 
                    {   
                        textField_appID.setText(rs.getString("Appid")); 
                        textArea_appReason.setText(rs.getString("Reason")); 
                        textField_date.setText(rs.getString("Date")); 
                        textField_time.setText(rs.getString("Time")); 
                        textField_doctorID.setText(rs.getString("DoctorID")); 
                        textField_officeID.setText(rs.getString("OfficeID")); 
                        textField_labID.setText(rs.getString("LabID")); 
                        textField_patientID.setText(rs.getString("PatientID"));
                    }
                } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Appointment not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
            case "Lab":
                
                try
                {
                    Connection con = DatabaseConnection.connectDB();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM APPOINTMENT WHERE Appid="+appID+" AND LabID="+currentUserID+";");

                    while (rs.next()) 
                    {   
                        textField_appID.setText(rs.getString("Appid")); 
                        textArea_appReason.setText(rs.getString("Reason")); 
                        textField_date.setText(rs.getString("Date")); 
                        textField_time.setText(rs.getString("Time")); 
                        textField_doctorID.setText(rs.getString("DoctorID")); 
                        textField_officeID.setText(rs.getString("OfficeID")); 
                        textField_labID.setText(rs.getString("LabID")); 
                        textField_patientID.setText(rs.getString("PatientID"));
                    }
                } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Appointment not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
            case "Pharmacy":
                
                try
                {
                    Connection con = DatabaseConnection.connectDB();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM APPOINTMENT WHERE Appid="+appID+" AND OfficeID="+currentUserID+";");

                    while (rs.next()) 
                    {   
                        textField_appID.setText(rs.getString("Appid")); 
                        textArea_appReason.setText(rs.getString("Reason")); 
                        textField_date.setText(rs.getString("Date")); 
                        textField_time.setText(rs.getString("Time")); 
                        textField_doctorID.setText(rs.getString("DoctorID")); 
                        textField_officeID.setText(rs.getString("OfficeID")); 
                        textField_labID.setText(rs.getString("LabID")); 
                        textField_patientID.setText(rs.getString("PatientID"));
                    }
                } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Appointment not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
            default:
                
                JOptionPane.showMessageDialog(null,"Unable to get current user data.", "Error", JOptionPane.ERROR_MESSAGE);
                
                break;
                
        }
    }
    
    @FXML
    public void reschedule() 
    {
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        String appID = textField_appID.getText();
        String reason = textArea_appReason.getText();
        String date = textField_date.getText();
        String time = textField_time.getText();
        String doctorID = textField_doctorID.getText();
        String patientID = textField_patientID.getText();
        String officeID = textField_officeID.getText();
        String labID = textField_labID.getText();
        
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
        else if (("Pharmacy".equals(currentUserType) || "Office".equals(currentUserType)) && (!currentUserID.equals(officeID))) 
        {
            JOptionPane.showMessageDialog(null,"Office ID does not match the current user!"
                    + "\nPlease enter the correct ID, and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if ("Lab".equals(currentUserType) && !currentUserID.equals(labID))
        {
            JOptionPane.showMessageDialog(null,"Lab ID does not match the current user!"
                    + "\nPlease enter the correct ID, and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else 
        {
            try 
            {
                Connection con = DatabaseConnection.connectDB();
                Statement stmt = con.createStatement();

                String query = "UPDATE APPOINTMENT " + 
                  "SET  Reason ='" + reason + "', Date = '" + date + 
                        "', Time = '" + time + "', DoctorID = " + doctorID + 
                        ", PatientID = " + patientID +", OfficeID = "+ officeID +
                        ", LabID = " + labID + " WHERE AppID = " +appID+ ";";

                System.out.println(query);            
                stmt.executeQuery(query);    
            } 
            catch (Exception e) {}
            JOptionPane.showMessageDialog(null,"New Record Updated!");
        }
    }
    
    @FXML
    public void cancel() 
    {
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        String appID = textField_appID.getText();
        String reason = textArea_appReason.getText();
        String date = textField_date.getText();
        String time = textField_time.getText();
        String doctorID = textField_doctorID.getText();
        String patientID = textField_patientID.getText();
        String officeID = textField_officeID.getText();
        String labID = textField_labID.getText();
        
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
        else if (("Pharmacy".equals(currentUserType) || "Office".equals(currentUserType)) && (!currentUserID.equals(officeID))) 
        {
            JOptionPane.showMessageDialog(null,"Office ID does not match the current user!"
                    + "\nPlease enter the correct ID, and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if ("Lab".equals(currentUserType) && !currentUserID.equals(labID))
        {
            JOptionPane.showMessageDialog(null,"Lab ID does not match the current user!"
                    + "\nPlease enter the correct ID, and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try 
            {
                System.out.println("Connection Success!");
                Connection con = DatabaseConnection.connectDB();
                Statement delquery = con.createStatement();

                int ID = Integer.parseInt(textField_appID.getText());

                String query = "DELETE FROM APPOINTMENT WHERE Appid = " + ID;

                System.out.println(query);            
                delquery.executeQuery(query);            
                JOptionPane.showMessageDialog(null,"Saved");    

            }
            catch (Exception e) {}
            JOptionPane.showMessageDialog(null,"Appointment Removed"); 
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
