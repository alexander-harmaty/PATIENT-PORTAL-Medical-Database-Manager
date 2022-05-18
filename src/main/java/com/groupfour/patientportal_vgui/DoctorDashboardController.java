/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;


import io.github.palexdev.materialfx.beans.properties.functional.ConsumerProperty;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;

import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;

import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class DoctorDashboardController implements Initializable 
{   
    //Declare buttons
    @FXML
    private Button button_accountInfo, button_appointments, button_clear, button_logOut, 
            button_go, button_insertRecords, button_patients, button_prescriptions, button_testResults;
    
    //Declare label
    @FXML
    private Label label_errorText;
    
    @FXML
    private Button button_devMenu;

    
    
    //Meow
    @FXML
    private StackPane meow;
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▼ UNORGANIZED & OTHER ▼ //////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Initializes this controller class
     * 
     * @author
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        spinnerNumber();
        comboboxStatus();
        refreshTable(); 
        medicalTable();
        patientSearch();
        medicalSearch();
        pharmacyTable();
        pharmacySearch();
        labTable();
        labSearch();
        appSearch();
    }
    
    //Declare anchor panes
    @FXML
    private AnchorPane panel_accountInfo, panel_appointments, panel_dashboard, 
            panel_medicalRecords, panel_patients, panel_pharmacy,text_doctordashboard, 
            panel_prescriptions, panel_testResults, panel_search, panel_lab;

    //Switch to log-in menu and log-out of current account
    @FXML
    private void handleButton_logOut() throws IOException
    {
        App.setRoot("loginScreen");
    }
    
    //Open developer menu options
    @FXML
    void switchToDevMenu() throws IOException 
    {
        App.setRoot("devMenu");
    }
    
    //Initialize current user ID  
    private String doctorID = App.currentUser.getUserID();
    String currentUserID = App.currentUser.getUserID();
    
    @FXML
    private TextField textField_appsearch;
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ UNORGANIZED & OTHER ▲ //////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ HOME ▼ /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Switch to home anchor pane
     * 
     * @author
     */
    public void show_panelDashboard() 
    {
        panel_dashboard.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
        panel_search.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }

