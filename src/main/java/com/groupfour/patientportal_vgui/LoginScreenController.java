/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class LoginScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Buttons
    
     @FXML
    private Button button_login;

    @FXML
    private Button button_register;
    
    @FXML
    private Button button_devMenu;
    
    //Textfields
    
    @FXML
    private TextField textField_degree;

    @FXML
    private TextField textField_demail;

    @FXML
    private TextField textField_dfname;

    @FXML
    private TextField textField_did;

    @FXML
    private TextField textField_dlname;

    @FXML
    private TextField textField_dphone;

    @FXML
    private TextField textField_paddress;

    @FXML
    private TextField textField_pcity;

    @FXML
    private TextField textField_pemail;

    @FXML
    private TextField textField_pfname;

    @FXML
    private TextField textField_pid;

    @FXML
    private TextField textField_pinsid;

    @FXML
    private TextField textField_pinsurance;

    @FXML
    private TextField textField_plname;

    @FXML
    private TextField textField_pphone;

    @FXML
    private TextField textField_pstate;

    @FXML
    private TextField textField_pzip;

    @FXML
    private TextField textField_spec;

    
    //dropdowns

    @FXML
    private ComboBox button_type;

    @FXML
    private ComboBox button_type2;
    
    //AnchorPanes

    @FXML
    private AnchorPane panel_login;

    @FXML
    private AnchorPane panel_register;
    
    @FXML
    private AnchorPane panel_registerPatient;
    
    @FXML
    private AnchorPane panel_registerDoctor;
    
    //Text Fields

    @FXML
    private TextField text_email;

    @FXML
    private PasswordField text_pass;

    @FXML
    private TextField text_pass2;

    @FXML
    private TextField text_user;

    @FXML
    private TextField text_user2;
    
    Connection con = null;
    Connection con2 = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    Statement stmt = null;
    long id = 0;
    
    public void panelLoginShow() {
        panel_login.setVisible(true);
        panel_register.setVisible(false);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
    }

    public void panelRegisterShow() {
        panel_login.setVisible(false);
        panel_register.setVisible(true);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
    }
    
    @FXML
    private void Login (ActionEvent event) throws Exception {
        String userName = text_user.getText();
        String passWord = text_pass.getText();
        try {
            
            con = DatabaseConnection.connectDB();
            Statement st = (Statement) con.createStatement();
            rs = st.executeQuery( "Select * FROM LOGIN WHERE Username = '" + userName + "' AND Password = '" + passWord + "';");
            //this loop will read the type and bring to a page specific to the type of user
            while (rs.next()) {
               // JOptionPane.showMessageDialog(null, "Your login was successful.");
               if (rs.getString(2).equals(userName) && rs.getString(3).equals(passWord)) 
                   App.currentUser = new CurrentUser(rs.getString(1));
                break;

            }
        if (rs.getString(5).toUpperCase().equals("PATIENT")){           
                    JOptionPane.showMessageDialog(null, "Your login was successful.");
                    button_login.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("patientDashboard.fxml")); 
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show(); 
                    
                    //PatientDashboardController.this.setLabelUserFirstLast();
            }
        else if (rs.getString(5).toUpperCase().equals("DOCTOR")) {
                    JOptionPane.showMessageDialog(null, "Your login was successful.");
                    button_login.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("doctorDashboard.fxml")); 
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show(); 
        }
        else 
            JOptionPane.showMessageDialog(null, "Incorrect username or password.");
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        }
    }
    
    
    
    public void addUser(ActionEvent event){
        con = DatabaseConnection.connectDB();
        String sql = "INSERT INTO LOGIN (Username, Password, Email, Type) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, text_user2.getText());
            ps.setString(2, text_pass2.getText());
            ps.setString(3, text_email.getText());
            ps.setString(4, button_type2.getValue().toString());
            System.out.println (sql);
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1); 
                    }
                } catch (Exception e){} 
            }
            
            System.out.println (id); 
           
            //JOptionPane.showMessageDialog(null, "Registered successfully");
        } catch (Exception e) {}
        
         if (button_type2.getValue().equals("Patient")){
                textField_pid.setText(String.valueOf(id));
                panel_login.setVisible(false);
                panel_register.setVisible(false);
                panel_registerPatient.setVisible(true);
                panel_registerDoctor.setVisible(false);                          
             }
             
             else if (button_type2.getValue().equals("Doctor")){
                textField_did.setText(String.valueOf(id));
                panel_login.setVisible(false);
                panel_register.setVisible(false);
                panel_registerPatient.setVisible(false);
                panel_registerDoctor.setVisible(true);
             }
        
    
    }
    /**
     * WORKS!!
     * @author Yasin
     * @param event 
     */
      @FXML
    public void registerPatient(ActionEvent event) {
        int ID = (int) id; 
        try {
        System.out.println("ID being used is: " + ID);
        con2 = DatabaseConnection.connectDB();
        stmt = con2.createStatement();
        
            int pID = ID;
            String fname = textField_pfname.getText();
            String lname = textField_plname.getText();
            String pnum =  textField_pphone.getText();
            String email = textField_pemail.getText();
            String street = textField_paddress.getText();
            String city = textField_pcity.getText();
            String zip = textField_pzip.getText();
            String state =  textField_pstate.getText();
            int insid =  Integer.parseInt(textField_pinsid.getText());
            String insur =  textField_pinsurance.getText();
            
        String patquery = "INSERT INTO PATIENT (PatientID, PFirstName, PLastName, PPhone, PEmail, Street, City, Zip, State, InsuranceID, Insurance)" 
                    + " VALUES  (" + pID + ",'" +fname+ "','" +lname+ "','" +pnum+ "','" +email+ "','" +street+ "','" +city+ "','" +zip+ "','" +state+ "'," +insid+ ",'" +insur+ "')";
        
        System.out.println(patquery);            
        stmt.executeQuery(patquery); 
            
        } catch (Exception e) {}
        
        panel_login.setVisible(true);
        panel_register.setVisible(false);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
        JOptionPane.showMessageDialog(null,"Registration Complete");
       
    }

    @FXML
    public void registerdoctor(ActionEvent event) {
        int ID = (int) id; 
        try {
        System.out.println("ID being used is: " + ID);
        con2 = DatabaseConnection.connectDB();
        stmt = con2.createStatement();
        
            int dID = ID;
            String fname = textField_dfname.getText();
            String lname = textField_dlname.getText();
            String dnum =  textField_dphone.getText();
            String email = textField_demail.getText();
            String degree = textField_degree.getText();
            String spec = textField_spec.getText();
            
        String docquery = "INSERT INTO DOCTOR (DoctorID, DFirstName, DLastName, DPhone, DEmail, Degree, Specialty)" 
                    + " VALUES  (" + dID + ",'" +fname+ "','" +lname+ "','" +dnum+ "','" +email+ "','" +degree+ "','" +spec+ "')";
        
        System.out.println(docquery);            
        stmt.executeQuery(docquery); 
            
        } catch (Exception e) {}
        
        panel_login.setVisible(true);
        panel_register.setVisible(false);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
        JOptionPane.showMessageDialog(null,"Registration Complete");

    }

    
    @FXML
    private void switchToDevMenu() throws IOException 
    {
        App.setRoot("devMenu");
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //button_type.getItems().addAll("Patient", "Doctor", "Nurse", "Lab");
        button_type2.getItems().addAll("Patient", "Doctor", "Nurse", "Lab");
        // TODO
    }    
    
}



