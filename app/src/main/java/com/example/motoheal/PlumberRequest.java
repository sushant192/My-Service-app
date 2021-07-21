package com.example.motoheal;

public class PlumberRequest {

    String sentTo,BusinessName,RequestId,flag,clickflag,Service,Issues;

    public PlumberRequest() {
    }

    public PlumberRequest(String sentTo, String businessName, String requestId, String flag, String clickflag, String service, String issues) {
        this.sentTo = sentTo;
        BusinessName = businessName;
        RequestId = requestId;
        this.flag = flag;
        this.clickflag = clickflag;
        Service = service;
        Issues = issues;
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
