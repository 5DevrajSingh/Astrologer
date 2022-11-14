package com.ksbm_astroexpert.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WalletModel implements Serializable {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("firstoffer")
    @Expose
    public String firstoffer;
    @SerializedName("response")
    @Expose
    public ArrayList<Response> response = null;

    @SerializedName("records2")
    @Expose
    public ArrayList<Response2> response2 = null;

    public class Response {

        @SerializedName("recharge_plan_id")
        @Expose
        public String rechargePlanId;
        @SerializedName("recharge_plan_amount")
        @Expose
        public String rechargePlanAmount;
        @SerializedName("recharge_plan_extra_percent")
        @Expose
        public String rechargePlanExtraPercent;
        @SerializedName("recharge_plan_status")
        @Expose
        public String rechargePlanStatus;
        @SerializedName("recharge_plan_cr_date")
        @Expose
        public String rechargePlanCrDate;
        @SerializedName("recharge_plan_up_date")
        @Expose
        public String rechargePlanUpDate;
    }


    public class Response2 {
        @SerializedName("id")
        @Expose
        public String rechargePlanId;
        @SerializedName("recharge_of")
        @Expose
        public String rechargePlanAmount;
        @SerializedName("recharge_get")
        @Expose
        public String rechargePlanExtraPercent;
        @SerializedName("status")
        @Expose
        public String rechargePlanStatus;
    }
}
