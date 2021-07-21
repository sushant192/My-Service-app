package com.example.motoheal;

public class Service {

    String ShopName,ShopNumber,Shopgid,ShopAddress,Noofworkers,Profilepic,Workshopname,GstNumber;

    public Service() {
    }

    public Service(String shopName, String shopNumber, String shopgid, String shopAddress, String noofworkers, String profilepic, String workshopname, String gstNumber) {
        ShopName = shopName;
        ShopNumber = shopNumber;
        Shopgid = shopgid;
        ShopAddress = shopAddress;
        Noofworkers = noofworkers;
        Profilepic = profilepic;
        Workshopname = workshopname;
        GstNumber = gstNumber;
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

    public String getProfilepic() {
        return Profilepic;
    }

    public void setProfilepic(String profilepic) {
        Profilepic = profilepic;
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
}
