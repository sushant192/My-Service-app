package com.example.motoheal;

public class Transaction {

    String Mode,BasePrice,TravelFair,TotalPrice,SentTo,TransactionId,date;

    public Transaction() {
    }

    public Transaction(String mode, String basePrice, String travelFair, String totalPrice, String sentTo, String transactionId, String date) {
        Mode = mode;
        BasePrice = basePrice;
        TravelFair = travelFair;
        TotalPrice = totalPrice;
        SentTo = sentTo;
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

    public String getSentTo() {
        return SentTo;
    }

    public void setSentTo(String sentTo) {
        SentTo = sentTo;
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
