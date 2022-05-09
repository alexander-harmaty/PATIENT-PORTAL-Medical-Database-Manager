/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author yasin
 */
public class DocInsertMRecordController implements Initializable {

    @FXML
    private TextField textField_bloodtype;

    @FXML
    private TextArea textField_diagnosis;

    @FXML
    private TextField textField_dob;

    @FXML
    private TextField textField_height;

    @FXML
    private TextField textField_patientID;

    @FXML
    private TextField textField_recorddate;

    @FXML
    private TextField textField_weight;
    
    Connection con = null;
    Statement stmt = null;

    @FXML
    void handleButton_recordClear(MouseEvent event) {
        
        this.textField_bloodtype.clear();
        this.textField_diagnosis.clear();
        this.textField_dob.clear();
        this.textField_height.clear();
        this.textField_patientID.clear();
        this.textField_recorddate.clear();
        this.textField_weight.clear();
            
    }

    @FXML
    void handleButton_recordInsert(ActionEvent event) {
        
        try {
        System.out.println("Insert Proccess has Started!");
        con = DatabaseConnection.connectDB();
        stmt = con.createStatement();
        
            int ID = Integer.parseInt(textField_patientID.getText());
            String DOB = textField_dob.getText();
            String date = textField_recorddate.getText();
            String H =  textField_height.getText();
            String W = textField_weight.getText();
            String bt = textField_bloodtype.getText();
            String diag = textField_diagnosis.getText();
            
        String addRecord = "INSERT INTO MEDICALRECORDS (PatientID, DOB, RecordDate, Height, Weight, BloodType, Diagnosis)" 
                    + " VALUES  (" + ID + ",'" +DOB+ "','" +date+ "','" +H+ "','" +W+ "','" +bt+ "','" +diag+ "')";
        
        System.out.println(addRecord);            
        stmt.execute(addRecord); 
        System.out.println("Insert Proccess has finished!");
        JOptionPane.showMessageDialog(null,"Medical record added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        } catch (Exception e) {System.out.println(e);}
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
