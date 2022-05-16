/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

/**
 *
 * @author Angie
 */
public class LabTable {
    int LabID;
    String Street, City, State, Zip, Phone, Fax, Email, Name;
    public LabTable(int LabID, String Street, String City, String State, String Zip, String Phone, String Fax, String Email, String Name) {
        this.LabID = LabID;
        this.Street = Street;
        this.City = City;
        this.State = State;
        this.Zip = Zip;
        this.Phone = Phone;
        this.Fax = Fax;
        this.Email = Email;
        this.Name = Name;
    }

    public int getLabID() {
        return LabID;
    }

    public void setLabID(int LabID) {
        this.LabID = LabID;
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

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
   
}
