package com.example.motoheal;

public class PlumberPartnerRequest {

    String custName,custLastName,custNumber,custEmail,flag,custId,custRequestId,Service,Issues;

    public PlumberPartnerRequest() {
    }

    public PlumberPartnerRequest(String custName, String custLastName, String custNumber, String custEmail, String flag, String custId, String custRequestId, String service, String issues) {
        this.custName = custName;
        this.custLastName = custLastName;
        this.custNumber = custNumber;
        this.custEmail = custEmail;
        this.flag = flag;
        this.custId = custId;
        this.custRequestId = custRequestId;
        Service = service;
        Issues = issues;
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

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    public String getIssues() {
        return Issues;
    }

    public void setIssues(String issues) {
        Issues = issues;
    }
}
