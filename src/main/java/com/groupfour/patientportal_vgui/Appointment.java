/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

/**
 *
 * @author AlexH
 */
public class Appointment {
    String AppID, Date, Time, PatientID, OfficeID, LabID;

    public Appointment(String AppID, String Date, String Time, String PatientID, String OfficeID, String LabID) {
        this.AppID = AppID;
        this.Date = Date;
        this.Time = Time;
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
