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
    
     @FXML
    private Button button_login;

    @FXML
    private Button button_register;

    @FXML
    private ComboBox button_type;

    @FXML
    private ComboBox button_type2;
    
    @FXML
    private Button button_devMenu;

    @FXML
    private AnchorPane panel_login;

    @FXML
    private AnchorPane panel_register;

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
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public void panelLoginShow() {
        panel_login.setVisible(true);
        panel_register.setVisible(false);
    }

    public void panelRegisterShow() {
        panel_login.setVisible(false);
        panel_register.setVisible(true);
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
                break;

            }
        if (rs.getString(5).toUpperCase().equals("PATIENT"))
        {
                    button_login.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("patientDashboard.fxml")); 
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show(); 
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
        long id = 0;
        con = DatabaseConnection.connectDB();
        String sql = "INSERT INTO LOGIN (Username, Password, Email, Type) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, text_user2.getText());
            ps.setString(2, text_pass2.getText());
            ps.setString(3, text_email.getText());
            ps.setString(4, button_type2.getValue().toString());
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1); 
                    }
                } catch (Exception e){} 
            }
            
            System.out.println (id); 
            
            JOptionPane.showMessageDialog(null, "Registered successfully");
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        }
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
