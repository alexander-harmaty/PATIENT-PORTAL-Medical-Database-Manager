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
 * @author Alex
 */

public class PatientDashboardController implements Initializable 
{
    @FXML
    private TableView<DoctorTable> table_doctor;
    
    @FXML
    private TableColumn<DoctorTable, String> 
            column_demail, column_dfirstname, column_dlastname, column_doctorid, 
            column_dphone, column_degree, column_specialty;
    
    @FXML
    private Button button_accountInfo, button_appointments, button_devMenu,
            button_logOut, button_home, button_medicalRecords,
            button_prescriptions, button_search, button_testResults;
    
    @FXML
    private AnchorPane panel_accountInfo, panel_appointments, panel_dashboard,
            panel_medicalRecords, panel_prescriptions, panel_search, panel_testResults, panel_findPharma;
    
    @FXML
    private TextField textField_search, textField_medicalSearch, textField_email, textField_firstName, 
            textField_insuranceCo, textField_insuranceID, textField_lastName, 
            textField_patientID, textField_phone, textField_primarydoc, 
            textField_street, textField_city, textField_zip, textField_state, textField_searchPharma;
    
    @FXML
    private Label label_errorText, label_userFirstLast; 
    
    
    //Script Table
    @FXML
    private TableView<PrescriptionTable> table_prescriptions;
    
    @FXML
    private TableView<MedicalTable> table_medicalrecords;
    
    @FXML
    private TableColumn<MedicalTable, String> column_recordID, column_recorddate, 
            column_weight, column_bloodtype, column_diagnosis, 
            column_dob, column_height, column_patientID2;
      
    @FXML
    private TableColumn<PrescriptionTable, String> 
            column_date, column_description, column_doctorid2, column_dosage, 
            column_frequency, column_medication, column_patientid, column_pharmid, 
            column_quantity, column_status, column_scriptid;
    
    ObservableList<PrescriptionTable> prescriptionList = FXCollections.observableArrayList();
    
    //Appointment Table
    @FXML
    private TableView<Appointment> table_appointments;
    
    @FXML
    private TableColumn<Appointment, String>
            column_appID, column_appRreason, column_appDate, column_appTime, 
            column_appDocID, column_appPatID, column_appOfficeID, column_appLabID;
    
    //find pharma table:
    @FXML
    private TableView<PharmaTable> table_pharmaLocation;
    
     @FXML
    private TableColumn<PharmaTable, String> 
             column_femail,column_ffax,column_fname
             ,column_fpharmid, column_fphone,column_fstate
             ,column_fstreet,column_fzip, column_fcity;

     // Emergency Contact table
      @FXML
    private TableView<ECTable> table_EC;
      
     @FXML
    private TableColumn<ECTable, String> column_ecfirst, column_eclast
             , column_ecphone, column_ecrelation;
    
    @FXML
    private MFXButton button_scheduleDocApp, button_scheduleLabApp, 
            button_rescheduleOrCancelApp, button_refreshApp, button_backToScript;
    
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
  
    //currentUserID   
    private String patientID = App.currentUser.getUserID();
    
    public  void setLabelUserFirstLast()
    {
        label_userFirstLast.setText("test");
    }
    
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
    }

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
        
       
    }
    
    @FXML
    void handleButton_UpdatePAccInfo() {
        
        try {
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
        
    }

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
    }

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
    }
    
    @FXML
    void handleButton_findPharma() {
        
        panel_findPharma.setVisible(true);
        
        panel_prescriptions.setVisible(false); 
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);

    }

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
    }

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
    }
    
    ObservableList<DoctorTable> doctorslist = FXCollections.observableArrayList();
    
    public void refreshTable() {
        try{
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM DOCTOR");
            doctorslist.clear();
                while (rs.next()) {
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

    public void prescriptionTable() {
        try{
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM SCRIPTDOC WHERE PatientID = " + patientID);
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
    
      ObservableList<Appointment> appointmentslist = FXCollections.observableArrayList();
    
    
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
    
   ObservableList<MedicalTable> medicalList = FXCollections.observableArrayList();
    public void medicalTable() {
        try{
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MEDICALRECORDS WHERE PatientID = " + patientID);
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
    
    ObservableList<PharmaTable> pharmaList = FXCollections.observableArrayList();
    public void pharmaTable() {
        try{
     
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PHARMA ");
            pharmaList.clear();
                while (rs.next()) {
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
    
     @FXML
     void handleButton_backToScript() {
         
        panel_prescriptions.setVisible(true);
        
        panel_accountInfo.setVisible(false);
        panel_appointments.setVisible(false);
        panel_dashboard.setVisible(false);
        panel_medicalRecords.setVisible(false);
        panel_search.setVisible(false);
        panel_testResults.setVisible(false);
        panel_findPharma.setVisible(false);
        
     }
     
     ObservableList<ECTable> ECList = FXCollections.observableArrayList();
     public void ECTable() {
        try{
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM EC WHERE PatientID = " + patientID);
            ECList.clear();
                while (rs.next()) {
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
     
     public void RefreshECTable() {
        try{
            Connection con = DatabaseConnection.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM EC WHERE PatientID = " + patientID);
            ECList.clear();
                while (rs.next()) {
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
     
     @FXML
    void handleButton_addEC()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ecadd.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    @FXML
    void handleButton_refreshEC(){RefreshECTable();}
    
    
      @FXML
    void handleButton_printMedical() {
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
    
    public void searchMedical() {
         FilteredList<MedicalTable> filtereddata = new FilteredList<>(medicalList, b -> true);
        textField_medicalSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddata.setPredicate(medical -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(medical.getRecordID()).contains(lowerCaseFilter)) {
                    return true;
                }
                else if (String.valueOf(medical.getPatientID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (medical.getDOB().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (medical.getRecordDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (medical.getHeight().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (medical.getWeight().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (medical.getBloodType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (medical.getDiagnosis().toLowerCase().contains(lowerCaseFilter)) {
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
    
    public void searchPharma() {
         FilteredList<PharmaTable> filtereddata = new FilteredList<>(pharmaList, b -> true);
        textField_searchPharma.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddata.setPredicate(pharma -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (String.valueOf(pharma.getPharmID()).contains(lowerCaseFilter)) {
                    return true;
                }
                else if (pharma.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (pharma.getStreet().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (pharma.getCity().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (pharma.getState().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (pharma.getZip().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (pharma.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (pharma.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
                else if (pharma.getFax().toLowerCase().contains(lowerCaseFilter)) {
                    return true;  
                }
      
                else 
                    return false;
            });
        });
        SortedList<PharmaTable> sortedData = new SortedList<>(filtereddata);
        sortedData.comparatorProperty().bind(table_pharmaLocation.comparatorProperty());
        table_pharmaLocation.setItems(sortedData);
    }
    
    @FXML
    void switchToDevMenu() throws IOException 
    {
        App.setRoot("devMenu");
    }
    
    @FXML
    private void handleButton_logOut() throws IOException
    {
        App.setRoot("loginScreen");
    }
    /**
     * Initializes the controller class.
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
        // TODO
        
        
    }      
    
}
