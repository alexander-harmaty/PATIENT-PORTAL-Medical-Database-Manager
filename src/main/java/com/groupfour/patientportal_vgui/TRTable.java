/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

/**
 *
 * @author Yaskh
 */
public class TRTable {
    int TRID;
    String Title, Result, Date, Type;
    int PatientID, LabID;
    String Name, City;
    
    public TRTable(int TRID, String Title, String Result, String Date, String Type, int PatientID, int LabID, String Name, String City) {
        this.TRID = TRID;
        this.Title = Title;
        this.Result = Result;
        this.Date = Date;
        this.Type = Type;
        this.PatientID = PatientID;
        this.LabID = LabID;
        this.Name = Name;
        this.City = City;
    }

    

    public int getTRID() {
        return TRID;
    }

    public void seTRID(int TRID) {
        this.TRID = TRID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }
    
    public String getDate() {
        return Date;
    }

    public void setDate(String Dte) {
        this.Date = Date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int PAtientID) {
        this.PatientID = PatientID;
    }

    public int getLabID() {
        return LabID;
    }

    public void setLabID(int LabID) {
        this.LabID= LabID;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
}
