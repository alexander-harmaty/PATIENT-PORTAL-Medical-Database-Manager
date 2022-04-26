/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaskh
 */
public class PharmSendPatientController implements Initializable {
    
    // fields
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> statusbar;

    @FXML
    private TextArea textField_desc;

    @FXML
    private TextField textField_did;

    @FXML
    private TextField textField_dose;

    @FXML
    private TextField textField_freq;

    @FXML
    private TextField textField_med;

    @FXML
    private TextField textField_pharmid;

    @FXML
    private TextField textField_pid;

    @FXML
    private TextField textField_quant;

    @FXML
    private TextField textField_scriptid;
    
    // button actions / code
    
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    int sID;
    
    
        // button to autofill information 
    @FXML
    void handleButton_go() {
        try
        {
            sID = Integer.parseInt(textField_scriptid.getText());
          
            con = DatabaseConnection.connectDB();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM SCRIPTDOC WHERE ScriptID="+sID+";");

                while (rs.next()) 
                {   
                    textField_pid.setText(String.valueOf(rs.getInt("PatientID"))); 
                    textField_did.setText(String.valueOf(rs.getInt("DoctorID")));
                    textField_pharmid.setText(String.valueOf(rs.getInt("PharmID")));
                    textField_med.setText(String.valueOf(rs.getString("Medication")));
                    textField_desc.setText(String.valueOf(rs.getString("Description")));
                    textField_dose.setText(String.valueOf(rs.getString("Dosage")));
                    textField_freq.setText(String.valueOf(rs.getString("Frequency")));
                    textField_quant.setText(String.valueOf(rs.getString("Quantity"))); 
                }
        } catch (Exception e){}
    }

        // button to update information in table so patient can see in their dashboard
    @FXML
    void handleButton_notify() {
        try
        {
            con = DatabaseConnection.connectDB();
            Statement st2 = con.createStatement();
            
            int scriptid = Integer.parseInt(textField_scriptid.getText());
            String med = textField_med.getText();
            String desc= textField_desc.getText();
            String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));                 
            int pid = Integer.parseInt(textField_pid.getText());
            int pharmid = Integer.parseInt(textField_pharmid.getText());
            int did = Integer.parseInt(textField_did.getText());
            String status = statusbar.getSelectionModel().getSelectedItem();
            String freq = textField_freq.getText();
            String dose = textField_dose.getText();
            String quant = textField_quant.getText();
       
            String sendPatient = "UPDATE SCRIPTDOC "
                    + "SET Medication = '" +med+"', Description = '"+desc+"'"
                    + ", DATE = '"+date+"', PatientID = "+pid+",PharmID = "+pharmid+""
                    + ", DoctorID = "+did+", Status = '"+status+"', Frequency = '"+freq+"'"
                    + ", Dosage = '"+dose+"', Quantity = '"+ quant+"' WHERE ScriptID = " +scriptid+";";
        
        st2.execute(sendPatient);  
        JOptionPane.showMessageDialog(null,"Notification Sent");
           
        } catch (Exception e){}

    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusbar.getItems().addAll("Ready", "Cancelled");
    }    
    
}
