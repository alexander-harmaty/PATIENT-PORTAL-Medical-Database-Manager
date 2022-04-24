/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ServicesDashboardController implements Initializable {

    // Buttons
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
    
    // TextFields
  
    
    //Anchor Panes
    @FXML
    private AnchorPane panel_account;

    @FXML
    private AnchorPane panel_appointment;

    @FXML
    private AnchorPane panel_dashboard;

    @FXML
    private AnchorPane panel_prescriptions;

    @FXML
    private AnchorPane panel_records;

    @FXML
    private AnchorPane panel_search;

    @FXML
    private AnchorPane panel_testResults;

    //Table variables
    
    @FXML
    private TableView<PrescriptionTable> table_prescriptions;
    
    @FXML
    private TableColumn<PrescriptionTable, String> column_date;

    @FXML
    private TableColumn<PrescriptionTable, String> column_description;

    @FXML
    private TableColumn<PrescriptionTable, String> column_doctorid;

    @FXML
    private TableColumn<PrescriptionTable, String> column_dosage;

    @FXML
    private TableColumn<PrescriptionTable, String> column_frequency;

    @FXML
    private TableColumn<PrescriptionTable, String> column_medication;

    @FXML
    private TableColumn<PrescriptionTable, String> column_patientid;

    @FXML
    private TableColumn<PrescriptionTable, String> column_pharmid;

    @FXML
    private TableColumn<PrescriptionTable, String> column_quantity;

    @FXML
    private TableColumn<PrescriptionTable, String> column_status;
    
    @FXML
    private TableColumn<PrescriptionTable, String> column_scriptid;
    
    ObservableList<PrescriptionTable> prescriptionList = FXCollections.observableArrayList();
    
    public void prescriptionTable() {
        try{
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM SCRIPTDOC");
            prescriptionList.clear();
                while (rs.next()) {
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
    
    
    @FXML
    void handleButton_account() {
        panel_account.setVisible(true);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);

    }

    @FXML
    void handleButton_appointments() {
        panel_account.setVisible(false);
        panel_appointment.setVisible(true);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);

    }

    @FXML
    void handleButton_prescriptions() {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(true);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);

    }
    
    

    @FXML
    void handleButton_record() {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(true);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);

    }

    @FXML
    void handleButton_search() {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(true);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);

    }

    @FXML
    void handleButton_testResults() {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(true);
        panel_dashboard.setVisible(false);

    }

    @FXML
    void handleButton_pharmSendPatient(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(""));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        prescriptionTable();
        // TODO
    }    
    
}
