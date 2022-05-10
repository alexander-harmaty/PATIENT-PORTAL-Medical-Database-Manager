/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import io.github.palexdev.materialfx.dialogs.AbstractMFXDialog;
import io.github.palexdev.materialfx.dialogs.MFXDialogs;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialogBuilder;
import io.github.palexdev.materialfx.enums.ScrimPriority;
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
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
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
    
    //AnchorPanes
    @FXML
    private AnchorPane panel_login, panel_register, 
            panel_registerPatient, panel_registerDoctor, panel_registerService;
    
    //Dev Menu Button: DELETE WHEN REMOVING DEV MENU
    @FXML
    private MFXButton button_devMenu;
    
    //Login Screen TextFields
    @FXML
    private MFXTextField text_user;
    @FXML
    private MFXPasswordField text_pass;
    
    //Login & Registration Buttons
    @FXML
    private MFXButton button_login, button_register;
    
    //AddUser Registration TextFeilds
    @FXML
    private MFXTextField text_user2, text_pass2, text_email;
    
    //Dropdowns
    @FXML
    private MFXLegacyComboBox button_type, button_type2;
    
    //Patient Registration Textfields
    @FXML
    private MFXTextField 
            textField_pfname, textField_plname,
            textField_pid, textField_pemail, textField_pphone,
            textField_paddress,textField_pcity, textField_pstate, textField_pzip,
            textField_pinsid, textField_pinsurance;
    
    //Service Registration TextFields
    @FXML
    private MFXTextField 
            textField_sname, textField_fax,
            textField_sid, textField_semail, textField_sphone,
            textField_saddress,textField_scity, textField_sstate, textField_szip;

    //Doctor Registration Textfields
    @FXML
    private MFXTextField 
            textField_dfname, textField_dlname,
            textField_did, textField_demail, textField_dphone,
            textField_degree, textField_spec;
    
    //Database connections 
    Connection con = null;
    Connection con3 = null;
    Connection con2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    Statement stmt = null;
    long id = 0;   
    
    
    @FXML
    private void Login (ActionEvent event) throws Exception {
        String userName = text_user.getText();
        String passWord = text_pass.getText();
        try 
        {
            con = DatabaseConnection.connectDB();
            Statement st = (Statement) con.createStatement();
            rs = st.executeQuery( "Select * FROM LOGIN WHERE Username = '" + userName + "' AND Password = '" + passWord + "';");
            
            con2 = DatabaseConnection.connectDB();
            Statement st2 = (Statement) con2.createStatement();
            rs2 = st2.executeQuery( "Select * FROM LOGIN WHERE Email = '" + userName + "' AND Password = '" + passWord + "';");
            
            if (rs.next() && rs.getString(2).equals(userName) && rs.getString(3).equals(passWord)) 
            {
                App.currentUser = new CurrentUser(rs.getString(1));
                
                if (rs.getString(5).toUpperCase().equals("PATIENT"))
                {           
                        JOptionPane.showMessageDialog(null, "Your login was successful.");
                        button_login.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("patientDashboard.fxml")); 
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show();
                }
                else if (rs.getString(5).toUpperCase().equals("DOCTOR")) 
                {
                        JOptionPane.showMessageDialog(null, "Your login was successful.");
                        button_login.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("doctorDashboard.fxml")); 
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show(); 
                }
                else if (rs.getString(5).toUpperCase().equals("SERVICE")) 
                {
                       JOptionPane.showMessageDialog(null, "Your login was successful.");
                        button_login.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("servicesDashboard.fxml")); 
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show(); 
                }
            }
            else if (rs2.next() && rs2.getString(4).equals(userName) && rs2.getString(3).equals(passWord)) 
            {
                App.currentUser = new CurrentUser(rs2.getString(1));
                
                if (rs2.getString(5).toUpperCase().equals("PATIENT"))
                {           
                        JOptionPane.showMessageDialog(null, "Your login was successful.");
                        button_login.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("patientDashboard.fxml")); 
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show();
                }
                else if (rs2.getString(5).toUpperCase().equals("DOCTOR")) 
                {
                        JOptionPane.showMessageDialog(null, "Your login was successful.");
                        button_login.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("doctorDashboard.fxml")); 
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show(); 
                }
                else if (rs2.getString(5).toUpperCase().equals("SERVICE")) 
                {
                        JOptionPane.showMessageDialog(null, "Your login was successful.");
                        button_login.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("servicesDashboard.fxml")); 
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show(); 
                }
            }
            else
            {
               
             JOptionPane.showMessageDialog( null, "Incorrect username or password." , "Error", 
             JOptionPane.ERROR_MESSAGE );
         
         //JOptionPane.showMessageDialog(f,"Successfully Updated.","Alert",JOptionPane.WARNING_MESSAGE);  
               
               
               
                //JOptionPane.showMessageDialog(null, "Incorrect username or password.");
            }
        } catch (Exception e) {
          
            JOptionPane.showMessageDialog( null, "Please fill in all fields" , "Error", 
             JOptionPane.ERROR_MESSAGE );
            //JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        }
    }
     
                
    @FXML
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
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,"Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);}
        
        if (button_type2.getValue().equals("Patient")){
            
            textField_pid.setText(String.valueOf(id));
            textField_pemail.setText(text_email.getText());
            
            panel_login.setVisible(false);
            panel_register.setVisible(false);
            panel_registerPatient.setVisible(true);
            panel_registerDoctor.setVisible(false);
            panel_registerService.setVisible(false);
        }   
        else if (button_type2.getValue().equals("Doctor")){
            
           textField_did.setText(String.valueOf(id));
           textField_demail.setText(text_email.getText());
           
           panel_login.setVisible(false);
           panel_register.setVisible(false);
           panel_registerPatient.setVisible(false);
           panel_registerDoctor.setVisible(true);
           panel_registerService.setVisible(false);
        }
         else if (button_type2.getValue().equals("Service")){
            
           textField_sid.setText(String.valueOf(id));
           textField_semail.setText(text_email.getText());
           
           panel_login.setVisible(false);
           panel_register.setVisible(false);
           panel_registerPatient.setVisible(false);
           panel_registerDoctor.setVisible(false);
           panel_registerService.setVisible(true);
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
        con3 = DatabaseConnection.connectDB();
        stmt = con3.createStatement();
        
            int pID = ID;
            String fname = textField_pfname.getText();
            String lname = textField_plname.getText();
            String pnum =  textField_pphone.getText();
            String email = textField_pemail.getText();
            String street = textField_paddress.getText();
            String city = textField_pcity.getText();
            String zip = textField_pzip.getText();
            String state = textField_pstate.getText();
            String insid = textField_pinsid.getText();
            String insur = textField_pinsurance.getText();
            
        String patquery = "INSERT INTO PATIENT (PatientID, PFirstName, PLastName, PPhone, PEmail, Street, City, Zip, State, InsuranceID, Insurance)" 
                    + " VALUES  (" + pID + ",'" +fname+ "','" +lname+ "','" +pnum+ "','" +email+ "','" +street+ "','" +city+ "','" +zip+ "','" +state+ "'," +insid+ ",'" +insur+ "')";
        
        System.out.println(patquery);            
        stmt.executeQuery(patquery); 
            
        } catch (Exception e) {}
        
        panel_login.setVisible(true);
        panel_register.setVisible(false);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
        JOptionPane.showMessageDialog(null,"Registration Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
       
    }

    @FXML
    public void registerdoctor(ActionEvent event) {
        int ID = (int) id; 
        try {
        System.out.println("ID being used is: " + ID);
        con3 = DatabaseConnection.connectDB();
        stmt = con3.createStatement();
        
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
         JOptionPane.showMessageDialog(null,"Registration Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);

    } 
    
    @FXML
    public void registerLab(ActionEvent event) {
        int ID = (int) id; 
        try {
        System.out.println("ID being used is: " + ID);
        con3 = DatabaseConnection.connectDB();
        stmt = con3.createStatement();
        
            int labID = ID;
            String company = textField_sname.getText();
            String fax = textField_fax.getText();
            String num =  textField_sphone.getText();
            String email = textField_semail.getText();
            String street = textField_saddress.getText();
            String city = textField_scity.getText();
            String zip = textField_szip.getText();
            String state = textField_sstate.getText();
            
        String labquery = "INSERT INTO LAB (LabID, Street, City, State, Zip, Phone, Fax, Email, Name)" 
                    + " VALUES  (" +labID +",'" +street+
                    "','" +city+ "','" +state+ "','" +zip+ "','" +num+
                    "','" +fax+ "','" +email+ "','"+company+"')";
        
        System.out.println(labquery);            
        stmt.executeQuery(labquery); 
        JOptionPane.showMessageDialog(null,"Registration Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);    
        } catch (Exception e) {}
        
        panel_login.setVisible(true);
        panel_register.setVisible(false);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
        panel_registerService.setVisible(false);
        //JOptionPane.showMessageDialog(null,"Registration Complete");
        JOptionPane.showMessageDialog(null,"Registration Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
       
    }
    
    @FXML
    public void registerPharm(ActionEvent event) {
        int ID = (int) id; 
        try {
        System.out.println("ID being used is: " + ID);
        con3 = DatabaseConnection.connectDB();
        stmt = con3.createStatement();
        
            int pharmID = ID;
            String company = textField_sname.getText();
            String fax = textField_fax.getText();
            String num =  textField_sphone.getText();
            String email = textField_semail.getText();
            String street = textField_saddress.getText();
            String city = textField_scity.getText();
            String zip = textField_szip.getText();
            String state = textField_sstate.getText();
            
        String pharmquery = "INSERT INTO PHARMA (PharmID, Street, City, State, Zip, Phone, Fax, Email, Name)" 
                    + " VALUES  (" + pharmID + ",'" +street+
                    "','" +city+ "','" +state+ "','" +zip+ "','" +num+
                    "','" +fax+ "','" +email+ "','"+company+"')";
        
        System.out.println(pharmquery);            
        stmt.executeQuery(pharmquery); 
            
        } catch (Exception e) {}
        
        panel_login.setVisible(true);
        panel_register.setVisible(false);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
        panel_registerService.setVisible(false);
         JOptionPane.showMessageDialog(null,"Registration Complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
       
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //button_type2.getItems().addAll("Patient", "Doctor", "Service");
        
        String[] test = new String[3];
        test[0] = "Patient";
        test[1] = "Doctor";
        test[2] = "Service";
        
        button_type2.getItems().addAll(test);
        
      
    }  
    
    @FXML
    public void panelLoginShow() {
        panel_login.setVisible(true);
        panel_register.setVisible(false);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
        panel_registerService.setVisible(false);
    }
    
    @FXML
    public void panelRegisterShow() {
        panel_login.setVisible(false);
        panel_register.setVisible(true);
        panel_registerPatient.setVisible(false);
        panel_registerDoctor.setVisible(false);
        panel_registerService.setVisible(false);
    }
    
    @FXML
    private void switchToDevMenu() throws IOException 
    {
        App.setRoot("devMenu");
        App.currentUser = new CurrentUser("DevMenuUser");
    }
}

