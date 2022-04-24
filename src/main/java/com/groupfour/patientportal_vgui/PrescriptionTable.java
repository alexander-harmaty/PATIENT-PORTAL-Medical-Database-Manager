/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

/**
 *
 * @author Angie
 * ADDED TABLE FOR PRESCRIPTIONS
 */
public class PrescriptionTable {

    int PatientID, PharmID, DoctorID, ScriptID; 
    String Medication, Description, DATE, Status, Frequency, Dosage, Quantity;
    
    public PrescriptionTable(int PatientID, int PharmID, int DoctorID, int ScriptID, String Medication, String Description, String DATE, String Status, String Frequency, String Dosage, String Quantity) {
        this.PatientID = PatientID;
        this.PharmID = PharmID;
        this.DoctorID = DoctorID;
        this.ScriptID = ScriptID;
        this.Medication = Medication;
        this.Description = Description;
        this.DATE = DATE;
        this.Status = Status;
        this.Frequency = Frequency;
        this.Dosage = Dosage;
        this.Quantity = Quantity;
    }

    

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int PatientID) {
        this.PatientID = PatientID;
    }

    public int getPharmID() {
        return PharmID;
    }

    public void setPharmID(int PharmID) {
        this.PharmID = PharmID;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int DoctorID) {
        this.DoctorID = DoctorID;
    }
    
    public int getScriptID() {
        return ScriptID;
    }

    public void setScriptID(int ScriptID) {
        this.ScriptID = ScriptID;
    }

    public String getMedication() {
        return Medication;
    }

    public void setMedication(String Medication) {
        this.Medication = Medication;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String Frequency) {
        this.Frequency = Frequency;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String Dosage) {
        this.Dosage = Dosage;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }
     
}
