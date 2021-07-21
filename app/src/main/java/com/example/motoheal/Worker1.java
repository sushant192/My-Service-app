package com.example.motoheal;

public class Worker1 {

    String Workername,Workerphone,Workerid,Workeraddress,Workerdob,Workergender,Under;

    public Worker1() {
    }

    public Worker1(String workername, String workerphone, String workerid, String workeraddress, String workerdob, String workergender, String under) {
        Workername = workername;
        Workerphone = workerphone;
        Workerid = workerid;
        Workeraddress = workeraddress;
        Workerdob = workerdob;
        Workergender = workergender;
        Under = under;
    }

    public String getWorkername() {
        return Workername;
    }

    public void setWorkername(String workername) {
        Workername = workername;
    }

    public String getWorkerphone() {
        return Workerphone;
    }

    public void setWorkerphone(String workerphone) {
        Workerphone = workerphone;
    }

    public String getWorkerid() {
        return Workerid;
    }

    public void setWorkerid(String workerid) {
        Workerid = workerid;
    }

    public String getWorkeraddress() {
        return Workeraddress;
    }

    public void setWorkeraddress(String workeraddress) {
        Workeraddress = workeraddress;
    }

    public String getWorkerdob() {
        return Workerdob;
    }

    public void setWorkerdob(String workerdob) {
        Workerdob = workerdob;
    }

    public String getWorkergender() {
        return Workergender;
    }

    public void setWorkergender(String workergender) {
        Workergender = workergender;
    }

    public String getUnder() {
        return Under;
    }

    public void setUnder(String under) {
        Under = under;
    }
}
