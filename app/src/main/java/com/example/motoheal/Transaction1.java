package com.example.motoheal;

public class Transaction1 {

    String Mode,BasePrice,TravelFair,TotalPrice,SentFrom,TransactionId,date;

    public Transaction1() {
    }

    public Transaction1(String mode, String basePrice, String travelFair, String totalPrice, String sentFrom, String transactionId, String date) {
        Mode = mode;
        BasePrice = basePrice;
        TravelFair = travelFair;
        TotalPrice = totalPrice;
        SentFrom = sentFrom;
        TransactionId = transactionId;
        this.date = date;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getBasePrice() {
        return BasePrice;
    }

    public void setBasePrice(String basePrice) {
        BasePrice = basePrice;
    }

    public String getTravelFair() {
        return TravelFair;
    }

    public void setTravelFair(String travelFair) {
        TravelFair = travelFair;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getSentFrom() {
        return SentFrom;
    }

    public void setSentFrom(String sentFrom) {
        SentFrom = sentFrom;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
