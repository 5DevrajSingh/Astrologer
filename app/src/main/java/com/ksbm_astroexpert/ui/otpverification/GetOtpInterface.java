package com.ksbm_astroexpert.ui.otpverification;

public interface GetOtpInterface {
    void onOtpReceived(String otp);
    void onOtpTimeout();
}