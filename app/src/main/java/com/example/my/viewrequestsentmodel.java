package com.example.my;

public class viewrequestsentmodel {

    String myemail;
    String recemail;
    String machinename;
    String requesthasbeensent;
    String accept;
    String norequestsent;
    String eraseusername;
    String erasemacname;

    public String getEraseusername() {
        return eraseusername;
    }

    public void setEraseusername(String eraseusername) {
        this.eraseusername = eraseusername;
    }

    public String getErasemacname() {
        return erasemacname;
    }

    public void setErasemacname(String erasemacname) {
        this.erasemacname = erasemacname;
    }

    public String getNorequestsent() {
        return norequestsent;
    }

    public void setNorequestsent(String norequestsent) {
        this.norequestsent = norequestsent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getMyemail() {
        return myemail;
    }

    public void setMyemail(String myemail) {
        this.myemail = myemail;
    }

    public String getRecemail() {
        return recemail;
    }

    public void setRecemail(String recemail) {
        this.recemail = recemail;
    }

    public String getMachinename() {
        return machinename;
    }

    public void setMachinename(String machinename) {
        this.machinename = machinename;
    }

    public String getRequesthasbeensent() {
        return requesthasbeensent;
    }

    public void setRequesthasbeensent(String requesthasbeensent) {
        this.requesthasbeensent = requesthasbeensent;
    }

    public viewrequestsentmodel(String myemail, String recemail, String machinename, String requesthasbeensent,String accept,String norequestsent,String eraseusername,String erasemacname) {
        this.myemail = myemail;
        this.recemail = recemail;
        this.machinename = machinename;
        this.requesthasbeensent = requesthasbeensent;
        this.accept = accept;
        this.norequestsent = norequestsent;
        this.eraseusername = eraseusername;
        this.erasemacname = erasemacname;

    }

    public viewrequestsentmodel(){}

}
