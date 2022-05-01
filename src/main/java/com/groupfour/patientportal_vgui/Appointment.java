/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

/**
 *
 * @author Alexander
 */
public class Appointment {

    public Appointment(int Appid, int DoctorID, int PatientID, int OfficeID, int LabID, String Reason, String Date, String Time) {
        this.Appid = Appid;
        this.DoctorID = DoctorID;
        this.PatientID = PatientID;
        this.OfficeID = OfficeID;
        this.LabID = LabID;
        this.Reason = Reason;
        this.Date = Date;
        this.Time = Time;
    }

    public int getAppid() {
        return Appid;
    }

    public void setAppid(int Appid) {
        this.Appid = Appid;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int DoctorID) {
        this.DoctorID = DoctorID;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int PatientID) {
        this.PatientID = PatientID;
    }

    public int getOfficeID() {
        return OfficeID;
    }

    public void setOfficeID(int OfficeID) {
        this.OfficeID = OfficeID;
    }

    public int getLabID() {
        return LabID;
    }

    public void setLabID(int LabID) {
        this.LabID = LabID;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    int Appid, DoctorID, PatientID, OfficeID, LabID;
    String Reason, Date, Time;
   
    
   
    

}
