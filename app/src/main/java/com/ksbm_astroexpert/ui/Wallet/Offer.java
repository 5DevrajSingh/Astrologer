package com.ksbm_astroexpert.ui.Wallet;

public class Offer {
    private String bannerId;

    private String bannerResId;

    private String bannerSrc;
    private String uri;
    private String date;
    private String name;
    private String description;
    private String cost;
    private String tone;
    private String answer;
    private String scheduled;
    private String scheduled_answer;
    private String user_answer;

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerResId() {
        return bannerResId;
    }

    public void setBannerResId(String bannerResId) {
        this.bannerResId = bannerResId;
    }

    public String getBannerSrc() {
        return bannerSrc;
    }

    public void setBannerSrc(String bannerSrc) {
        this.bannerSrc = bannerSrc;
    }


    public String geturi() {
        return uri;
    }

    public void seturi(String uri) {
        this.uri = uri;
    }



    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }



    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }


    public String gettone() {
        return tone;
    }

    public void settone(String tone) {
        this.tone = tone;
    }

    public String getcost() {
        return cost;
    }

    public void setcost(String cost) {
        this.cost = cost;
    }

    public String getanswer() {
        return answer;
    }

    public void setanswer(String answer) {
        this.answer = answer;
    }


    public String getscheduled() {
        return scheduled;
    }

    public void setscheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getscheduled_answer() {
        return scheduled_answer;
    }

    public void setscheduled_answer(String scheduled_answer) {
        this.scheduled_answer = scheduled_answer;
    }


    public String getuser_answer() {
        return user_answer;
    }

    public void setuser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    String user_ans_verified;


    public String getuser_ans_verified() {
        return user_ans_verified;
    }

    public void setuser_ans_verified(String user_ans_verified) {
        this.user_ans_verified = user_ans_verified;
    }
}