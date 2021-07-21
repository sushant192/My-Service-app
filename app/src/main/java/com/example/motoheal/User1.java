package com.example.motoheal;

public class User1 {

    String custName,custLastName,custNumber,custEmail,flag,custId,custRequestId,custpic,type,date,totalAmount,timetaken;

    public User1() {
    }

    public User1(String custName, String custLastName, String custNumber, String custEmail, String flag, String custId, String custRequestId, String custpic, String type, String date, String totalAmount, String timetaken) {
        this.custName = custName;
        this.custLastName = custLastName;
        this.custNumber = custNumber;
        this.custEmail = custEmail;
        this.flag = flag;
        this.custId = custId;
        this.custRequestId = custRequestId;
        this.custpic = custpic;
        this.type = type;
        this.date = date;
        this.totalAmount = totalAmount;
        this.timetaken = timetaken;
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

    public String getCustpic() {
        return custpic;
    }

    public void setCustpic(String custpic) {
        this.custpic = custpic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(String timetaken) {
        this.timetaken = timetaken;
    }
}
