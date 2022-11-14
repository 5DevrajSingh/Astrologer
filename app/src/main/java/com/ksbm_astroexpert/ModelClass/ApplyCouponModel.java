package com.ksbm_astroexpert.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ApplyCouponModel implements Serializable {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("data")
    @Expose
    public Data data;

    public class Data {
        @SerializedName("coupon_id")
        @Expose
        public String couponId;
        @SerializedName("coupon_name")
        @Expose
        public String couponName;
        @SerializedName("coupon_type")
        @Expose
        public String couponType;
        @SerializedName("coupon_value")
        @Expose
        public String couponValue;
        @SerializedName("coupon_start_date")
        @Expose
        public String couponStartDate;
        @SerializedName("coupon_end_date")
        @Expose
        public String couponEndDate;
        @SerializedName("coupon_status")
        @Expose
        public String couponStatus;
        @SerializedName("coupon_use_per_user")
        @Expose
        public String couponUsePerUser;
        @SerializedName("coupon_cr_date")
        @Expose
        public String couponCrDate;
    }
}
