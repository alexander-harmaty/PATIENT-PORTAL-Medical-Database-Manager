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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
     * Author: Angie 
     * VARIABLES FOR PRESCRIPTION TABLE
     */
    @FXML
    private AnchorPane panel_prescriptions;
    
    @FXML
    private AnchorPane panel_dashboard;
    
    @FXML
    private TableView<PrescriptionTable> table_prescriptions;
    
     @FXML
    private TableColumn<PrescriptionTable,String> column_date;

    @FXML
    private TableColumn<PrescriptionTable,String> column_description;

    @FXML
    private TableColumn< PrescriptionTable,String> column_doctorid;

    @FXML
    private TableColumn<PrescriptionTable,String> column_dosage;

    @FXML
    private TableColumn<PrescriptionTable,String> column_frequency;

    @FXML
    private TableColumn<PrescriptionTable, String> column_medication;

    @FXML
    private TableColumn<PrescriptionTable, String> column_patientid;

    @FXML
    private TableColumn<PrescriptionTable, String> column_pharmid;

    @FXML
    private TableColumn<PrescriptionTable, String> column_quantity;

    @FXML
    private TableColumn<PrescriptionTable,String> column_status;
    
    ObservableList<PrescriptionTable> prescriptionList = FXCollections.observableArrayList();
    
    public void prescriptionTable() {
        try{
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM SCRIPTDOC");
            prescriptionList.clear();
                while (rs.next()) {
                    prescriptionList.add(new PrescriptionTable(rs.getInt("PatientID"),rs.getInt("PharmID"),
                    rs.getInt("DoctorID"),rs.getString("Medication"),rs.getString("Description"),
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
                column_status.setCellValueFactory(new PropertyValueFactory <>("Status"));
                column_frequency.setCellValueFactory(new PropertyValueFactory <>("Frequency"));
                column_dosage.setCellValueFactory(new PropertyValueFactory <>("Dosage"));
                column_quantity.setCellValueFactory(new PropertyValueFactory <>("Quantity"));
                table_prescriptions.setItems(prescriptionList);
        
    }
    
    
    @FXML
    void handleButton_prescriptions() {
    panel_prescriptions.setVisible(true);
    panel_dashboard.setVisible(false);
    }

    /** MY CODE ENDS HERE - ANGIE
     * 
     */
    
    
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
