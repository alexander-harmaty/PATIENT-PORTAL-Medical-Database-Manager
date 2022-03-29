/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private AnchorPane text_doctordashboard;

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
    private TextField textField_street;
    
    @FXML
    private TextField textField_city;
    
    @FXML
    private TextField textField_zip;
    
    @FXML
    private TextField textField_state;
    
    ////TABLEVIEW 
    @FXML
    private TableView<PatientTable> table_patients;
    
    @FXML
    private TableColumn<PatientTable, String> column_patientID;

    @FXML
    private TableColumn<PatientTable, String> column_pcity;

    @FXML
    private TableColumn<PatientTable, String> column_pdoctor;

    @FXML
    private TableColumn<PatientTable, String> column_pemail;

    @FXML
    private TableColumn<PatientTable, String> column_pfirstname;

    @FXML
    private TableColumn<PatientTable, String> column_pinsurance;

    @FXML
    private TableColumn<PatientTable, String> column_pinsuranceid;

    @FXML
    private TableColumn<PatientTable, String> column_plastname;

    @FXML
    private TableColumn<PatientTable, String> column_pphone;

    @FXML
    private TableColumn<PatientTable, String> column_pstate;

    @FXML
    private TableColumn<PatientTable, String> column_pstreet;

    @FXML
    private TableColumn<PatientTable, String> column_pzip;
    
    
    
    
    
    int index = -1;
    
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    PatientTable pt = null;
    

    @FXML
    void handleButton_refresh() {     
    }
    
    
    
    @FXML
    void handleButton_update() {
        
    }
    
    @FXML
    void handleButton_insert() {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("doctorInsertPatient.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    @FXML
    void handleButton_accountInfo() {
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(true);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
        text_doctordashboard.setVisible(true);
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
        text_doctordashboard.setVisible(true);
    }

    @FXML
    void handleButton_clear() {
        
    }

    @FXML
    void handleButton_go() {
        
    }
    
//    @FXML
//    void handleButton_save() {
//       // String 
//    }

    @FXML
    void handleButton_insertRecords() {
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(true);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
        text_doctordashboard.setVisible(true);
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
        text_doctordashboard.setVisible(true);
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
        text_doctordashboard.setVisible(true);
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
        text_doctordashboard.setVisible(true);
    }

    @FXML
    void switchToDevMenu() throws IOException 
    {
        App.setRoot("devMenu");
    }

    /**
     * Initializes the controller class.
     */
    
    ObservableList<PatientTable> patientslist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 try{
     
    Connection con = DatabaseConnection.connectDB();
    ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PATIENT");
       while (rs.next()) {
            patientslist.add(new PatientTable(rs.getInt("PatientID"),rs.getString("PFirstName"),
            rs.getString("PLastName"),rs.getString("PPhone"),rs.getString("PEmail"),
            rs.getString("Street"),rs.getString("City"),rs.getString("Zip"),
            rs.getString("State"),rs.getInt("InsuranceID"),rs.getString("Insurance"),
            rs.getInt("PrimaryDoctor")));
        }
 } catch (Exception e) {
     
 }
        column_patientID.setCellValueFactory(new PropertyValueFactory <>("PatientID"));
        column_pfirstname.setCellValueFactory(new PropertyValueFactory <>("PFirstName"));
        column_plastname.setCellValueFactory(new PropertyValueFactory <>("PLastName"));
        column_pphone.setCellValueFactory(new PropertyValueFactory <>("PPhone"));
        column_pemail.setCellValueFactory(new PropertyValueFactory <>("PEmail"));
        column_pstreet.setCellValueFactory(new PropertyValueFactory <>("Street"));
        column_pcity.setCellValueFactory(new PropertyValueFactory <>("City"));
        column_pzip.setCellValueFactory(new PropertyValueFactory <>("Zip"));
        column_pstate.setCellValueFactory(new PropertyValueFactory <>("State"));
        column_pinsuranceid.setCellValueFactory(new PropertyValueFactory <>("InsuranceID"));
        column_pinsurance.setCellValueFactory(new PropertyValueFactory <>("Insurance"));
        column_pdoctor.setCellValueFactory(new PropertyValueFactory <>("PrimaryDoctor"));
        table_patients.setItems(patientslist);
        // TODO
    }    
    
}
