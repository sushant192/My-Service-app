package com.example.motoheal;

public class Withdraw {

    String BusinessName,UpiName,UpiId,Amount,BusinessId,WithdrawId,flag,date;

    public Withdraw() {
    }

    public Withdraw(String businessName, String upiName, String upiId, String amount, String businessId, String withdrawId, String flag, String date) {
        BusinessName = businessName;
        UpiName = upiName;
        UpiId = upiId;
        Amount = amount;
        BusinessId = businessId;
        WithdrawId = withdrawId;
        this.flag = flag;
        this.date = date;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getUpiName() {
        return UpiName;
    }

    public void setUpiName(String upiName) {
        UpiName = upiName;
    }

    public String getUpiId() {
        return UpiId;
    }

    public void setUpiId(String upiId) {
        UpiId = upiId;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(String businessId) {
        BusinessId = businessId;
    }

    public String getWithdrawId() {
        return WithdrawId;
    }

    public void setWithdrawId(String withdrawId) {
        WithdrawId = withdrawId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
