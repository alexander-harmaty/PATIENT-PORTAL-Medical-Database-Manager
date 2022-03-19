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

    @FXML
    private Button buttonViewPatient;
    @FXML
    private Button buttonViewDoctor;
    @FXML
    private Button buttonUpdatePatient;
    @FXML
    private Button buttonUpdateDoctor;
    @FXML
    private Button buttonDeletePatient;
    @FXML
    private Button buttonQuitApp;
    
    @FXML
    private void handleButtonViewPatient() throws IOException
    {
        switchToSecondary();
    }
    @FXML
    private void handlebuttonViewDoctor()
    {
        
    }
    @FXML
    private void handlebuttonUpdatePatient()
    {
        
    }
    @FXML
    private void handlebuttonUpdateDoctor()
    {
        
    }
    @FXML
    private void handlebuttonDeletePatient()
    {
        
    }
    @FXML
    private void handlebuttonQuitApp()
    {
        System.exit(0);
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
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
