/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alex
 */

public class PatientDashboardController implements Initializable 
{
    //String user = "pportal";
    //String pwd = "admin";
    
    int patientID;
    
    @FXML
    private Button button_accountInfo;

    @FXML
    private Button button_appointments;

    @FXML
    private Button button_devMenu;

    @FXML
    private Button button_medicalRecords;

    @FXML
    private Button button_prescriptions;

    @FXML
    private Button button_search;

    @FXML
    private Button button_testResults;
    
    @FXML
    private AnchorPane panel_accountInfo;

    @FXML
    private AnchorPane panel_appointments;

    @FXML
    private AnchorPane panel_dashboard;

    @FXML
    private AnchorPane panel_medicalRecords;

    @FXML
    private AnchorPane panel_prescriptions;

    @FXML
    private AnchorPane panel_search;

    @FXML
    private AnchorPane panel_testResults;
    
    @FXML
    private TextField textField_email;

    @FXML
    private TextField textField_firstName;

    @FXML
    private TextField textField_insuranceCo;

    @FXML
    private TextField textField_insuranceID;

    @FXML
    private TextField textField_lastName;

    @FXML
    private TextField textField_patientID;

    @FXML
    private TextField textField_phone;
    
    @FXML
    private Label label_errorText;
    
    public void show_panelDashboard() 
    {
        panel_dashboard.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_accountInfo() 
    {
        panel_accountInfo.setVisible(true);
        
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_appointments() 
    {
        panel_appointments.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_medicalRecords() 
    {
        panel_medicalRecords.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_prescriptions() 
    {
        panel_prescriptions.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_search() 
    {
        panel_search.setVisible(true);
        
        panel_dashboard.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_testResults() 
    {
        panel_testResults.setVisible(true);
        
        panel_dashboard.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
    }
    
    @FXML
    private void handleButton_go()
    {
        try
        {
            patientID = Integer.parseInt(textField_patientID.getText());
            
            if (String.valueOf(textField_patientID.getText()).length() == 5)
            {
                label_errorText.setText("");

                //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //cant be cipher
                //Connection con = DriverManager.getConnection("jdbc:sqlserver://24.189.211.114:1433;"
                        //+ "databaseName=PatientPortal;encrypt=true;trustServerCertificate=true;", user, pwd);
                Connection con = DatabaseConnection.connectDB();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PATIENT WHERE PatientID="+patientID+";");

                while (rs.next()) 
                {   //displays data. there must be a simpler way to implement

                    textField_patientID.setText(String.valueOf(rs.getInt("PatientID")));
                    //System.out.println("PATIENT ID: " + rs.getInt("PatientID"));

                    textField_firstName.setText(rs.getString("PFirstName"));
                    //System.out.println("FIRST NAME: " + rs.getString("PFirstName"));

                    textField_lastName.setText(rs.getString("PLastName"));
                    //System.out.println("LAST NAME: " + rs.getString("PLastName"));

                    textField_phone.setText(rs.getString("PPhone"));
                    //System.out.println("PHONE NUMBER: " + rs.getString("PPhone"));

                    textField_email.setText(rs.getString("PEmail"));
                    //System.out.println("EMAIL: " + rs.getString("PEmail"));

                    textField_insuranceID.setText(String.valueOf(rs.getInt("InsuranceID")));
                    //System.out.println("INSURANCE ID: " + rs.getInt("InsuranceID"));

                    textField_insuranceCo.setText(rs.getString("Insurance"));
                    //System.out.println("INSURANCE COMPANY: " + rs.getString("Insurance"));
                }
            }
            else
            {
                //ALEX: Maybe throw a custom exception for this?
                label_errorText.setText("ONLY use 5 numbers! Please try again."); 
            }
        }
        catch (NumberFormatException e)
        {
            label_errorText.setText("ONLY use numbers! Please try again.");
        }
        catch (Exception e)
        {
            label_errorText.setText("UNKNOWN ERROR! Please try again.");
        }
    }
    
    @FXML
    private void handleButton_clear()
    {
        this.textField_patientID.clear();
        this.textField_firstName.clear();
        this.textField_lastName.clear();
        this.textField_phone.clear();
        this.textField_email.clear();
        this.textField_insuranceID.clear();
        this.textField_insuranceCo.clear();
        this.label_errorText.setText("");
        patientID = 0;
    }

    @FXML
    void switchToDevMenu() throws IOException 
    {
        App.setRoot("devMenu");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
