/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.awt.event.MouseEvent;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private TextField textField_degree;

    @FXML
    private TextField textField_specialty;
    
     @FXML
    private TextField textField_insuranceCo;

    @FXML
    private TextField textField_insuranceID;

    @FXML
    private TextField textField_lastName;

    @FXML
    private TextField textField_doctorID;
    @FXML
    private TextField textField_doctorID2;
    

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
    
    @FXML
    private TextField textField_medicalSearch;
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
    
    @FXML
    private TableView<MedicalTable> table_medicalrecords;
    
    @FXML
    private TableColumn<MedicalTable, String> column_recordID;

    @FXML
    private TableColumn<MedicalTable, String> column_recorddate;

    @FXML
    private TableColumn<MedicalTable, String> column_weight;
    
    @FXML
    private TableColumn<MedicalTable, String> column_bloodtype;

    @FXML
    private TableColumn<MedicalTable, String> column_diagnosis;

    @FXML
    private TableColumn<MedicalTable, String> column_dob;

    @FXML
    private TableColumn<MedicalTable, String> column_height;
    
    @FXML
    private TableColumn<MedicalTable, String> column_patientID2;
    
    /**
     * Angie 
     */
    
     @FXML
     private ComboBox combobox_status;
     
    @FXML
    private TextField textField_frequency;
     

    @FXML
    private DatePicker datepicker_date;
    
    @FXML
    private Spinner<Integer> spinner_quantity;
    
     @FXML
    private TextArea textField_description;
     
    @FXML
    private TextField textField_dosage;
    
    @FXML
    private TextField textField_medication;

    @FXML
    private TextField textField_scriptID;
    
    @FXML
    private TextField textField_pharmID;
    
    @FXML
    private TextField textField_patientID;
    
    

    
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
    void handleButton_medicalRefresh() { 
        medicalTable();    
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
    void handleButton_medicalDelete() {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("docDeleteRecord.fxml"));
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
    void handleButton_medicalUpdate() {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("doctorInsertRecord.fxml"));
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
           
    }
    
    @FXML
    void handleButton_UpdateDAccInfo() {
        
        try {
        System.out.println("Connection Success!");
        con = DatabaseConnection.connectDB();
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
            JOptionPane.showMessageDialog(null,"Saved");
            
    } catch (Exception e) {}
        
        JOptionPane.showMessageDialog(null,"Saved");

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
    ObservableList<MedicalTable> medicalList = FXCollections.observableArrayList();
  
     public void medicalTable() {
        try{
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MEDICALRECORDS");
            medicalList.clear();
                while (rs.next()) {
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
    
    public void patientSearch() {
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
    
    public void medicalSearch() {
        FilteredList<MedicalTable> filtereddata = new FilteredList<>(medicalList, b -> true);
        textField_medicalSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddata.setPredicate(patients -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(patients.getRecordID()).contains(lowerCaseFilter)) {
                    return true;
                }
                else if (String.valueOf(patients.getPatientID()).contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getDOB().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getRecordDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getHeight().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getWeight().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getBloodType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (patients.getDiagnosis().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
               
                else 
                    return false;
            });
        });
        SortedList<MedicalTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_medicalrecords.comparatorProperty());
        table_medicalrecords.setItems(sortedData);
    }
    
    @FXML
    public void handleButton_print() {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("docPrintRecord.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void spinnerNumber() {
        SpinnerValueFactory<Integer> value = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20);
        value.setValue(1);
        spinner_quantity.setValueFactory(value);
        
    }
    
    public void comboboxStatus() {
//         combobox_status.getItems().removeAll(combobox_status.getItems());
//    combobox_status.getItems().addAll("Option A", "Option B", "Option C");
//    combobox_status.getSelectionModel().select("Option B");
        
        ObservableList<String> list = FXCollections.observableArrayList("Active", "Cancelled", "Deleted", "Expired", "Refill");
        combobox_status.setItems(list);   
    }
    
   
    @FXML
    void handleButton_order() { 
        try {
        con = DatabaseConnection.connectDB();
        Statement stmt = con.createStatement();
           // int scriptID = Integer.parseInt(textField_scriptID.getText());
            String medication = textField_medication.getText();
            String description = textField_description.getText();
           // String date = datepicker_date.getConverter().toString();
           String date = datepicker_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));         
          
                   
           int patientID = Integer.parseInt(textField_patientID.getText());
            int pharmID = Integer.parseInt(textField_pharmID.getText());
           int doctorID = Integer.parseInt(textField_doctorID2.getText());
           String status = combobox_status.getSelectionModel().getSelectedItem().toString();
           String frequency = textField_frequency.getText();
           String dosage = textField_dosage.getText();
           int quantity = spinner_quantity.getValue();
       
            String addPrescription = "INSERT INTO SCRIPTDOC (Medication, Description, DATE, PatientID, PharmID, DoctorID, Status, Frequency, Dosage, Quantity)" 
                    + " VALUES  ("  +medication+ ",'" +description+ "','" +date+ "','" + patientID+ "','" +pharmID+ "','" + doctorID + "','" + status + "','" + frequency + "','" + dosage + "','" + quantity + "')";
        
       // System.out.println(addRecord);          
       //NOTE:   EXECUTE statement for insert, update, delete; executeQuery for data retrieval like select
       

        stmt.execute(addPrescription);
        JOptionPane.showMessageDialog(null,"New Record Added!");
            
    } catch (Exception e) {
        System.out.print(e);
    }  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinnerNumber();
        comboboxStatus();
        refreshTable(); 
        medicalTable();
        patientSearch();
        medicalSearch();
        
    }    
    
}
