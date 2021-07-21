package com.example.motoheal;

public class Painter {

    String ShopName,ShopNumber,Shopgid,ShopAddress,Noofworkers,Owner;

    public Painter() {
    }

    public Painter(String shopName, String shopNumber, String shopgid, String shopAddress, String noofworkers, String owner) {
        ShopName = shopName;
        ShopNumber = shopNumber;
        Shopgid = shopgid;
        ShopAddress = shopAddress;
        Noofworkers = noofworkers;
        Owner = owner;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getShopNumber() {
        return ShopNumber;
    }

    public void setShopNumber(String shopNumber) {
        ShopNumber = shopNumber;
    }

    public String getShopgid() {
        return Shopgid;
    }

    public void setShopgid(String shopgid) {
        Shopgid = shopgid;
    }

    public String getShopAddress() {
        return ShopAddress;
    }

    public void setShopAddress(String shopAddress) {
        ShopAddress = shopAddress;
    }

    public String getNoofworkers() {
        return Noofworkers;
    }

    public void setNoofworkers(String noofworkers) {
        Noofworkers = noofworkers;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }
}
