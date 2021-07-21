package com.example.motoheal;

public class Ratings {

    String stars,date,sentfrom,sentto;

    public Ratings() {
    }

    public Ratings(String stars, String date, String sentfrom, String sentto) {
        this.stars = stars;
        this.date = date;
        this.sentfrom = sentfrom;
        this.sentto = sentto;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSentfrom() {
        return sentfrom;
    }

    public void setSentfrom(String sentfrom) {
        this.sentfrom = sentfrom;
    }

    public String getSentto() {
        return sentto;
    }

    public void setSentto(String sentto) {
        this.sentto = sentto;
    }
}
