package com.ksbm_astroexpert.ui.signin;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInResponseModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Response")
    @Expose
    private Object response = null;
    @SerializedName("otp")
    @Expose
    private int otp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public static class User implements Parcelable {

        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("id")
        @Expose
        private String id;

        protected User(Parcel in) {
            username = in.readString();
            phone = in.readString();
            id = in.readString();
        }

        public static Creator<User> CREATOR = new Creator<User>() {
            @Override
            public User createFromParcel(Parcel in) {
                return new User(in);
            }

            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(username);
            dest.writeString(phone);
            dest.writeString(id);
        }
    }
}
