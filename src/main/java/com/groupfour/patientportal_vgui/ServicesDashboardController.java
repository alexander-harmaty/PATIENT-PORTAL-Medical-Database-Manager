/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ServicesDashboardController implements Initializable {

    @FXML
    private Button button_search;
    @FXML
    private Button button_accountInfo;
    @FXML
    private Button button_medicalRecords;
    @FXML
    private Button button_testResults;
    @FXML
    private Button button_prescriptions;
    @FXML
    private Button button_appointments;
    @FXML
    private Button button_devMenu;
    

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void switchToDevMenu() throws IOException 
    {
        App.setRoot("devMenu");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
