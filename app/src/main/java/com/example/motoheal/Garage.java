package com.example.motoheal;

public class Garage {
    String Gname,Address;

    public Garage() {
    }

    public Garage(String gname, String address) {
        Gname = gname;
        Address = address;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddrerss(String address) {
        Address = address;
    }
}
