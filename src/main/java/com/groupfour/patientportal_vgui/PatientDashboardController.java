/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Alexander Harmaty, Yasin Khan, Brian Noss, Christopher Scheer, Angela Todaro
 */
public class PatientDashboardController implements Initializable 
{
    
////////////////////////////////////////////////////////////////////////////////
//// ▼ UNORGANIZED & OTHER ▼ //////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Initialize this controller class
     * 
     * @author
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
        prescriptionTable();
        appointmentTable();
        medicalTable();
        searchMedical();
        pharmaTable();
        searchPharma(); 
        ECTable();
        RefreshECTable();
        pharmacyTable();
        pharmacySearch();
        labTable();
        labSearch();
        searchPrescription();
        appSearch();
        FilteredList<DoctorTable> filtereddata = new FilteredList<>(doctorslist, b -> true);
        textField_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddata.setPredicate(doctors -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(doctors.getDoctorID()).contains(lowerCaseFilter)) {
                    return true;
                }
                else if (doctors.getDFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (doctors.getDLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (doctors.getDPhone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (doctors.getDEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (doctors.getDegree().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (doctors.getSpecialty().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
      
                else 
                    return false;
            });
        });
        SortedList<DoctorTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_doctor.comparatorProperty());
        table_doctor.setItems(sortedData);
    }
    
    //Initialize observable list for lab table
    ObservableList<LabTable> lablist = FXCollections.observableArrayList();
    
    //Initialize observable list for pharmacy table
    ObservableList<PharmacyTable> pharmacylist = FXCollections.observableArrayList();
    
    //Declare search text fields
    @FXML 
    private TextField textField_pharmacysearch, textField_labsearch, textField_appsearch;
    
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
    
    @FXML
    private AnchorPane panel_accountInfo, panel_appointments, panel_dashboard,
            panel_medicalRecords, panel_prescriptions, panel_search, panel_testResults, panel_findPharma, 
            panel_doctors, panel_pharmacy, panel_lab;
    
    //Declare buttons to switch anchor panes
    @FXML
    private Button button_accountInfo, button_appointments, button_devMenu,
            button_logOut, button_home, button_medicalRecords,
            button_prescriptions, button_search, button_testResults;
    
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
    private String patientID = App.currentUser.getUserID();
    
    //Attempt to display current users name on sign-in
    public  void setLabelUserFirstLast()
    {
        label_userFirstLast.setText("test");
    }
    @FXML
    private Label label_errorText, label_userFirstLast;
    
