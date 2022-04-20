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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
    private Button button_servicesView;
    
    
    
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
        //App.setRoot("patientDashboard");
        Parent root = FXMLLoader.load(getClass().getResource("patientDashboard.fxml")); 
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show(); 
    }
    @FXML
    private void switchToDoctorDashboard() throws IOException
    {
        //App.setRoot("doctorDashboard");
        Parent root = FXMLLoader.load(getClass().getResource("doctorDashboard.fxml")); 
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show(); 
    }
    @FXML
    private void switchToServicesDashboard() throws IOException
    {
        //App.setRoot("servicesDashboard");
        Parent root = FXMLLoader.load(getClass().getResource("servicesDashboard.fxml")); 
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show(); 
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
