/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class DoctorInsertPatientController implements Initializable {
    
    
    @FXML
    private TextField textField_firstName;

    @FXML
    private TextField textField_insuranceCo;

    @FXML
    private TextField textField_insuranceID;

    @FXML
    private TextField textField_lastName;

    @FXML
    private TextField textField_patientID;
    
    @FXML
    private TextField textField_email;

    @FXML
    private TextField textField_phone;
    
    @FXML
    private TextField textField_street;
    
    @FXML
    private TextField textField_city;
    
    @FXML
    private TextField textField_zip;
    
    @FXML
    private TextField textField_state;
    
    @FXML
    private TextField textField_primarydoc;
    
    
     
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps;
    PatientTable table = null;
    int patientID;  
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void handleButton_go() {     
        try
        {
            patientID = Integer.parseInt(textField_patientID.getText());
          
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
        } catch (Exception e){}
    }
    
    @FXML
    void handleButton_save() {
         con = DatabaseConnection.connectDB();
         getQuery();
          JOptionPane.showMessageDialog(null,"Patient information updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
    }
    
    @FXML
    void handleButton_clear() {
        this.textField_patientID.clear();
        this.textField_firstName.clear();
        this.textField_lastName.clear();
        this.textField_phone.clear();
        this.textField_email.clear();
        this.textField_street.clear();
        this.textField_city.clear();
        this.textField_zip.clear();
        this.textField_state.clear();
        this.textField_insuranceID.clear();
        this.textField_insuranceCo.clear();
        this.textField_primarydoc.clear();
        
    }
    
    private void getQuery() {
        
        try {
        System.out.println("Connection Success!");
        con = DatabaseConnection.connectDB();
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
              
            
    } catch (Exception e) {
        
    }
    }


    
}


 /**String 
       con = DatabaseConnection.connectDB();
       String firstname = textField_firstName.getText();
       String lastname = textField_lastName.getText();
       String phone = textField_phone.getText();
       String email = textField_email.getText(); 
       String street = textField_street.getText();
       String city = textField_city.getText();
       String zip = textField_zip.getText();
       String state = textField_state.getText();
       //String insuranceid = String.valueOf(textField_insuranceID);
       String insuranceid = textField_insuranceID.getText();
       String insurance = textField_insuranceCo.getText();
       
       if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || email.isEmpty() || street.isEmpty()
               || city.isEmpty() || zip.isEmpty() || state.isEmpty() || insuranceid.isEmpty() || insurance.isEmpty()) {
           JOptionPane.showMessageDialog(null, "Please fill in all information.");
       } else {
           getQuery();
           //insert();
       }
  */


/**String query = "INSERT INTO PATIENT" + "(PatientID, PFirstName, PLastName, PPhone, PEmail, Street, City, Zip, State, InsuranceID, Insurance, PrimaryDoctor)" + 
              "values (?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);*/

 
           /** query = "INSERT INTO PATIENT" + "(PatientID, PFirstName, PLastName, PPhone, PEmail, Street, City, Zip, State, InsuranceID, Insurance, PrimaryDoctor)" + 
              "values (" + ID +", '" + fname + "', '" + lName + "',
              * '" + pNum + "', '" + email + "', '" + street +"', 
              * '"+ city +"', '" + zip + "', '" + state + "',
              * " + insID + ", '" + insur + "', " + prim +");";*/