////////////////////////////////////////////////////////////////////////////////
//// ▲ HOME ▲ /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ SEARCH ▼ ///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Angie
     * Search for appointment
     */
    public void appSearch() {
    FilteredList<Appointment> filtereddata = new FilteredList<>(appointmentslist, b -> true);
        textField_appsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddata.setPredicate(doctors -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(doctors.getAppid()).contains(lowerCaseFilter)) {
                    return true;
                }
                else if (String.valueOf(doctors.getDoctorID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (String.valueOf(doctors.getPatientID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (String.valueOf(doctors.getOfficeID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (String.valueOf(doctors.getLabID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (doctors.getReason().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (doctors.getDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (doctors.getTime().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else 
                    return false;
            });
        });
        SortedList<Appointment> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_appointments.comparatorProperty());
        table_appointments.setItems(sortedData);
    
    }   
    
    /**
     * Switch to search anchor pane
     */
    @FXML
    void handleButton_patients() {
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
        panel_search.setVisible(true);
        text_doctordashboard.setVisible(true);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    /**
     * Switch to search patients anchor pane
     * 
     * @author Angela Todaro
     */
    @FXML
    public void handleButton_searchpatients() 
    {
        panel_dashboard.setVisible(false);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_patients.setVisible(true);
        panel_testResults.setVisible(false);
        panel_search.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    /**
     * Switch to search lab anchor pane
     * 
     * @author Angela Todaro
     */
    @FXML
    public void handleButton_searchlab() 
    {
        panel_dashboard.setVisible(false);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_testResults.setVisible(false);
        panel_search.setVisible(false);
        panel_lab.setVisible(true);
    }
    
    /**
     * Switch to search pharmacy anchor pane
     * 
     * @author Angela Todaro
     */
    @FXML
    public void handleButton_searchpharmacy() {
        panel_dashboard.setVisible(false);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_pharmacy.setVisible(true);
        panel_testResults.setVisible(false);
        panel_search.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    /**Author: Angie
     * Variables for all search panels
     */
    
    //Declare columns for pharmacy table
    @FXML
    private TableColumn<PharmacyTable, String>
            column_pharmacyid, column_pharmacystreet, column_pharmacycity, column_pharmacyzip, 
            column_pharmacystate, column_pharmacyphone, column_pharmacyfax, column_pharmacyemail, column_pharmacyname;
    
    //Declare columns for lab table
    @FXML
    private TableColumn<LabTable, String>
            column_labid, column_labstreet, column_labcity, column_labstate, 
            column_labzip, column_labphone, column_labfax, column_labemail, column_labname;
    
    //Declare table view for lab table
    @FXML
    private TableView<LabTable> table_lab;
    
    //Declare table view for pharmacy table
    @FXML
    private TableView<PharmacyTable> table_pharmacy;
    
    //Declare search text fields
    @FXML 
    private TextField textField_pharmacysearch, textField_labsearch;
    
    //Initialize observable list for lab table
    ObservableList<LabTable> lablist = FXCollections.observableArrayList();
    
    //Initialize observable list for pharmacy table
    ObservableList<PharmacyTable> pharmacylist = FXCollections.observableArrayList();
    
    //Initialize observable list for patients table
    ObservableList<PatientTable> patientslist = FXCollections.observableArrayList();
    
    //Declare textfield for patient search
    @FXML
    private TextField textField_search;
    
    //Declare table view for patient table
    @FXML
    private TableView<PatientTable> table_patients;
    
    //Declare column for patient table
    @FXML
    private TableColumn<PatientTable, String> column_patientID, column_pcity, 
            column_pdoctor, column_pemail, column_pfirstname, column_pinsurance, 
            column_pinsuranceid, column_plastname, column_pphone, column_pstate, 
            column_pstreet, column_pzip;
    
    /**
     * Button handler function
     * 
     * @author Angela Todaro
     */
    @FXML
    void handleButton_refresh() { 
        refreshTable();    
    }
    
    /**
     * Function to refresh patient table
     * 
     * @author Angela Todaro
     */
    public void refreshTable(){
        try
        {
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PATIENT");
            patientslist.clear();
            while (rs.next()) 
            {
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
    
    /**
     * Function to search through patient table
     * 
     * @author Angela Todaro
     */
    public void patientSearch() {
        FilteredList<PatientTable> filtereddata = new FilteredList<>(patientslist, b -> true);
        textField_search.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filtereddata.setPredicate(patients -> 
            {
                if (newValue == null || newValue.isEmpty()) { return true; }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(patients.getPatientID()).contains(lowerCaseFilter)) { return true; }
                else if (patients.getPFirstName().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getPLastName().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getPPhone().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getPEmail().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getStreet().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getCity().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getZip().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getState().toLowerCase().contains(lowerCaseFilter)) { return true; }
                if (String.valueOf(patients.getInsuranceID()).contains(lowerCaseFilter)) { return true; }
                else if (patients.getInsurance().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (String.valueOf(patients.getPrimaryDoctor()).contains(lowerCaseFilter)) { return true; }
                else return false;
            });
        });
        SortedList<PatientTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_patients.comparatorProperty());
        table_patients.setItems(sortedData);
    }
    
    /**
     * Function to update patient in patient search table
     * 
     * @author
     */
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
    
    /**
     * Function to delete patient from patient search table
     * 
     * @author
     */
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
    
    /**
     * Function to refresh pharmacy table
     * 
     * @author Angela Todaro
     */
    public void pharmacyTable()
    {
        //button_refreshApp
        //refresh tableview 
        
        try
        {

            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PHARMA");
            pharmacylist.clear();
            
            while (rs.next()) 
            {
                pharmacylist.add(new PharmacyTable(rs.getInt("PharmID"), rs.getString("Street"),
                rs.getString("City"),rs.getString("State"),rs.getString("Zip"), rs.getString("Phone"),
                rs.getString("Fax"),rs.getString("Email"), rs.getString("Name")));
            }
        } 
        catch (Exception e) {}

        column_pharmacyid.setCellValueFactory(new PropertyValueFactory <>("PharmID"));
        column_pharmacystreet.setCellValueFactory(new PropertyValueFactory <>("Street"));
        column_pharmacycity.setCellValueFactory(new PropertyValueFactory <>("City"));
        column_pharmacyzip.setCellValueFactory(new PropertyValueFactory <>("State"));
        column_pharmacystate.setCellValueFactory(new PropertyValueFactory <>("Zip"));
        column_pharmacyphone.setCellValueFactory(new PropertyValueFactory <>("Phone"));
        column_pharmacyfax.setCellValueFactory(new PropertyValueFactory <>("Fax"));
        column_pharmacyemail.setCellValueFactory(new PropertyValueFactory <>("Email"));
         column_pharmacyname.setCellValueFactory(new PropertyValueFactory <>("Name"));

        table_pharmacy.setItems(pharmacylist);
    }
    
    /**
     * Function to search through pharmacy table
     * 
     * @author Angela Todaro
     */
    public void pharmacySearch() 
    {
        FilteredList<PharmacyTable> filtereddata = new FilteredList<>(pharmacylist, b -> true);
        textField_pharmacysearch.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filtereddata.setPredicate(patients -> 
            {
                if (newValue == null || newValue.isEmpty()) { return true; }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(patients.getPharmID()).contains(lowerCaseFilter)) { return true; }
                else if (patients.getStreet().contains(lowerCaseFilter)) { return true; }
                else if (patients.getCity().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getState().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getZip().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getPhone().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getFax().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getEmail().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getName().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else return false;
            });
        });
        SortedList<PharmacyTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_pharmacy.comparatorProperty());
        table_pharmacy.setItems(sortedData);
    }
    
    /**
     * Function to refresh lab table
     * 
     * @author Angela Todaro
     */
    public void labTable()
    {
        //button_refreshApp
        //refresh tableview 
        
        try
        {

            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM LAB");
            lablist.clear();
            
            while (rs.next()) 
            {
                lablist.add(new LabTable(rs.getInt("LabID"), rs.getString("Street"),
                rs.getString("City"),rs.getString("State"),rs.getString("Zip"), rs.getString("Phone"),
                rs.getString("Fax"),rs.getString("Email"), rs.getString("Name")));
            }
        } 
        catch (Exception e) {}

        column_labid.setCellValueFactory(new PropertyValueFactory <>("LabID"));
        column_labstreet.setCellValueFactory(new PropertyValueFactory <>("Street"));
        column_labcity.setCellValueFactory(new PropertyValueFactory <>("City"));
        column_labzip.setCellValueFactory(new PropertyValueFactory <>("State"));
        column_labstate.setCellValueFactory(new PropertyValueFactory <>("Zip"));
        column_labphone.setCellValueFactory(new PropertyValueFactory <>("Phone"));
        column_labfax.setCellValueFactory(new PropertyValueFactory <>("Fax"));
        column_labemail.setCellValueFactory(new PropertyValueFactory <>("Email"));
         column_labname.setCellValueFactory(new PropertyValueFactory <>("Name"));

        table_lab.setItems(lablist);
    }
      
    /**
     * Function to search through lab table
     * 
     * @author Angela Todaro
     */
    public void labSearch() {
        FilteredList<LabTable> filtereddata = new FilteredList<>(lablist, b -> true);
        textField_labsearch.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filtereddata.setPredicate(patients -> 
            {
                if (newValue == null || newValue.isEmpty()) { return true; }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(patients.getLabID()).contains(lowerCaseFilter)) { return true; }
                else if (patients.getStreet().contains(lowerCaseFilter)) { return true; }
                else if (patients.getCity().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getState().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getZip().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getPhone().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getFax().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getEmail().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getName().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else return false;
            });
        });
        SortedList<LabTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_lab.comparatorProperty());
        table_lab.setItems(sortedData);
    }
      
    

////////////////////////////////////////////////////////////////////////////////
//// ▲ SEARCH ▲ ///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ ACCOUNT INFO ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    //Declare text fields for account information
    @FXML
    private TextField textField_firstName, textField_degree, textField_specialty, 
            textField_insuranceCo, textField_insuranceID, textField_lastName, 
            textField_doctorID, textField_email , textField_phone, 
            textField_street, textField_city, textField_zip, textField_state;
    
    /**
     * Function to switch to account info and get account information
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_accountInfo() 
    {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DOCTOR WHERE DoctorID="+App.currentUser.getUserID()+";");

            while (rs.next()) 
            {   
                textField_doctorID.setText(String.valueOf(rs.getInt("DoctorID")));
                textField_firstName.setText(rs.getString("DFirstName"));
                textField_lastName.setText(rs.getString("DLastName"));
                textField_phone.setText(rs.getString("DPhone"));
                textField_email.setText(rs.getString("DEmail"));
                textField_degree.setText((rs.getString("Degree")));
                textField_specialty.setText(rs.getString("Specialty"));              
            }
        }
        catch (Exception e){}
        
        panel_prescriptions.setVisible(false);
        panel_accountInfo.setVisible(true);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
        text_doctordashboard.setVisible(true);
        panel_search.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    /**
     * Function to update account information
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_UpdateDAccInfo() 
    {
        try 
        {
            System.out.println("Connection Success!");
            Connection con = DatabaseConnection.connectDB();
            Statement stmt = con.createStatement();

                int ID = Integer.parseInt(textField_doctorID.getText());
                String fname = textField_firstName.getText();
                String lName = textField_lastName.getText();
                String dNum =  textField_phone.getText();
                String email = textField_email.getText();
                String degree = textField_degree.getText();
                String specialty = textField_specialty.getText();

                String query = "UPDATE DOCTOR " + 
                  "SET  DFirstName ='" + fname + "', DLastName = '" + lName + 
                        "', DPhone = '" + dNum + "', DEmail = '" + email + 
                        "', Degree = '" + degree+ "', Specialty = '" + specialty +
                        "'  WHERE DoctorID = " + ID + ";";

                System.out.println(query);            
                stmt.executeQuery(query);            
                JOptionPane.showMessageDialog(null,"Changes updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {}
        
        JOptionPane.showMessageDialog(null,"Changes updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    

////////////////////////////////////////////////////////////////////////////////
//// ▲ ACCOUNT INFO ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ MEDICAL RECORDS ▼ //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Switch to medical records anchor pane
     * 
     * @author 
     */
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
        panel_search.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    //Declare observable list for medical records table
    ObservableList<MedicalTable> medicalList = FXCollections.observableArrayList();
    
    //Declare text field for medical records search
    @FXML
    private TextField textField_medicalSearch;
    
    //Declare table view for medical records table
    @FXML
    private TableView<MedicalTable> table_medicalrecords;
    
    //Declare columns for medical records
    @FXML
    private TableColumn<MedicalTable, String> column_recordID, column_recorddate, 
            column_weight, column_bloodtype, column_diagnosis, column_dob, 
            column_height, column_patientID2;
    
    /**
     * Function to refresh medical records table
     * 
     * @author Angela Todaro
     */
    public void medicalTable() 
    {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MEDICALRECORDS");
            medicalList.clear();
            while (rs.next()) 
            {
                medicalList.add(new MedicalTable(rs.getInt("RecordID"),rs.getInt("PatientID"),
                rs.getString("DOB"),rs.getString("RecordDate"),rs.getString("Height"),
                rs.getString("Weight"),rs.getString("BloodType"),rs.getString("Diagnosis")));
            }
        } 
        catch (Exception e) {}
 
        column_recordID.setCellValueFactory(new PropertyValueFactory <>("RecordID"));
        column_patientID2.setCellValueFactory(new PropertyValueFactory <>("PatientID"));
        column_dob.setCellValueFactory(new PropertyValueFactory <>("DOB"));
        column_recorddate.setCellValueFactory(new PropertyValueFactory <>("RecordDate"));
        column_height.setCellValueFactory(new PropertyValueFactory <>("Height"));
        column_weight.setCellValueFactory(new PropertyValueFactory <>("Weight"));
        column_bloodtype.setCellValueFactory(new PropertyValueFactory <>("BloodType"));
        column_diagnosis.setCellValueFactory(new PropertyValueFactory <>("Diagnosis"));

        table_medicalrecords.setItems(medicalList);
    }
     
    /**
     * Function to search through medical records table
     * 
     * @author Angela Todaro
     */
    public void medicalSearch() 
    {
        FilteredList<MedicalTable> filtereddata = new FilteredList<>(medicalList, b -> true);
        textField_medicalSearch.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filtereddata.setPredicate(patients -> 
            {
                if (newValue == null || newValue.isEmpty()) { return true; }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(patients.getRecordID()).contains(lowerCaseFilter)) { return true; }
                else if (String.valueOf(patients.getPatientID()).contains(lowerCaseFilter)) { return true; }
                else if (patients.getDOB().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getRecordDate().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getHeight().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getWeight().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getBloodType().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (patients.getDiagnosis().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else return false;
            });
        });
        SortedList<MedicalTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_medicalrecords.comparatorProperty());
        table_medicalrecords.setItems(sortedData);
    }
    
    /**
     * Function to insert a new medical record into medical records table
     */
    @FXML
    void handleButton_medicalInsert() {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("docInsertMRecord.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Button handler function to call medical table refresh
     * 
     * @author Angela Todaro
     */
    @FXML
    void handleButton_medicalRefresh() 
    { 
        medicalTable();    
    }
    
    /**
     * Button handler to delete a medical record
     */
    @FXML
    void handleButton_medicalDelete() 
    {
        
        try 
        {
            Parent root = FXMLLoader.load(getClass().getResource("docDeleteRecord.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Button handler to update a medical record
     * 
     * @author
     */
    @FXML
    void handleButton_medicalUpdate() 
    {
        try 
        {
            Parent root = FXMLLoader.load(getClass().getResource("doctorInsertRecord.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Function to print medical records table
     * 
     * @author Angela Todaro
     */
    @FXML
    public void handleButton_print() 
    {
        try 
        {
            Parent root = FXMLLoader.load(getClass().getResource("docPrintRecord.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

////////////////////////////////////////////////////////////////////////////////
//// ▲ MEDICAL RECORDS ▲ //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ APPOINTMENTS ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Switch to appointments anchor pane
     */
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
        panel_search.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
        
        //update table
        appointmentTable();
    }
    
    //Declare table view for appointments table
    @FXML
    private TableView<Appointment> table_appointments;
    
    //Initialize observable list for appointments table
    ObservableList<Appointment> appointmentslist = FXCollections.observableArrayList();
    
    //Declare columns for appointments table
    @FXML
    private TableColumn<Appointment, String>
            column_appID, column_appRreason, column_appDate, column_appTime, 
            column_appDocID, column_appPatID, column_appOfficeID, column_appLabID;
    
    /**
     * Function to schedule a lab appointment
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_scheduleLabApp()
    {
        //button_scheduleLabApp
        //open labAppointmentInsert.fxml
        try 
        {
            Parent root = FXMLLoader.load(getClass().getResource("labAppointmentInsert.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) 
        {
            Logger.getLogger(PatientDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Function to schedule a doctors appointment
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_scheduleDocApp()
    {
        //button_scheduleDocApp
        //open docAppointmentInsert.fxml
        try 
        {
            Parent root = FXMLLoader.load(getClass().getResource("docAppointmentInsert.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) 
        {
            Logger.getLogger(PatientDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Function to reschedule or cancel an appointment
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_rescheduleOrCancelApp()
    {
        //button_rescheduleOrCancelApp
        //open appointmentModify.fxml
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("appointmentRescheduleOrCancel.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PatientDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Function to refresh appointment table
     * 
     * @author Alexander Harmaty
     */
    public void appointmentTable()
    {
        //button_refreshApp
        //refresh tableview 
        
        try
        {

            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM APPOINTMENT WHERE DoctorID = " + currentUserID);
            appointmentslist.clear();
            
            while (rs.next()) 
            {
                appointmentslist.add(new Appointment(rs.getInt("Appid"), rs.getInt("DoctorID"),
                rs.getInt("PatientID"),rs.getInt("OfficeID"),rs.getInt("LabID"), rs.getString("Reason"),
                rs.getString("Date"),rs.getString("Time")));
            }
        } 
        catch (Exception e) {}

        column_appID.setCellValueFactory(new PropertyValueFactory <>("Appid"));
        column_appRreason.setCellValueFactory(new PropertyValueFactory <>("Reason"));
        column_appDate.setCellValueFactory(new PropertyValueFactory <>("Date"));
        column_appTime.setCellValueFactory(new PropertyValueFactory <>("Time"));
        column_appDocID.setCellValueFactory(new PropertyValueFactory <>("DoctorID"));
        column_appPatID.setCellValueFactory(new PropertyValueFactory <>("PatientID"));
        column_appOfficeID.setCellValueFactory(new PropertyValueFactory <>("OfficeID"));
        column_appLabID.setCellValueFactory(new PropertyValueFactory <>("LabID"));

        table_appointments.setItems(appointmentslist);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ APPOINTMENTS ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ PRESCRIPTIONS ▼ ////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    //Declare status combo box for prescriptions
    @FXML
    private MFXComboBox combobox_status;
    
    //Declare datapicker for prescriptions
    @FXML
    private MFXDatePicker datepicker_date;
    
    //Declare spinner for prescriptions
    @FXML
    private MFXSpinner<Integer> spinner_quantity;
    
    //Declare text area for prescriptions
    @FXML
    private TextArea textField_description;
    
    //Declare text fields for prescriptions
    @FXML
    private TextField textField_dosage, textField_medication, textField_frequency, 
            textField_scriptID, textField_pharmID, textField_patientID;
    
    /**
     * Function for spinner
     * 
     * @author Angela Todaro
     */
    public void spinnerNumber() 
    {
        IntegerSpinnerModel count = new IntegerSpinnerModel(0);
        count.next();
        spinner_quantity.setSpinnerModel(count);
        //want to add it editable but cant figure it out atm
    }
    
    //Declare text field for doctor ID
    @FXML
    private TextField  textField_doctorID2;
    
    /**
     * Switch to prescriptions anchor pane
     * 
     * @author Angela Todaro
     */
    @FXML
    void handleButton_prescriptions() {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DOCTOR WHERE DoctorID="+App.currentUser.getUserID()+";");

            while (rs.next()) 
            {   
                textField_doctorID2.setText(String.valueOf(rs.getInt("DoctorID")));
            }
        }
        catch (Exception e) {}
        panel_prescriptions.setVisible(true);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_patients.setVisible(false);
        panel_testResults.setVisible(false);
        text_doctordashboard.setVisible(true);
        panel_search.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }

    /**
     * Function to set status combo box (drop-down menu) for prescriptions
     * 
     * @author Angela Todaro
     */
    public void comboboxStatus() 
    {        
        ObservableList<String> list = FXCollections.observableArrayList("Processing", "Cancelled", "Refill");
        combobox_status.setItems(list);   
    }
    
    /**
     * Function to place order for prescription
     * 
     * @author Angela Todaro
     */
    @FXML
    void handleButton_order()
    { 
        try 
        {
            Connection con = DatabaseConnection.connectDB();
            Statement stmt = con.createStatement();

            String medication = textField_medication.getText();
            String description = textField_description.getText();
            String date = datepicker_date.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));         


            int patientID = Integer.parseInt(textField_patientID.getText());
            int pharmID = Integer.parseInt(textField_pharmID.getText());
            int doctorID = Integer.parseInt(textField_doctorID2.getText());
            String status = combobox_status.getSelectionModel().getSelectedItem().toString();
            String frequency = textField_frequency.getText();
            String dosage = textField_dosage.getText();
            int quantity = spinner_quantity.getValue();

            String addPrescription = "INSERT INTO SCRIPTDOC (Medication, Description, DATE, PatientID, PharmID, DoctorID, Status, Frequency, Dosage, Quantity)" 
                    + " VALUES  ('" +medication+ "','" +description+ "','" +date+ "','" + patientID+ "','" +pharmID+ "','" + doctorID + "','" + status + "','" + frequency + "','" + dosage + "','" + quantity + "')";

            //System.out.println(addRecord);          
            //NOTE:   EXECUTE statement for insert, update, delete; executeQuery for data retrieval like select
            stmt.execute(addPrescription);

            // JOptionPane.showMessageDialog(null,"New Record Added!");
        }
        catch (Exception e) 
        {
            System.out.print(e);
        }
        
        JOptionPane.showMessageDialog(null,"Prescription order placed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

////////////////////////////////////////////////////////////////////////////////
//// ▲ PRESCRIPTIONS ▲ ////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ TEST RESULTS ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////   
    
    
    
    /**
     * Switch to test results anchor pane
     * 
     * @author 
     */
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
        panel_search.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }

////////////////////////////////////////////////////////////////////////////////
//// ▲ TEST RESULTS ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ END OF CLASS ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////



    
}//END OF DOCTOR DASHBOARD CONTROLLER CLASS
