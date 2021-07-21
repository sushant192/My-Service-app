package com.example.motoheal;

public class Partner {

    String Fullname,PhoneNumber,email,password,type,Address,DOB,partnerType,idType,Image1,Image2;

    public Partner() {
    }

    public Partner(String fullname, String phoneNumber, String email, String password, String type, String address, String DOB, String partnerType, String idType, String image1, String image2) {
        Fullname = fullname;
        PhoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.type = type;
        Address = address;
        this.DOB = DOB;
        this.partnerType = partnerType;
        this.idType = idType;
        Image1 = image1;
        Image2 = image2;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }
}
