package com.example.motoheal;

public class Worker {

    String Workername,Workerphone,Workerid,Workeraddress,Workerdob,Workergender,image1,image2;

    public Worker() {
    }

    public Worker(String workername, String workerphone, String workerid, String workeraddress, String workerdob, String workergender, String image1, String image2) {
        Workername = workername;
        Workerphone = workerphone;
        Workerid = workerid;
        Workeraddress = workeraddress;
        Workerdob = workerdob;
        Workergender = workergender;
        this.image1 = image1;
        this.image2 = image2;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
