/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.patientportal_vgui;

/**
 *
 * @author Yaskh
 */
public class ECTable {
     
    String ECfirst, EClast,ECphone, Relation; 
    
    
    public String getECfirst() {
        return ECfirst;
    }

    public void setECfirst(String ECfirst) {
        this.ECfirst = ECfirst;
    }

    public String getEClast() {
        return EClast;
    }

    public void setEClast(String EClast) {
        this.EClast = EClast;
    }

    public String getECphone() {
        return ECphone;
    }

    public void setECphone(String ECphone) {
        this.ECphone = ECphone;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelationl(String Relation) {
        this.Relation = Relation;
    }

   
    public ECTable(String ECfirst, String EClast, String ECphone, String Relation) {
        
        this.ECfirst = ECfirst;
        this.EClast = EClast;
        this.ECphone = ECphone;
        this.Relation = Relation;
        
    }
}
