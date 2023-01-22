package com.example.my;

public class viewrequestmodel
{
    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    String id,myemail,senderlocation,status,from,machinename,currentusername,date,number,description,hours,var;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrentusername() {
        return currentusername;
    }

    public void setCurrentusername(String currentusername) {
        this.currentusername = currentusername;
    }

    public String getMachinename() {
        return machinename;
    }

    public void setMachinename(String machinename) {
        this.machinename = machinename;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenderlocation() {
        return senderlocation;
    }

    public void setSenderlocation(String senderlocation) {
        this.senderlocation = senderlocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMyemail() {
        return myemail;
    }

    public void setMyemail(String myemail) {
        this.myemail = myemail;
    }

    public viewrequestmodel(String id, String myemail,String senderlocation,String status,String from,String var,String machinename,String currentusername,String date,String number,String description,String hours) {
        this.id = id;
        this.myemail = myemail;
        this.senderlocation = senderlocation;
        this.status = status;
        this.from = from;
        this.var = var;
        this.machinename = machinename;
        this.currentusername = currentusername;
        this.date = date;
        this.number = number;
        this.description = description;
        this.hours = hours;
    }

    public viewrequestmodel() {
    }







}
