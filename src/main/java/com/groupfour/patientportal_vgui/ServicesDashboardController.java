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
 * @author Alexander Harmaty, Yasin Khan, Brian Noss, Christopher Scheer, Angela Todaro
 */
public class ServicesDashboardController implements Initializable 
{
    
////////////////////////////////////////////////////////////////////////////////
//// ▼ UNORGANIZED & OTHER ▼ //////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////    
    
    
    
    /**
     * Initializes the controller class.
     * 
     * @author
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        prescriptionTable();
        // TODO
    }
    
    //Declare anchor panes
    @FXML
    private AnchorPane panel_account, panel_appointment, panel_dashboard, 
            panel_prescriptions, panel_records, panel_search, panel_testResults;
    
    //Declare buttons to switch anchor panes
    @FXML
    private Button button_home, button_search, button_accountInfo, button_medicalRecords, 
            button_testResults, button_prescriptions, button_appointments;
    
    //Declare buttons for log-out and devMenu
    @FXML
    private Button button_logOut, button_devMenu;
    
    //Switch to log-in menu
    @FXML
    private void handleButton_logOut() throws IOException
    {
        App.setRoot("loginScreen");
    } 
    
    //Open developer menu options
    @FXML
    private void switchToDevMenu() throws IOException 
    {
        App.setRoot("devMenu");
    }
    
    //Initialize current user ID  
    private String currentUserID = App.currentUser.getUserID();
    
    
    
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
    @FXML
    void show_panelDashboard() 
    {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(true);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ HOME ▲ /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ SEARCH ▼ ///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////   
    
    
    
    /**
     * Switch to search anchor pane
     * 
     * @author
     */
    @FXML
    void handleButton_search() 
    {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(true);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ SEARCH ▲ ///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ ACCOUNT INFO ▼ /////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////// 
    
    
    
    /**
     * Switch to account information anchor pane
     * 
     * @author Angela Todaro
     */
    @FXML
    void handleButton_account() 
    {
//        try
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
    
    //Declare textfields for account information
    @FXML
    private TextField textField_scity, textField_semail, textField_serviceid, 
             textField_sfax, textField_sname, textField_sphone, 
             textField_sstate, textField_sstreet, textField_szip;
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ ACCOUNT INFO ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ MEDICAL RECORDS ▼ //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////    
    
    
    
    /**
     * Switch to medical records anchor pane
     */
    @FXML
    void handleButton_record() 
    {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(true);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ MEDICAL RECORDS ▲ //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ TEST RESULTS ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Switch to test results anchor pane
     * 
     * @author
     */
    @FXML
    void handleButton_testResults() 
    {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(true);
        panel_dashboard.setVisible(false);
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ TEST RESULTS ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ PRESCRIPTIONS ▼ ////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////    
    
    
    
    /**
     * Switch to prescriptions anchor pane
     * 
     * @author
     */
    @FXML
    void handleButton_prescriptions() 
    {
        panel_account.setVisible(false);
        panel_appointment.setVisible(false);
        panel_prescriptions.setVisible(true);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);
    }
    
    //Declare table view for prescriptions table
    @FXML
    private TableView<PrescriptionTable> table_prescriptions;
    
    //Declare columns for prescriptions table
    @FXML
    private TableColumn<PrescriptionTable, String> column_date, 
            column_description, column_doctorid, column_dosage, 
            column_frequency, column_medication, column_patientid, 
            column_pharmid, column_quantity, column_status, column_scriptid;
    
    //Declare observable lsit for prescriptions table
    ObservableList<PrescriptionTable> prescriptionList = FXCollections.observableArrayList();
    
    /**
     * Function to refresh prescriptions table
     * 
     * @author
     */
    public void prescriptionTable() 
    {
        try{
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM SCRIPTDOC");
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
     * Button handler function to sent prescriptions to patient from pharmacy
     * 
     * @author
     */
    @FXML
    void handleButton_pharmSendPatient()
    {
        try 
        {
            Parent root = FXMLLoader.load(getClass().getResource("pharmSendPatient.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) 
        {
            Logger.getLogger(ServicesDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ PRESCRIPTIONS ▲ ////////////////////////////////////////////////////////
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
        panel_account.setVisible(false);
        panel_appointment.setVisible(true);
        panel_prescriptions.setVisible(false);
        panel_records.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_dashboard.setVisible(false);
        
        appointmentTable();
    }

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
    
    /**
     * Function to schedule a doctor appointment 
     * 
     * @author Alexander Harmaty
     */
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
    
    /**
     * Function to reschedule or cancel any appointment 
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
     * Function to refresh appointments table
     * 
     * @author Alexander Harmaty
     */
    public void appointmentTable()
    {
        //button_refreshApp
        //refresh tableview 
        
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        switch (currentUserType) 
        {
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

////////////////////////////////////////////////////////////////////////////////
//// ▲ APPOINTMENTS ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ END OF CLASS ▼ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    
}//END OF SERVICE DASHBOARD CONTROLLER CLASS
