package com.example.motoheal;

public class Business {

    String ShopName,ShopNumber,Shopgid,ShopAddress,Noofworkers,Workshopname,GstNumber,Owner;

    public Business() {
    }

    public Business(String shopName, String shopNumber, String shopgid, String shopAddress, String noofworkers, String workshopname, String gstNumber, String owner) {
        ShopName = shopName;
        ShopNumber = shopNumber;
        Shopgid = shopgid;
        ShopAddress = shopAddress;
        Noofworkers = noofworkers;
        Workshopname = workshopname;
        GstNumber = gstNumber;
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

    public String getWorkshopname() {
        return Workshopname;
    }

    public void setWorkshopname(String workshopname) {
        Workshopname = workshopname;
    }

    public String getGstNumber() {
        return GstNumber;
    }

    public void setGstNumber(String gstNumber) {
        GstNumber = gstNumber;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }
}
