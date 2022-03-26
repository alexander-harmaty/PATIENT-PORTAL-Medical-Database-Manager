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
    @FXML
    private Button buttonViewPatient;
    
    @FXML
    private Button buttonQuitApp;
    
    //Button Handler Methods
    @FXML
    private void handlebuttonViewPatient() throws IOException
    {
        switchToViewPatient();
    }
    
    @FXML
    private void handlebuttonQuitApp()
    {
        System.exit(0);
    }
    
    @FXML
    private void switchToViewPatient() throws IOException 
    {
        App.setRoot("viewPatientInfo");
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
