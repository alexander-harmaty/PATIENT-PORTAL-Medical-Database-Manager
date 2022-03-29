/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

import javafx.fxml.Initializable;

/**
 *
 * @author Angie
 */
public class PatientTable  {
    
    int PatientID, InsuranceID, PrimaryDoctor; 
    String PFirstName, PLastName, PPhone, PEmail, Street, City, Zip, State, Insurance; 

    public Integer getPatientID() {
        return PatientID;
    }

    public void setPatientID(Integer PatientID) {
        this.PatientID = PatientID;
    }

    public String getPFirstName() {
        return PFirstName;
    }

    public void setPFirstName(String PFirstName) {
        this.PFirstName = PFirstName;
    }

    public String getPLastName() {
        return PLastName;
    }

    public void setPLastName(String PLastName) {
        this.PLastName = PLastName;
    }

    public String getPPhone() {
        return PPhone;
    }

    public void setPPhone(String PPhone) {
        this.PPhone = PPhone;
    }

    public String getPEmail() {
        return PEmail;
    }

    public void setPEmail(String PEmail) {
        this.PEmail = PEmail;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public Integer getInsuranceID() {
        return InsuranceID;
    }

    public void setInsuranceID(Integer InsuranceID) {
        this.InsuranceID = InsuranceID;
    }

    public String getInsurance() {
        return Insurance;
    }

    public void setInsurance(String Insurance) {
        this.Insurance = Insurance;
    }

    public Integer getPrimaryDoctor() {
        return PrimaryDoctor;
    }

    public void setPrimaryDoctor(Integer PrimaryDoctor) {
        this.PrimaryDoctor = PrimaryDoctor;
    }

    public PatientTable(Integer PatientID, String PFirstName, String PLastName, String PPhone, String PEmail, String Street, String City, String Zip, String State, Integer InsuranceID, String Insurance, Integer PrimaryDoctor) {
        this.PatientID = PatientID;
        this.PFirstName = PFirstName;
        this.PLastName = PLastName;
        this.PPhone = PPhone;
        this.PEmail = PEmail;
        this.Street = Street;
        this.City = City;
        this.Zip = Zip;
        this.State = State;
        this.InsuranceID = InsuranceID;
        this.Insurance = Insurance;
        this.PrimaryDoctor = PrimaryDoctor;
    }
  
    
}
