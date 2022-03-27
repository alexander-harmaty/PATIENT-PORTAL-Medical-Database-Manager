/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.collections.ObservableList;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.stage.FileChooser;

public class DevMenuController implements Initializable 
{
    //Initialize Buttons
    //Sprint1
    @FXML
    private Button button_quitApp;

    @FXML
    private Button button_viewPatient;
    
    //Sprint2
    @FXML
    private Button button_login;
    
    @FXML
    private Button button_patientView;
    
    @FXML
    private Button button_doctorView;

    @FXML
    private Button button_labView;

    @FXML
    private Button button_pharmaView;
    
    
    
    //Button Handler Methods
    @FXML
    private void switchToViewPatient() throws IOException 
    {
        App.setRoot("viewPatientInfo");
    }
    @FXML
    private void handlebuttonQuitApp()
    {
        System.exit(0);
    }
    @FXML
    private void switchToLoginScreen() throws IOException
    {
        App.setRoot("loginScreen");
    }      
    @FXML
    private void switchToPatientDashboard() throws IOException
    {
        App.setRoot("patientDashboard");
    }
    @FXML
    private void switchToDoctorDashboard() throws IOException
    {
        App.setRoot("doctorDashboard");
    }
    @FXML
    private void switchToLabDashboard() throws IOException
    {
        App.setRoot("labDashboard");
    }
    @FXML
    private void switchToPharmaDashboard() throws IOException
    {
        App.setRoot("pharmaDashboard");
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
}
