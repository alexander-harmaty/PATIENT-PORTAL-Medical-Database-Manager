/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
            panel_medicalRecords, panel_prescriptions, panel_search, panel_testResults;
    
    @FXML
    private TextField textField_search, textField_email, textField_firstName, 
            textField_insuranceCo, textField_insuranceID, textField_lastName, 
            textField_patientID, textField_phone, textField_primarydoc, 
            textField_street, textField_city, textField_zip, textField_state;
    
    @FXML
    private Label label_errorText;
    
    @FXML
    protected Label label_userFirstLast; 
    
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
        
        JOptionPane.showMessageDialog(null,"Saved");
        
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
