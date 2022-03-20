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
    
    String patientID;
    
    @FXML
    private void handleViewPatientSearchButton()
    {
//        try
//        {
//            patientID = textFieldPatientID.getText();
//            if (patientID.length() > 5)
//            {
//                //throw custom exception and break
//            }
//            
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //cant be cipher
//            Connection con = DriverManager.getConnection("jdbc:sqlserver://24.189.211.114:1433;"
//                    + "databaseName=PatientPortal;encrypt=true;trustServerCertificate=true;", user, pwd);
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM PATIENT WHERE PatientID="+patientID+";");
//        }
//        catch (NumberFormatException e)
//        {
//            labelErrorText.setText("ONLY use numbers! Please try again.");
//        }
//        catch (Exception e)
//        {
//            labelErrorText.setText("UNKNOWN ERROR! Please try again.");
//        }
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
        patientID = "";
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
