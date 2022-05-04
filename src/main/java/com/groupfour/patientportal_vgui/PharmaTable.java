/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

/**
 *
 * @author Yaskh
 */
public class PharmaTable  {
    
    int PharmID; 
    String Name, Street, City, State, Zip, Email, Phone, Fax; 

    public Integer getPharmID() {
        return PharmID;
    }

    public void setPharmID(Integer PharmID) {
        this.PharmID = PharmID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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
    
    public PharmaTable(Integer PharmID, String Name, String Street
            , String City, String State, String Zip, String Email, String Phone, String Fax) {
        this.PharmID = PharmID;
        this.Name = Name;
        this.Street = Street;
        this.City = City;
        this.State = State;
        this.Zip = Zip;
        this.Email = Email;
        this.Phone = Phone;
        this.Fax = Fax;
        
    }
    
} 

