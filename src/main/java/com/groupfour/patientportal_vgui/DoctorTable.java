package com.groupfour.patientportal_vgui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Angie
 */
public class DoctorTable {

    int DoctorID;
    String DFirstName, DLastName, DPhone, DEmail, Degree, Specialty;
    
    public DoctorTable(int DoctorID, String DFirstName, String DLastName, String DPhone, String DEmail, String Degree, String Specialty) {
        this.DoctorID = DoctorID;
        this.DFirstName = DFirstName;
        this.DLastName = DLastName;
        this.DPhone = DPhone;
        this.DEmail = DEmail;
        this.Degree = Degree;
        this.Specialty = Specialty;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int DoctorID) {
        this.DoctorID = DoctorID;
    }

    public String getDFirstName() {
        return DFirstName;
    }

    public void setDFirstName(String DFirstName) {
        this.DFirstName = DFirstName;
    }

    public String getDLastName() {
        return DLastName;
    }

    public void setDLastName(String DLastName) {
        this.DLastName = DLastName;
    }

    public String getDPhone() {
        return DPhone;
    }

    public void setDPhone(String DPhone) {
        this.DPhone = DPhone;
    }

    public String getDEmail() {
        return DEmail;
    }

    public void setDEmail(String DEmail) {
        this.DEmail = DEmail;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String Degree) {
        this.Degree = Degree;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String Specialty) {
        this.Specialty = Specialty;
    }
    
}
