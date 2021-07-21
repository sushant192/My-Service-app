package com.example.motoheal;

public class Notification1 {

    String Name,body,sentfrom,sentoto,date,flag,requestid;

    public Notification1() {
    }

    public Notification1(String name, String body, String sentfrom, String sentoto, String date, String flag, String requestid) {
        Name = name;
        this.body = body;
        this.sentfrom = sentfrom;
        this.sentoto = sentoto;
        this.date = date;
        this.flag = flag;
        this.requestid = requestid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSentfrom() {
        return sentfrom;
    }

    public void setSentfrom(String sentfrom) {
        this.sentfrom = sentfrom;
    }

    public String getSentoto() {
        return sentoto;
    }

    public void setSentoto(String sentoto) {
        this.sentoto = sentoto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }
}