     /**
     * Function to refresh pharmacy table
     * 
     * @author Angela Todaro
     */
   ////  ▲ UNORGANIZED AND OTHER ▲/////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////// 
   //// ▼ SEARCH AND TABLEVIEWS ▼ /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
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
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
        panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
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
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
        panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(true);
        panel_lab.setVisible(false);
    }
    
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
     * Author: Angie
     * Added pharmacy table
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
//// ▲ SEARCH AND TABLEVIEWS ▲ //////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ HOME ▼ /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Switch to home anchor pane
     * 
     * @author Alexander Harmaty
     */
    public void show_panelDashboard() 
    {
        panel_dashboard.setVisible(true);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
        panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ HOME ▲ /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ DOCTORS ▼ //////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Switch to search doctors anchor pane
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_search() 
    {
        panel_search.setVisible(true);
        
        panel_dashboard.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
        panel_doctors.setVisible(false);
         panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    //Declare table for list of doctors
    @FXML
    private TableView<DoctorTable> table_doctor;
    
    //Declare columns for doctor table
    @FXML
    private TableColumn<DoctorTable, String>
            column_demail, column_dfirstname, column_dlastname, column_doctorid,
            column_dphone, column_degree, column_specialty;
    
    //Initialize observable list for doctor table
    ObservableList<DoctorTable> doctorslist = FXCollections.observableArrayList();
    
    /**
     * Function to refresh doctor table
     * 
     * @author
     */
    public void refreshTable() 
    {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM DOCTOR");
            doctorslist.clear();
            while (rs.next()) 
            {
                doctorslist.add(new DoctorTable(rs.getInt("DoctorID"),rs.getString("DFirstName"),
                rs.getString("DLastName"),rs.getString("DPhone"),rs.getString("DEmail"),
                rs.getString("Degree"),rs.getString("Specialty")));
            }
        }
        catch (Exception e) {}
        
        column_doctorid.setCellValueFactory(new PropertyValueFactory <>("DoctorID"));
        column_dfirstname.setCellValueFactory(new PropertyValueFactory <>("DFirstName"));
        column_dlastname.setCellValueFactory(new PropertyValueFactory <>("DLastName"));
        column_dphone.setCellValueFactory(new PropertyValueFactory <>("DPhone"));
        column_demail.setCellValueFactory(new PropertyValueFactory <>("DEmail"));
        column_degree.setCellValueFactory(new PropertyValueFactory <>("Degree"));
        column_specialty.setCellValueFactory(new PropertyValueFactory <>("Specialty"));
        table_doctor.setItems(doctorslist);
    }
    
    @FXML
    public void handleButton_searchdoctors() 
    {
        panel_search.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
        panel_doctors.setVisible(true);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ DOCTORS ▲ //////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ ACCOUNT INFO ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Switch to account info anchor pane
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
            ResultSet rs = st.executeQuery("SELECT * FROM PATIENT WHERE PatientID="+patientID+";");

            while (rs.next()) 
            {   
                    textField_patientID.setText(String.valueOf(rs.getInt("PatientID")));                  
                    textField_firstName.setText(rs.getString("PFirstName"));                   
                    textField_lastName.setText(rs.getString("PLastName"));
                    textField_phone.setText(rs.getString("PPhone"));
                    textField_email.setText(rs.getString("PEmail"));                   
                    textField_street.setText(rs.getString("Street"));                    
                    textField_city.setText(rs.getString("City"));                   
                    textField_zip.setText(rs.getString("Zip"));                   
                    textField_state.setText(rs.getString("State"));
                    textField_insuranceID.setText(String.valueOf(rs.getInt("InsuranceID")));
                    textField_insuranceCo.setText(rs.getString("Insurance"));                   
                    textField_primarydoc.setText(String.valueOf(rs.getInt("PrimaryDoctor")));
            }
        }
        catch (Exception e){}
        
        panel_accountInfo.setVisible(true);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
         panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    //Declare text fields for account information
    @FXML
    private TextField textField_search, textField_medicalSearch, textField_email, textField_firstName, 
            textField_insuranceCo, textField_insuranceID, textField_lastName, 
            textField_patientID, textField_phone, textField_primarydoc, 
            textField_street, textField_city, textField_zip, textField_state, textField_searchPharma,
            textField_prescriptionsearch;
    
    //Declare table view for emergency contacts
    @FXML
    private TableView<ECTable> table_EC;
    
    //Declare columns for emergeccy contacts table
    @FXML
    private TableColumn<ECTable, String> column_ecfirst, column_eclast, 
            column_ecphone, column_ecrelation;
    
    //Initialize observable list for emergenct contacts
    ObservableList<ECTable> ECList = FXCollections.observableArrayList();
    
    /**
     * Function to update patient account information
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_UpdatePAccInfo() 
    {
        try
        {
            System.out.println("Connection Success!");
            Connection con = DatabaseConnection.connectDB();
            Statement stmt = con.createStatement();
            
            int ID = Integer.parseInt(textField_patientID.getText());
            String fname = textField_firstName.getText();
            String lName = textField_lastName.getText();
            String pNum =  textField_phone.getText();
            String email = textField_email.getText();
            String street = textField_street.getText();
            String city = textField_city.getText();
            String zip = textField_zip.getText();
            String state =  textField_state.getText();
            int insID =  Integer.parseInt(textField_insuranceID.getText());
            String insur =  textField_insuranceCo.getText();
            int prim = Integer.parseInt(textField_primarydoc.getText());
            
            String query = "UPDATE PATIENT " + 
              "SET  pFirstName ='" + fname + "', PLastName = '" + lName + 
                    "', PPhone = '" + pNum + "', PEmail = '" + email + 
                    "', Street = '" + street +"', City = '"+ city +
                    "', Zip = '" + zip + "', State = '" + state + 
                    "', InsuranceID = " + insID + ", Insurance = '" + insur + 
                    "', PrimaryDoctor = " + prim +
                    " WHERE PatientID = " + ID + ";";
            
            System.out.println(query);
            stmt.executeQuery(query);
        } catch (Exception e) {}
        
        JOptionPane.showMessageDialog(null,"Account information updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Initialize emergency contact table
     * 
     * @author
     */
    public void ECTable() 
    {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM EC WHERE PatientID = " + patientID);
            ECList.clear();
            while (rs.next()) 
            {
                ECList.add(new ECTable(rs.getString("ECfirst"),
                rs.getString("EClast"),rs.getString("ECphone"),rs.getString("Relation")));
            }
        }
        catch (Exception e) {}
 
        column_ecfirst.setCellValueFactory(new PropertyValueFactory <>("ECfirst"));
        column_eclast.setCellValueFactory(new PropertyValueFactory <>("EClast"));
        column_ecphone.setCellValueFactory(new PropertyValueFactory <>("ECphone"));
        column_ecrelation.setCellValueFactory(new PropertyValueFactory <>("Relation"));

        table_EC.setItems(ECList);
    }    
    
    /**
     * Button handler function to refresh the emergency contact table
     * 
     * @author
     */
    @FXML
    void handleButton_refreshEC()
    {
        RefreshECTable();
    }
    
    /**
     * Function to refresh emergency contact table
     * 
     * @author
     */
    public void RefreshECTable()
    {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM EC WHERE PatientID = " + patientID);
            ECList.clear();
            while (rs.next()) 
            {
                ECList.add(new ECTable(rs.getString("ECfirst"),
                rs.getString("EClast"),rs.getString("ECphone"),rs.getString("Relation")));

            }
        } 
        catch (Exception e) {}
 
        column_ecfirst.setCellValueFactory(new PropertyValueFactory <>("ECfirst"));
        column_eclast.setCellValueFactory(new PropertyValueFactory <>("EClast"));
        column_ecphone.setCellValueFactory(new PropertyValueFactory <>("ECphone"));
        column_ecrelation.setCellValueFactory(new PropertyValueFactory <>("Relation"));

        table_EC.setItems(ECList);
    }
     
    /**
     * Button handler function to add an emergency contact
     * 
     * @author
     */
    @FXML
    void handleButton_addEC()
    {
        try 
        {
            Parent root = FXMLLoader.load(getClass().getResource("ecadd.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) 
        {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Button handler function to delete an emergency contact
     * 
     * @author
     */
    @FXML
    void handleButon_deleteEC()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ecdel.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    

////////////////////////////////////////////////////////////////////////////////
//// ▲ ACCOUNT INFO ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ MEDICAL RECORDS ▼ //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    
    
    /**
     * Button handler function to switch to medical records anchor pane
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_medicalRecords() 
    {
        panel_medicalRecords.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
         panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    //Declare table view for medical records table
    @FXML
    private TableView<MedicalTable> table_medicalrecords;
    
    //Declare columns for medical records table
    @FXML
    private TableColumn<MedicalTable, String> column_recordID, column_recorddate, 
            column_weight, column_bloodtype, column_diagnosis, 
            column_dob, column_height, column_patientID2;
    
    //Initialize observable list for medical records table
    ObservableList<MedicalTable> medicalList = FXCollections.observableArrayList();
    
    /**
     * Function to initialize medical records table
     * 
     * @author
     */
    public void medicalTable() 
    {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MEDICALRECORDS WHERE PatientID = " + patientID);
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
     * Button handler function to print medical records table
     * 
     * @author
     */
    @FXML
    void handleButton_printMedical() 
    {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            Statement st = con.createStatement();
            //  int ID = Integer.parseInt(textField_recordprintID.getText());
            String sql = "SELECT * FROM MEDICALRECORDS WHERE PatientID = " + patientID;
            ResultSet rs = st.executeQuery(sql);
            
            //FileChooser
            FileChooser fc;
            fc = new FileChooser();
            File selectedFile;
            selectedFile = fc.showSaveDialog(null);
            
            if(selectedFile != null)
            {
                System.out.println("\nName & Path Selected!");
                System.out.println(selectedFile);

                PrintStream ps = null;

                try
                {
                    ps = new PrintStream(selectedFile);
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("File Not Found!");
                    //break;
                }

                //cg.report(ps);
                while (rs.next()) 
                {
                    System.out.println("Got here");
                    int ID1 = rs.getInt("RecordID");
                    int ID2 = rs.getInt("PatientID");
                    String dob = rs.getString("DOB");
                    String recorddate = rs.getString("RecordDate");
                    String height = rs.getString("Height");
                    String weight = rs.getString("Weight");
                    String bloodtype = rs.getString("BloodType");
                    String diagnosis = rs.getString("Diagnosis");

                    ps.append("OFFICIAL MEDICAL RECORD" + "\n----------------------" + "\nRecordID: "+ID1+
                    "\n\nPatientID: "+ID2+"\n\nDate of Birth: "+dob+"\n\nRecord Date: "+ recorddate +
                    "\n\nHeight: " + height + "\n\nWeight: " + weight + "\n\nBlood Type: " + bloodtype +
                    "\n\nDiagnosis: " + diagnosis +"\n\n");
                }
                System.out.println("\nReport Written!\n");
            }
            else
            {
                System.out.println("File Chooser Closed!");
            }
            
            rs.close();
            //bw.close();
            // fout.close();
            
        } // End of try statement
        catch (Exception e) 
        {
            System.out.print(e);
        } 
    }
    
    /**
     * Function to search through medical records table
     * 
     * @author
     */
    public void searchMedical() 
    {
        FilteredList<MedicalTable> filtereddata = new FilteredList<>(medicalList, b -> true);
        textField_medicalSearch.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filtereddata.setPredicate(medical -> 
            {
                if (newValue == null || newValue.isEmpty()) { return true; }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(medical.getRecordID()).contains(lowerCaseFilter)) { return true; }                
                else if (String.valueOf(medical.getPatientID()).toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getDOB().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getRecordDate().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getHeight().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getWeight().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getBloodType().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getDiagnosis().toLowerCase().contains(lowerCaseFilter)) { return true; }      
                else return false;
            });
        });
        SortedList<MedicalTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_medicalrecords.comparatorProperty());
        table_medicalrecords.setItems(sortedData);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ MEDICAL RECORDS ▲ //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ APPOINTMENTS ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Switch to appointments anchor pane
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_appointments() 
    {
        panel_appointments.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
         panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
        
    }
    
    //Declare scheduling buttons
    @FXML
    private MFXButton button_scheduleDocApp, button_scheduleLabApp, 
            button_rescheduleOrCancelApp, button_refreshApp;
    
    //Initialize observable list for appointments table
    ObservableList<Appointment> appointmentslist = FXCollections.observableArrayList();
    
    //Initialize table view for appointments table
    @FXML
    private TableView<Appointment> table_appointments;
    
    //Initialize columns for appointments table
    @FXML
    private TableColumn<Appointment, String>
            column_appID, column_appRreason, column_appDate, column_appTime, 
            column_appDocID, column_appPatID, column_appOfficeID, column_appLabID;
    
    /**
     * Button handler function to schedule a doctors appointment
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
     * Button handler function to schedule a lab appointment
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
     * Button handler function to reschedule or cancel any appointment
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
     * Function to refresh the appointments table
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
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM APPOINTMENT WHERE PatientID = " + patientID);
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

    
    
    /**
     * Switch to prescriptions anchor pane
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_prescriptions() 
    {
        panel_prescriptions.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
         panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    /**
     * Author: Angie
     * Search through prescriptions
     */
    public void searchPrescription() 
    {
        FilteredList<PrescriptionTable> filtereddata = new FilteredList<>(prescriptionList, b -> true);
        textField_prescriptionsearch.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filtereddata.setPredicate(medical -> 
            {
                if (newValue == null || newValue.isEmpty()) { return true; }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(medical.getScriptID()).contains(lowerCaseFilter)) { return true; }                
                else if (String.valueOf(medical.getPatientID()).toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (String.valueOf(medical.getDoctorID()).toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (String.valueOf(medical.getPharmID()).toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getMedication().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getDescription().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getDATE().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (medical.getStatus().toLowerCase().contains(lowerCaseFilter)) { return true; }
                else if (medical.getFrequency().toLowerCase().contains(lowerCaseFilter)) { return true; }      
                else if (medical.getDosage().toLowerCase().contains(lowerCaseFilter)) { return true; }      
                else if (medical.getQuantity().toLowerCase().contains(lowerCaseFilter)) { return true; }      
                else return false;
            });
        });
        SortedList<PrescriptionTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_prescriptions.comparatorProperty());
        table_prescriptions.setItems(sortedData);
    }
    
    /**
     * Switch to find pharmacy anchor pane
     * 
     * @author
     */
    @FXML
    void handleButton_findPharma() 
    {
        panel_findPharma.setVisible(true);
        
        panel_prescriptions.setVisible(false); 
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
         panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    /**
     * Switch back to prescriptions anchor pane
     * 
     * @author
     */
    @FXML
     void handleButton_backToScript() 
     {
        panel_prescriptions.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
         panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
     //Declare table view for prescriptions table
    @FXML
    private TableView<PrescriptionTable> table_prescriptions;
    
    //Declare columns for prescriptions table
    @FXML
    private TableColumn<PrescriptionTable, String> 
            column_date, column_description, column_doctorid2, column_dosage, 
            column_frequency, column_medication, column_patientid, column_pharmid, 
            column_quantity, column_status, column_scriptid;
    
    //Initialize observable list for prescriptions table
    ObservableList<PrescriptionTable> prescriptionList = FXCollections.observableArrayList();
    
    //Declare table view for pharmacy table
    @FXML
    private TableView<PharmaTable> table_pharmaLocation;
    
    //Initialize observable list for pharmacy table
    ObservableList<PharmaTable> pharmaList = FXCollections.observableArrayList();
    
    //Declare columns for pharmacy table
    @FXML
    private TableColumn<PharmaTable, String> 
            column_femail,column_ffax,column_fname,
            column_fpharmid, column_fphone,column_fstate,
            column_fstreet,column_fzip, column_fcity;

    //Declare button to switch back to prescriptions anchor pane
    @FXML
    private MFXButton button_backToScript;
    
    /**
     * Function to refresh prescriptions table
     * 
     * @author
     */
    public void prescriptionTable() {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM SCRIPTDOC WHERE PatientID = " + patientID);
            prescriptionList.clear();
            while (rs.next()) 
            {
                prescriptionList.add(new PrescriptionTable( rs.getInt("PatientID"), rs.getInt("PharmID"),
                rs.getInt("DoctorID"),rs.getInt("ScriptID"),rs.getString("Medication"),rs.getString("Description"),
                rs.getString("Date"),rs.getString("Status"),rs.getString("Frequency"),
                rs.getString("Dosage"), rs.getString("Quantity"))); 
            }
        } 
        catch (Exception e) {}
 
        column_medication.setCellValueFactory(new PropertyValueFactory <>("Medication"));
        column_description.setCellValueFactory(new PropertyValueFactory <>("Description"));
        column_date.setCellValueFactory(new PropertyValueFactory <>("DATE"));
        column_patientid.setCellValueFactory(new PropertyValueFactory <>("PatientID"));
        column_pharmid.setCellValueFactory(new PropertyValueFactory <>("PharmID")); 
        column_doctorid.setCellValueFactory(new PropertyValueFactory <>("DoctorID"));
        column_scriptid.setCellValueFactory(new PropertyValueFactory <>("ScriptID")); 
        column_status.setCellValueFactory(new PropertyValueFactory <>("Status"));
        column_frequency.setCellValueFactory(new PropertyValueFactory <>("Frequency"));
        column_dosage.setCellValueFactory(new PropertyValueFactory <>("Dosage"));
        column_quantity.setCellValueFactory(new PropertyValueFactory <>("Quantity"));    
        table_prescriptions.setItems(prescriptionList);
    }
    
    /**
     * Function to refresh pharmacy table
     * 
     * @author
     */
    public void pharmaTable() 
    {
        try
        {
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PHARMA ");
            pharmaList.clear();
            while (rs.next()) 
            {
                pharmaList.add(new PharmaTable(rs.getInt("PharmID"),rs.getString("Name"),
                rs.getString("Street"),rs.getString("City"),rs.getString("State"),
                rs.getString("Zip"),rs.getString("Email"),rs.getString("Phone"),rs.getString("Fax")));

            }
        } 
        catch (Exception e) {}
 
        column_fpharmid.setCellValueFactory(new PropertyValueFactory <>("PharmID"));
        column_fname.setCellValueFactory(new PropertyValueFactory <>("Name"));
        column_fstreet.setCellValueFactory(new PropertyValueFactory <>("Street"));
        column_fcity.setCellValueFactory(new PropertyValueFactory <>("City"));
        column_fstate.setCellValueFactory(new PropertyValueFactory <>("State"));
        column_fzip.setCellValueFactory(new PropertyValueFactory <>("Zip"));
        column_femail.setCellValueFactory(new PropertyValueFactory <>("Email"));
        column_fphone.setCellValueFactory(new PropertyValueFactory <>("Phone"));
        column_ffax.setCellValueFactory(new PropertyValueFactory <>("Fax"));

        table_pharmaLocation.setItems(pharmaList);
    }
    
    /**
     * Function to search for pharmacy
     * 
     * @author
     */
     public void searchPharma() 
     {
        FilteredList<PharmaTable> filtereddata = new FilteredList<>(pharmaList, b -> true);
        textField_searchPharma.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filtereddata.setPredicate(pharma -> 
            {
                if (newValue == null || newValue.isEmpty()) { return true; }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(pharma.getPharmID()).contains(lowerCaseFilter)) { return true; }                
                else if (pharma.getName().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (pharma.getStreet().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (pharma.getCity().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (pharma.getState().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (pharma.getZip().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (pharma.getEmail().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (pharma.getPhone().toLowerCase().contains(lowerCaseFilter)) { return true; }                
                else if (pharma.getFax().toLowerCase().contains(lowerCaseFilter)) { return true; }      
                else return false;
            });
        });
        SortedList<PharmaTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_pharmaLocation.comparatorProperty());
        table_pharmaLocation.setItems(sortedData);
    }
     
     
     
////////////////////////////////////////////////////////////////////////////////
//// ▲ PRESCRIPTIONS ▲ ////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ TEST RESULTS ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////     
    
    
    
    /**
     * Switch to test results anchor pane
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_testResults() 
    {
        panel_testResults.setVisible(true);
        panel_dashboard.setVisible(false);
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_search.setVisible(false);
        panel_findPharma.setVisible(false);
         panel_doctors.setVisible(false);
        panel_pharmacy.setVisible(false);
        panel_lab.setVisible(false);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ TEST RESULTS ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ END OF CLASS ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
}//END OF PATIENT DASHBOARD CONTROLLER CLASS
