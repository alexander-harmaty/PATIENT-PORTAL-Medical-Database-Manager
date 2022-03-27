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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class DoctorDashboardController implements Initializable 
{
    String user = "pportal";
    String pwd = "admin";
    
    int patientID;
    
    @FXML
    private Button button_accountInfo;

    @FXML
    private Button button_appointments;

    @FXML
    private Button button_clear;

    @FXML
    private Button button_devMenu;

    @FXML
    private Button button_go;

    @FXML
    private Button button_insertRecords;

    @FXML
    private Button button_patients;

    @FXML
    private Button button_prescriptions;

    @FXML
    private Button button_testResults;

    @FXML
    private Label label_errorText;

    @FXML
    private AnchorPane panel_accountInfo;

    @FXML
    private AnchorPane panel_appointments;

    @FXML
    private AnchorPane panel_dashboard;

    @FXML
    private AnchorPane panel_medicalRecords;

    @FXML
    private AnchorPane panel_patients;

    @FXML
    private AnchorPane panel_prescriptions;

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
    void handleButton_accountInfo() {
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(true);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_appointments() {
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(true);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_clear() {
        
    }

    @FXML
    void handleButton_go() {
        
    }

    @FXML
    void handleButton_insertRecords() {
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(true);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_patients() {
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(true);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_prescriptions() {
        panel_prescriptions.setVisible(true);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
    }

    @FXML
    void handleButton_testResults() {
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(true);
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
