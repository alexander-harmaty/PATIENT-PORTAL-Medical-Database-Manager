/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alexander
 */

public class ViewPatientInfoController implements Initializable 
{
    //ALEX: there should be a better way to pass in server user & pass
    String user = "pportal";
    String pwd = "admin";
    
    int patientID;
    
    @FXML
    private Button buttonViewPatientSearch;
    @FXML
    private Button buttonMainMenu;
    @FXML
    private Button buttonClear;
    @FXML
    private Label labelErrorText;
    @FXML
    private TextField textFieldPatientID;
    @FXML
    private TextField textFieldPatientFName;
    @FXML
    private TextField textFieldPatientLName;
    @FXML
    private TextField textFieldPatientPhone;
    @FXML
    private TextField textFieldPatientEmail;
    @FXML
    private TextField textFieldPatientInsuranceID;
    @FXML
    private TextField textFieldPatientInsuranceCo;
    
    @FXML
    private void handleViewPatientSearchButton()
    {
        try
        {
            patientID = Integer.parseInt(textFieldPatientID.getText());
            
            if (String.valueOf(textFieldPatientID.getText()).length() == 5)
            {
                labelErrorText.setText("");

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //cant be cipher
                Connection con = DriverManager.getConnection("jdbc:sqlserver://24.189.211.114:1433;"
                        + "databaseName=PatientPortal;encrypt=true;trustServerCertificate=true;", user, pwd);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PATIENT WHERE PatientID="+patientID+";");

                while (rs.next()) 
                {   //displays data. there must be a simpler way to implement

                    textFieldPatientID.setText(String.valueOf(rs.getInt("PatientID")));
                    //System.out.println("PATIENT ID: " + rs.getInt("PatientID"));

                    textFieldPatientFName.setText(rs.getString("PFirstName"));
                    //System.out.println("FIRST NAME: " + rs.getString("PFirstName"));

                    textFieldPatientLName.setText(rs.getString("PLastName"));
                    //System.out.println("LAST NAME: " + rs.getString("PLastName"));

                    textFieldPatientPhone.setText(rs.getString("PPhone"));
                    //System.out.println("PHONE NUMBER: " + rs.getString("PPhone"));

                    textFieldPatientEmail.setText(rs.getString("PEmail"));
                    //System.out.println("EMAIL: " + rs.getString("PEmail"));

                    textFieldPatientInsuranceID.setText(String.valueOf(rs.getInt("InsuranceID")));
                    //System.out.println("INSURANCE ID: " + rs.getInt("InsuranceID"));

                    textFieldPatientInsuranceCo.setText(rs.getString("Insurance"));
                    //System.out.println("INSURANCE COMPANY: " + rs.getString("Insurance"));
                }
            }
            else
            {
                //ALEX: Maybe throw a custom exception for this?
                labelErrorText.setText("ONLY use 5 numbers! Please try again."); 
            }
        }
        catch (NumberFormatException e)
        {
            labelErrorText.setText("ONLY use numbers! Please try again.");
        }
        catch (Exception e)
        {
            labelErrorText.setText("UNKNOWN ERROR! Please try again.");
        }
    }
    @FXML
    private void handleButtonClear()
    {
        this.textFieldPatientID.clear();
        this.textFieldPatientFName.clear();
        this.textFieldPatientLName.clear();
        this.textFieldPatientPhone.clear();
        this.textFieldPatientEmail.clear();
        this.textFieldPatientInsuranceID.clear();
        this.textFieldPatientInsuranceCo.clear();
        this.labelErrorText.setText("");
        patientID = 0;
    }
    @FXML
    private void handleMainMenuButton() throws IOException
    {
        switchToDevMenu();
    }
    
    @FXML
    private void switchToDevMenu() throws IOException
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
