package com.example.motoheal;

public class PlumberRequest1 {

    String sentTo,BusinessName,RequestId,flag,clickflag,TotalRooms,HowmanyDays;

    public PlumberRequest1() {
    }

    public PlumberRequest1(String sentTo, String businessName, String requestId, String flag, String clickflag, String totalRooms, String howmanyDays) {
        this.sentTo = sentTo;
        BusinessName = businessName;
        RequestId = requestId;
        this.flag = flag;
        this.clickflag = clickflag;
        TotalRooms = totalRooms;
        HowmanyDays = howmanyDays;
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
