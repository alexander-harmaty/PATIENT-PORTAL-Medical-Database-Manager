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
