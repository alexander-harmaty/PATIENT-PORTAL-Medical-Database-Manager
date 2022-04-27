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
    
    String AppID, Reason, Date, Time, DoctorID, PatientID, OfficeID, LabID;

    public Appointment(String AppID, String Reason, String Date, String Time, String DoctorID, String PatientID, String OfficeID, String LabID) {
        this.AppID = AppID;
        this.Reason = Reason;
        this.Date = Date;
        this.Time = Time;
        this.DoctorID = DoctorID;
        this.PatientID = PatientID;
        this.OfficeID = OfficeID;
        this.LabID = LabID;
    }

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String AppID) {
        this.AppID = AppID;
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

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public String getOfficeID() {
        return OfficeID;
    }

    public void setOfficeID(String OfficeID) {
        this.OfficeID = OfficeID;
    }

    public String getLabID() {
        return LabID;
    }

    public void setLabID(String LabID) {
        this.LabID = LabID;
    }
}
