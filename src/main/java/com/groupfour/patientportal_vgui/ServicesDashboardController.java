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
        TestResultsTable();
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
     * @author yasin
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
     * @author Alexander Harmaty, Angela Todaro
     */
    @FXML
    void handleButton_account() 
    {
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        switch(currentUserType)
        {
            case "Office":
                
                try
                {
                    Connection con = DatabaseConnection.connectDB();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM OFFICE WHERE OfficeID="+currentUserID+";");

                    while (rs.next()) 
                    {   
                        textField_serviceid.setText(rs.getString("OfficeID"));
                        textField_sname.setText(rs.getString("OfficeName"));
                        textField_sType.setText(currentUserType);
                        
                        textField_sstreet.setText(rs.getString("Street"));
                        textField_scity.setText(rs.getString("City"));
                        textField_sstate.setText(rs.getString("State"));
                        textField_szip.setText(rs.getString("Zip"));
                        textField_sphone.setText(rs.getString("Phone"));
                        textField_sfax.setText(rs.getString("Fax"));
                        textField_semail.setText(rs.getString("Email"));
                    }
                }
                catch (Exception e){}
                
                break;
            case "Lab":
                
                try
                {
                    Connection con = DatabaseConnection.connectDB();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM LAB WHERE LabID="+currentUserID+";");

                    while (rs.next()) 
                    {   
                        textField_serviceid.setText(rs.getString("LabID"));
                        textField_sname.setText(rs.getString("Name"));
                        textField_sType.setText(currentUserType);
                        
                        textField_sstreet.setText(rs.getString("Street"));
                        textField_scity.setText(rs.getString("City"));
                        textField_sstate.setText(rs.getString("State"));
                        textField_szip.setText(rs.getString("Zip"));
                        textField_sphone.setText(rs.getString("Phone"));
                        textField_sfax.setText(rs.getString("Fax"));
                        textField_semail.setText(rs.getString("Email"));
                    }
                }
                catch (Exception e){}
                
                break;
            case "Pharmacy":
                
                try
                {
                    Connection con = DatabaseConnection.connectDB();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM PHARMA WHERE PharmID="+currentUserID+";");

                    while (rs.next()) 
                    {   
                        textField_serviceid.setText(rs.getString("PharmID"));
                        textField_sname.setText(rs.getString("Name"));
                        textField_sType.setText(currentUserType);
                        
                        textField_sstreet.setText(rs.getString("Street"));
                        textField_scity.setText(rs.getString("City"));
                        textField_sstate.setText(rs.getString("State"));
                        textField_szip.setText(rs.getString("Zip"));
                        textField_sphone.setText(rs.getString("Phone"));
                        textField_sfax.setText(rs.getString("Fax"));
                        textField_semail.setText(rs.getString("Email"));
                    }
                }
                catch (Exception e){}
                
                break;
            default:
                break;
        }
         
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
             textField_sstate, textField_sstreet, textField_szip, textField_sType;
    
    /**
     * Function to update service account information
     * 
     * @author Alexander Harmaty
     */
    @FXML
    void handleButton_UpdateSAccInfo()
    {
        String currentUserID = App.currentUser.getUserID();
        String currentUserType = App.currentUser.getType();
        
        switch(currentUserType)
        {
            case "Office":
                
                try
                {
                    System.out.println("Connection Success!");
                    Connection con = DatabaseConnection.connectDB();
                    Statement stmt = con.createStatement();
                    
                    //String id = textField_serviceid.getText();
                    String name = textField_sname.getText();
                    //String type = textField_sType.getText();

                    String street = textField_sstreet.getText();
                    String city = textField_scity.getText();
                    String state = textField_sstate.getText();
                    String zip = textField_szip.getText();
                    String phone = textField_sphone.getText();
                    String fax = textField_sfax.getText();
                    String email = textField_semail.getText();


                    String query = "UPDATE OFFICE " + 
                      "SET  OfficeName ='" + name + "', Phone = '" + phone + "', Email = '" + email + 
                            "', Street = '" + street +"', City = '"+ city +
                            "', Zip = '" + zip + "', State = '" + state + 
                            "', Fax = '" + fax + 
                            "', WHERE OfficeID = " + currentUserID + ";";

                    System.out.println(query);
                    stmt.executeQuery(query);
                } 
                catch (Exception e) {}

                JOptionPane.showMessageDialog(null,"Account information updated successfully!\n"
                        + "Account ID and Type remain unchanged.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                handleButton_account();
                
                break;
                
            case "Lab":
                
                try
                {
                    System.out.println("Connection Success!");
                    Connection con = DatabaseConnection.connectDB();
                    Statement stmt = con.createStatement();
                    
                    //String id = textField_serviceid.getText();
                    String name = textField_sname.getText();
                    //String type = textField_sType.getText();

                    String street = textField_sstreet.getText();
                    String city = textField_scity.getText();
                    String state = textField_sstate.getText();
                    String zip = textField_szip.getText();
                    String phone = textField_sphone.getText();
                    String fax = textField_sfax.getText();
                    String email = textField_semail.getText();


                    String query = "UPDATE LAB " + 
                      "SET  Name ='" + name + "', Phone = '" + phone + "', Email = '" + email + 
                            "', Street = '" + street +"', City = '"+ city +
                            "', Zip = '" + zip + "', State = '" + state + 
                            "', Fax = '" + fax + 
                            "', WHERE LabID = " + currentUserID + ";";

                    System.out.println(query);
                    stmt.executeQuery(query);
                } 
                catch (Exception e) {}

                JOptionPane.showMessageDialog(null,"Account information updated successfully!\n"
                        + "Account ID and Type remain unchanged.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                handleButton_account();
                
                break;
                
            case "Pharmacy":
                
                try
                {
                    System.out.println("Connection Success!");
                    Connection con = DatabaseConnection.connectDB();
                    Statement stmt = con.createStatement();
                    
                    //String id = textField_serviceid.getText();
                    String name = textField_sname.getText();
                    //String type = textField_sType.getText();

                    String street = textField_sstreet.getText();
                    String city = textField_scity.getText();
                    String state = textField_sstate.getText();
                    String zip = textField_szip.getText();
                    String phone = textField_sphone.getText();
                    String fax = textField_sfax.getText();
                    String email = textField_semail.getText();


                    String query = "UPDATE PHARMA " + 
                      "SET  Name ='" + name + "', Phone = '" + phone + "', Email = '" + email + 
                            "', Street = '" + street +"', City = '"+ city +
                            "', Zip = '" + zip + "', State = '" + state + 
                            "', Fax = '" + fax + 
                            "', WHERE PharmID = " + currentUserID + ";";

                    System.out.println(query);
                    stmt.executeQuery(query);
                } 
                catch (Exception e) {}

                JOptionPane.showMessageDialog(null,"Account information updated successfully!\n"
                        + "Account ID and Type remain unchanged.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                handleButton_account();
                
                break;
            default:
                JOptionPane.showMessageDialog(null,"Unable to get current user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
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
     * @author Yasin Khan
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
    
    //Declare table view for Results table
    @FXML
    private TableView<TRTable> table_testResults;
    
    //Declare columns for Results table
    @FXML
    private TableColumn<TRTable, String> column_trid, 
            column_title, column_result, column_trdate, 
            column_type, column_trpatientid, column_trlabid;
    
    //Declare observable lsit for Results table
    ObservableList<TRTable> resultList = FXCollections.observableArrayList();
    
    /**
     * Function to load and refresh Results table
     * 
     * @author
     */
    public void TestResultsTable() 
    {
        try{
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM TESTRESULTS");
            resultList.clear();
            while (rs.next()) 
            {
                resultList.add(new TRTable(rs.getInt("TRID"), rs.getString("Title"),
                rs.getString("Result"),rs.getString("Date"),rs.getString("Type"),
                rs.getInt("PatientID"),rs.getInt("LabID")));  
            }
        } 
        catch (Exception e) {}
 
        column_trid.setCellValueFactory(new PropertyValueFactory <>("TRID"));
        column_title.setCellValueFactory(new PropertyValueFactory <>("Title"));
        column_result.setCellValueFactory(new PropertyValueFactory <>("Result"));
        column_trdate.setCellValueFactory(new PropertyValueFactory <>("Date"));
        column_type.setCellValueFactory(new PropertyValueFactory <>("Type")); 
        column_trpatientid.setCellValueFactory(new PropertyValueFactory <>("PatientID"));
        column_trlabid.setCellValueFactory(new PropertyValueFactory <>("LabID")); 
   
        table_testResults.setItems(resultList); 
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
//// ▲ TEST RESULTS ▲ /////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//// ▼ PRESCRIPTIONS ▼ ////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////    
    
    
    
    /**
     * Switch to prescriptions anchor pane
     * 
     * @author yasin
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
     * @author yasin
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
