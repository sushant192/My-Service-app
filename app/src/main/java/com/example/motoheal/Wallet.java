package com.example.motoheal;

public class Wallet {

    String TotalPrice,Goldcoins,OwnerId,date,transactionId;

    public Wallet() {
    }

    public Wallet(String totalPrice, String goldcoins, String ownerId, String date, String transactionId) {
        TotalPrice = totalPrice;
        Goldcoins = goldcoins;
        OwnerId = ownerId;
        this.date = date;
        this.transactionId = transactionId;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getGoldcoins() {
        return Goldcoins;
    }

    public void setGoldcoins(String goldcoins) {
        Goldcoins = goldcoins;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(String ownerId) {
        OwnerId = ownerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
