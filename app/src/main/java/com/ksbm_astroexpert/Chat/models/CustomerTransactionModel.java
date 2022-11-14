package com.ksbm_astroexpert.Chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerTransactionModel implements Serializable {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("response")
    @Expose
    public ArrayList<Response> response = null;
    @SerializedName("dataCalling")
    @Expose
    public ArrayList<DataCalling> dataCalling = null;

    public class Response {

        @SerializedName("recharge_plan_id")
        @Expose
        public String rechargePlanId;
        @SerializedName("user_id")
        @Expose
        public String userId;

        @SerializedName("cramount")
        @Expose
        public String cramount;
        @SerializedName("dramount")
        @Expose
        public String dramount;
        @SerializedName("trans_id")
        @Expose
        public String trans_id;
        @SerializedName("bal_type")
        @Expose
        public String bal_type;
        @SerializedName("recharge_plan_amount")
        @Expose
        public String rechargePlanAmount;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("payment_status")
        @Expose
        public String paymentStatus;
        @SerializedName("p_id")
        @Expose
        public String pId;
        @SerializedName("online_payment_status")
        @Expose
        public String onlinePaymentStatus;
        @SerializedName("transdate")
        @Expose
        public String transcationDate;
        @SerializedName("recharge_plan_status")
        @Expose
        public String rechargePlanStatus;
        @SerializedName("recharge_plan_cr_date")
        @Expose
        public String rechargePlanCrDate;
        @SerializedName("description")
        @Expose
        public String description;
    }


    public class DataCalling {

        @SerializedName("call_log_update_at")
        @Expose
        public String callLogUpdateAt;
        @SerializedName("call_log_pickup_time")
        @Expose
        public String callLogPickupTime;
        @SerializedName("call_log_end_time")
        @Expose
        public Object callLogEndTime;
        @SerializedName("call_log_duration_min")
        @Expose
        public String callLogDurationMin;
        @SerializedName("call_log_charge_amount")
        @Expose
        public String callLogChargeAmount;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("owner_name")
        @Expose
        public String ownerName;
    }


}
