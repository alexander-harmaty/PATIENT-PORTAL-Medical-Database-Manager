/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

/**
 *
 * @author Alexander
 */
public class CurrentUser 
{
    
    protected static String userID;
    private static String firstName;
    private static String lastName;
    private static String phone;
    private static String email;
    private static String type;

    public CurrentUser() {
        this.userID = "userID";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.phone = "phone";
        this.email = "email";
        this.type = "type";
    }
    
    public CurrentUser(String userID, String type) {
        this.userID = userID;
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.phone = "phone";
        this.email = "email";
        this.type = type;
    }    

    public String getUserID() {
        return userID;
    }

//    public void setUserID(String userID) {
//        this.userID = userID;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
