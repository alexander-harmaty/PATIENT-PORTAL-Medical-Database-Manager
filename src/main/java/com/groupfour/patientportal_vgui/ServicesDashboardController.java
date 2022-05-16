/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javax.swing.JOptionPane;

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
    @FXML
    private Button button_logOut;
    @FXML
    private Button button_home;
    
    // TextFields
    /**Angie
     * 
     */
     @FXML
    private TextField textField_scity;

    @FXML
    private TextField textField_semail;

    @FXML
    private TextField textField_serviceid;

    @FXML
    private TextField textField_sfax;

    @FXML
    private TextField textField_sname;

    @FXML
    private TextField textField_sphone;

    @FXML
    private TextField textField_sstate;

    @FXML
    private TextField textField_sstreet;

    @FXML
    private TextField textField_szip;
  
    
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
    void show_panelDashboard() {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(true);
    }
    
    @FXML
    void handleButton_account() {
//         try
//        {
//            Connection con = DatabaseConnection.connectDB();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM DOCTOR WHERE DoctorID="+App.currentUser.getUserID()+";");
//
//            while (rs.next()) 
//            {   
//                textField_serviceid.setText(String.valueOf(rs.getInt("DoctorID")));
//                textField_firstName.setText(rs.getString("DFirstName"));
//                textField_lastName.setText(rs.getString("DLastName"));
//                textField_phone.setText(rs.getString("DPhone"));
//                textField_email.setText(rs.getString("DEmail"));
//                textField_degree.setText((rs.getString("Degree")));
//                textField_specialty.setText(rs.getString("Specialty"));              
//            }
//        }
//        catch (Exception e){}
         
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
        
        appointmentTable();
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
            Parent root = FXMLLoader.load(getClass().getResource("pharmSendPatient.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ServicesDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    //currentUserID   
    private String currentUserID = App.currentUser.getUserID();
    
    @FXML
    void handleButton_scheduleLabApp()
    {
        //button_scheduleLabApp
        //open labAppointmentInsert.fxml
        try {
            Parent root = FXMLLoader.load(getClass().getResource("labAppointmentInsert.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PatientDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleButton_scheduleDocApp()
    {
        //button_scheduleDocApp
        //open docAppointmentInsert.fxml
        try {
            Parent root = FXMLLoader.load(getClass().getResource("docAppointmentInsert.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PatientDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    ObservableList<Appointment> appointmentslist = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<Appointment, String>
            column_appID, column_appRreason, column_appDate, column_appTime, 
            column_appDocID, column_appPatID, column_appOfficeID, column_appLabID;
    
    @FXML
    private TableView<Appointment> table_appointments;
    
    public void appointmentTable()
    {
        //button_refreshApp
        //refresh tableview 
        
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        switch (currentUserType) {
            case "Lab":
                try
                {

                    Connection con = DatabaseConnection.connectDB();
                    ResultSet rs = con.createStatement().executeQuery("SELECT * FROM APPOINTMENT WHERE LabID = " + currentUserID);
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
                
                break;
            case "Office":
                try
                {

                    Connection con = DatabaseConnection.connectDB();
                    ResultSet rs = con.createStatement().executeQuery("SELECT * FROM APPOINTMENT WHERE OfficeID = " + currentUserID);
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
                
                break;
            case "Service":
                try
                {

                    Connection con = DatabaseConnection.connectDB();
                    ResultSet rs = con.createStatement().executeQuery("SELECT * FROM APPOINTMENT WHERE LabID = " + currentUserID);
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
                
                break;
            default:
                JOptionPane.showMessageDialog(null,"Unable to get current user data.", "Error", JOptionPane.ERROR_MESSAGE);
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
    
        @FXML
    private void handleButton_logOut() throws IOException
    {
        App.setRoot("loginScreen");
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prescriptionTable();
        // TODO
    }    
    
}
