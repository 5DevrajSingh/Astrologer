package com.ksbm_astroexpert.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RechargeCustomerModel implements Serializable {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("Response")
    @Expose
    public String response;
    @SerializedName("Custmer Recharge Details")
    @Expose
    public CustmerRechargeDetails custmerRechargeDetails;
    public class CustmerRechargeDetails {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("role")
        @Expose
        public Object role;
        @SerializedName("social_id")
        @Expose
        public String socialId;
        @SerializedName("login_type")
        @Expose
        public String loginType;
        @SerializedName("ip_address")
        @Expose
        public String ipAddress;
        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("password")
        @Expose
        public Object password;
        @SerializedName("salt")
        @Expose
        public Object salt;
        @SerializedName("email")
        @Expose
        public Object email;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("activation_code")
        @Expose
        public Object activationCode;
        @SerializedName("forgotten_password_code")
        @Expose
        public Object forgottenPasswordCode;
        @SerializedName("forgotten_password_time")
        @Expose
        public Object forgottenPasswordTime;
        @SerializedName("remember_code")
        @Expose
        public Object rememberCode;
        @SerializedName("created_on")
        @Expose
        public String createdOn;
        @SerializedName("last_login")
        @Expose
        public Object lastLogin;
        @SerializedName("active")
        @Expose
        public Object active;
        @SerializedName("first_name")
        @Expose
        public String firstName;
        @SerializedName("last_name")
        @Expose
        public String lastName;
        @SerializedName("user_profile_image")
        @Expose
        public String userProfileImage;
        @SerializedName("permission")
        @Expose
        public Object permission;
        @SerializedName("company")
        @Expose
        public Object company;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("device_id")
        @Expose
        public Object deviceId;
        @SerializedName("device_token")
        @Expose
        public String deviceToken;
        @SerializedName("lat")
        @Expose
        public Object lat;
        @SerializedName("lon")
        @Expose
        public Object lon;
        @SerializedName("token")
        @Expose
        public Object token;
        @SerializedName("otp")
        @Expose
        public String otp;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("verification")
        @Expose
        public String verification;
        @SerializedName("wallet")
        @Expose
        public String wallet;
        @SerializedName("date_of_birth")
        @Expose
        public String dateOfBirth;
        @SerializedName("time_of_birth")
        @Expose
        public String timeOfBirth;
        @SerializedName("place_of_birth")
        @Expose
        public String placeOfBirth;
        @SerializedName("referral_code")
        @Expose
        public String referralCode;
        @SerializedName("customer_cr_date")
        @Expose
        public String customerCrDate;
    }
}
