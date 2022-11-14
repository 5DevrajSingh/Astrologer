package com.ksbm_astroexpert.ui.home;

public class AstroModel {


    private String astroId;
    private String astroName;
    private String astroImage;
    private String astroShop;
    private String astroEmail;
    private String astroAddress;
    private String astroPhone;
    private String astroRating;
    private String astroCallPrice;
    private String wait_time;
    private String chat_commission;
    private String call_commission;
    private String device_token;
    private String astro_exp;

    public AstroModel(String astroId, String astroName, String astroImage, String astroShop, String astroEmail, String astroAddress, String astroPhone, String astroRating, String astroCallPrice, String astroChatPrice, String astroStatus, String astroStatus_call
    ,String wait_time, String chat_commission, String call_commission,String device_token) {
        this.astroId = astroId;
        this.astroName = astroName;
        this.astroImage = astroImage;
        this.astroShop = astroShop;
        this.astroEmail = astroEmail;
        this.astroAddress = astroAddress;
        this.astroPhone = astroPhone;
        this.astroRating = astroRating;
        this.astroCallPrice = astroCallPrice;
        this.astroChatPrice = astroChatPrice;
        this.astroStatus = astroStatus;
        this.astroStatus_call = astroStatus_call;
        this.wait_time =wait_time;
        this.chat_commission =chat_commission;
        this.call_commission =call_commission;
        this.device_token=device_token;
    }

    private String astroChatPrice;
    private String astroStatus;
    private String astroStatus_call;

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getAstroName() {
        return astroName;
    }

    public void setAstroName(String astroName) {
        this.astroName = astroName;
    }


    public String getwait_time() {
        return wait_time;
    }

    public void setwait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    public String getAstroShop() {
        return astroShop;
    }

    public void setAstroShop(String astroShop) {
        this.astroShop = astroShop;
    }

    public String getAstroEmail() {
        return astroEmail;
    }

    public void setAstroEmail(String astroEmail) {
        this.astroEmail = astroEmail;
    }

    public String getAstroAddress() {
        return astroAddress;
    }

    public void setAstroAddress(String astroAddress) {
        this.astroAddress = astroAddress;
    }

    public String getAstroPhone() {
        return astroPhone;
    }

    public void setAstroPhone(String astroPhone) {
        this.astroPhone = astroPhone;
    }

    public String getAstroCallPrice() {
        return astroCallPrice;
    }

    public void setAstroCallPrice(String astroCallPrice) {
        this.astroCallPrice = astroCallPrice;
    }

    public String getAstroChatPrice() {
        return astroChatPrice;
    }

    public void setAstroChatPrice(String astroChatPrice) {
        this.astroChatPrice = astroChatPrice;
    }

    public String getAstroStatus() {
        return astroStatus;
    }

    public void setAstroStatus(String astroStatus) {
        this.astroStatus = astroStatus;
    }





    public String getAstroStatus_call() {
        return astroStatus_call;
    }

    public void setAstroStatus_call(String astroStatus_call) {
        this.astroStatus_call = astroStatus_call;
    }




    public String getAstroId() {
        return astroId;
    }

    public void setAstroId(String astroId) {
        this.astroId = astroId;
    }

    public String getAstroImage() {
        return astroImage;
    }

    public void setAstroImage(String astroImage) {
        this.astroImage = astroImage;
    }

    public String getAstroRating() {
        return astroRating;
    }

    public void setAstroRating(String astroRating) {
        this.astroRating = astroRating;
    }



    public String getchat_commission() {
        return chat_commission;
    }

    public void setchat_commission(String chat_commission) {
        this.chat_commission = chat_commission;
    }

    public String getcall_commission() {
        return call_commission;
    }

    public void setcall_commission(String call_commission) {
        this.call_commission = call_commission;
    }


}
