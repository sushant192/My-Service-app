package com.example.motoheal;

public class PlumberPartnerRequest1 {

    String custName,custLastName,custNumber,custEmail,flag,custId,custRequestId,TotalRooms,HowmanyDays;

    public PlumberPartnerRequest1() {
    }

    public PlumberPartnerRequest1(String custName, String custLastName, String custNumber, String custEmail, String flag, String custId, String custRequestId, String totalRooms, String howmanyDays) {
        this.custName = custName;
        this.custLastName = custLastName;
        this.custNumber = custNumber;
        this.custEmail = custEmail;
        this.flag = flag;
        this.custId = custId;
        this.custRequestId = custRequestId;
        TotalRooms = totalRooms;
        HowmanyDays = howmanyDays;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustNumber() {
        return custNumber;
    }

    public void setCustNumber(String custNumber) {
        this.custNumber = custNumber;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustRequestId() {
        return custRequestId;
    }

    public void setCustRequestId(String custRequestId) {
        this.custRequestId = custRequestId;
    }

    public String getTotalRooms() {
        return TotalRooms;
    }

    public void setTotalRooms(String totalRooms) {
        TotalRooms = totalRooms;
    }

    public String getHowmanyDays() {
        return HowmanyDays;
    }

    public void setHowmanyDays(String howmanyDays) {
        HowmanyDays = howmanyDays;
    }
}
