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
    int trid;
    String title, result, trdate, type;
    int trpatientid, trlabid; 
    
    public TRTable(int trid, String title, String result, String trdate, String type, int trpatientid, int trlabid) {
        this.trid = trid;
        this.title = title;
        this.result = result;
        this.trdate = trdate;
        this.type = type;
        this.trpatientid = trpatientid;
        this.trlabid = trlabid;
    }

    

    public int gettrid() {
        return trid;
    }

    public void settrid(int trid) {
        this.trid = trid;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getresult() {
        return result;
    }

    public void setresult(String result) {
        this.result = result;
    }
    
    public String gettrdate() {
        return trdate;
    }

    public void settrdate(String trdate) {
        this.trdate = trdate;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public int gettrpatientid() {
        return trpatientid;
    }

    public void settrpatientid(int trpatientid) {
        this.trpatientid = trpatientid;
    }

    public int gettrlabid() {
        return trlabid;
    }

    public void settrlabid(int trlabid) {
        this.trlabid = trlabid;
    }
}
