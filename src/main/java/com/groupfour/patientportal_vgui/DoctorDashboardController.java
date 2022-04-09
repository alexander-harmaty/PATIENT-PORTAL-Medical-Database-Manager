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
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private TextField textField_doctorID;

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
    
    @FXML
    private TextField textField_search;
    ////TABLEVIEW 
    @FXML
    private TableView<PatientTable> table_patients;
    
//     @FXML
//    private TableView<MedicalRecordTable> table_medicalrecords;
    
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
    
    //currentUserID   
    private String doctorID = App.currentUser.getUserID();
    
    @FXML
    void handleButton_refresh() { 
        refreshTable();    
    }
    
    
    @FXML
    void handleButton_delete() {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("docDeletePatient.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void handleButton_update() {
        
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
        
        textField_doctorID.setText(App.currentUser.getUserID());
        handleButton_go();
        
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

    private String doctorIDText;
    @FXML
    private void handleButton_go()
    {
        try
        {
            doctorIDText = textField_doctorID.getText();
            label_errorText.setText("");

            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //cant be cipher
            //Connection con = DriverManager.getConnection("jdbc:sqlserver://24.189.211.114:1433;"
                    //+ "databaseName=PatientPortal;encrypt=true;trustServerCertificate=true;", user, pwd);
            Connection con = DatabaseConnection.connectDB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DOCTOR WHERE DoctorID="+doctorIDText+";");

            while (rs.next()) 
            {   //displays data. there must be a simpler way to implement

                textField_doctorID.setText(String.valueOf(rs.getInt("DoctorID")));
                //System.out.println("PATIENT ID: " + rs.getInt("DoctorID"));

                textField_firstName.setText(rs.getString("DFirstName"));
                //System.out.println("FIRST NAME: " + rs.getString("DFirstName"));

                textField_lastName.setText(rs.getString("DLastName"));
                //System.out.println("LAST NAME: " + rs.getString("DLastName"));

                textField_phone.setText(rs.getString("DPhone"));
                //System.out.println("PHONE NUMBER: " + rs.getString("DPhone"));

                textField_email.setText(rs.getString("DEmail"));
                //System.out.println("EMAIL: " + rs.getString("DEmail"));

                //textField_insuranceID.setText(String.valueOf(rs.getInt("InsuranceID")));
                //System.out.println("INSURANCE ID: " + rs.getInt("InsuranceID"));

                //textField_insuranceCo.setText(rs.getString("Insurance"));
                //System.out.println("INSURANCE COMPANY: " + rs.getString("Insurance"));
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
        this.textField_doctorID.clear();
        this.textField_firstName.clear();
        this.textField_lastName.clear();
        this.textField_phone.clear();
        this.textField_email.clear();
        this.textField_insuranceID.clear();
        this.textField_insuranceCo.clear();
        this.label_errorText.setText("");
        doctorID = "";
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
    
    public void refreshTable(){
        try{
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PATIENT");
            patientslist.clear();
                while (rs.next()) {
                    patientslist.add(new PatientTable(rs.getInt("PatientID"),rs.getString("PFirstName"),
                    rs.getString("PLastName"),rs.getString("PPhone"),rs.getString("PEmail"),
                    rs.getString("Street"),rs.getString("City"),rs.getString("Zip"),
                    rs.getString("State"),rs.getInt("InsuranceID"),rs.getString("Insurance"),
                    rs.getInt("PrimaryDoctor")));
                    }
            } 
        catch (Exception e) {}
 
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
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable(); 
        FilteredList<PatientTable> filtereddata = new FilteredList<>(patientslist, b -> true);
        textField_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddata.setPredicate(patients -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(patients.getPatientID()).contains(lowerCaseFilter)) {
                    return true;
                }
                else if (patients.getPFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getPLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getPPhone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getPEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getStreet().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getCity().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getZip().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getState().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                if (String.valueOf(patients.getInsuranceID()).contains(lowerCaseFilter)) {
                    return true;
                }
                else if (patients.getInsurance().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (String.valueOf(patients.getPrimaryDoctor()).contains(lowerCaseFilter)) {
                    return true;
                }
                else 
                    return false;
            });
        });
        SortedList<PatientTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_patients.comparatorProperty());
        table_patients.setItems(sortedData);
    }    
    
}
