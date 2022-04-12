/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class DoctorInsertRecordController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TextField textField_recordID;
     
     @FXML
    private TextField textField_patientID;
    
     @FXML
    private TextField textField_dob;
     
     @FXML
    private TextField textField_recorddate;
     
     @FXML
    private TextField textField_height;
     
     @FXML
    private TextField textField_weight;
     
     @FXML
    private TextField textField_bloodtype;
     
     @FXML
    private TextField textField_diagnosis;
    
    
     
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps;
    MedicalTable table = null;
    int recordID;  
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void handleButton_recordGo() {     
        try
        {
            recordID = Integer.parseInt(textField_recordID.getText());
          
            Connection con = DatabaseConnection.connectDB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM MEDICALRECORDS WHERE RecordID="+recordID+";");

                while (rs.next()) 
                {   
                    textField_recordID.setText(String.valueOf(rs.getInt("RecordID")));     
                    textField_patientID.setText(String.valueOf(rs.getInt("PatientID")));
                    textField_dob.setText(rs.getString("DOB"));                   
                    textField_recorddate.setText(rs.getString("RecordDate"));
                    textField_height.setText(rs.getString("Height"));
                    textField_weight.setText(rs.getString("Weight"));                   
                    textField_bloodtype.setText(rs.getString("BloodType"));                    
                    textField_diagnosis.setText(rs.getString("Diagnosis"));                   
                    
                }
        } catch (Exception e){}
    }
    
    @FXML
    void handleButton_recordSave() {
         con = DatabaseConnection.connectDB();
         getRecordQuery();
         //JOptionPane.showMessageDialog(null," Record Saved");
    }
    
    @FXML
    void handleButton_recordClear() {
        
        this.textField_recordID.clear();    
        this.textField_patientID.clear();
        this.textField_dob.clear();                 
        this.textField_recorddate.clear();
        this.textField_height.clear();
        this.textField_weight.clear();                 
        this.textField_bloodtype.clear();                    
        this.textField_diagnosis.clear();
   
    }
    
    private void getRecordQuery() {
        
        try {
        System.out.println("Connection Success!");
        con = DatabaseConnection.connectDB();
        Statement stmt = con.createStatement();
        
            int ID = Integer.parseInt(textField_recordID.getText());
            int ID2 = Integer.parseInt(textField_patientID.getText());
            String dob = textField_dob.getText();
            String recorddate = textField_recorddate.getText();
            String height =  textField_height.getText();
            String weight = textField_weight.getText();
            String bloodtype = textField_bloodtype.getText();
            String diagnosis = textField_diagnosis.getText();
           
            
            String query2 = "UPDATE MEDICALRECORDS " + 
              "SET  PatientID = " + ID2 + 
                    ", DOB = '" + dob + "', RecordDate = '" + recorddate + 
                    "', Height = '" + height +"', Weight = '"+ weight +
                    "', BloodType = '" + bloodtype + "', Diagnosis = '" + diagnosis +    
                    "' WHERE RecordID = " + ID + ";";
            
            System.out.println(query2);  
           JOptionPane.showMessageDialog(null,"Medical Record Updated Successfully.");
            stmt.executeQuery(query2);            
            
            
    } catch (Exception e) {
        
    }
    }
}
