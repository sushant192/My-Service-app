package com.example.motoheal;

public class Request {

    String sentTo,BusinessName,RequestId,flag,clickflag,date,totalAmount,timetaken;

    public Request() {
    }

    public Request(String sentTo, String businessName, String requestId, String flag, String clickflag, String date, String totalAmount, String timetaken) {
        this.sentTo = sentTo;
        BusinessName = businessName;
        RequestId = requestId;
        this.flag = flag;
        this.clickflag = clickflag;
        this.date = date;
        this.totalAmount = totalAmount;
        this.timetaken = timetaken;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getClickflag() {
        return clickflag;
    }

    public void setClickflag(String clickflag) {
        this.clickflag = clickflag;
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
