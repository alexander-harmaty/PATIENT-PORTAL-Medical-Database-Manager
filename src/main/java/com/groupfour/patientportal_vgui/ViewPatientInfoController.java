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
    private Button button_search;
    @FXML
    private Button button_devMenu;
    @FXML
    private Button button_clear;
    @FXML
    private Label label_errorText;
    @FXML
    private TextField textField_patientID;
    @FXML
    private TextField textField_firstName;
    @FXML
    private TextField textField_lastName;
    @FXML
    private TextField textField_phone;
    @FXML
    private TextField textField_email;
    @FXML
    private TextField textField_insuranceID;
    @FXML
    private TextField textField_insuranceCo;
    
    @FXML
    private void handleButton_search()
    {
        try
        {
            patientID = Integer.parseInt(textField_patientID.getText());
            
            if (String.valueOf(textField_patientID.getText()).length() == 5)
            {
                label_errorText.setText("");

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //cant be cipher
                Connection con = DriverManager.getConnection("jdbc:sqlserver://24.189.211.114:1433;"
                        + "databaseName=PatientPortal;encrypt=true;trustServerCertificate=true;", user, pwd);
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
