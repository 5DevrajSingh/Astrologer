package com.ksbm_astroexpert.Chat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderHistroyModel implements Serializable {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("result")
    @Expose
    public ArrayList<Result> result = null;

    public class Result {

        @SerializedName("call_log_id")
        @Expose
        public String callLogId;
        @SerializedName("call_log_cr_date")
        @Expose
        public String callLogCrDate;
        @SerializedName("call_log_call_id")
        @Expose
        public String callLogCallId;
        @SerializedName("call_log_user_id")
        @Expose
        public String callLogUserId;
        @SerializedName("call_log_astrologer_user_id")
        @Expose
        public String callLogAstrologerUserId;
        @SerializedName("astrologer_name")
        @Expose
        public String astrologerName;
        @SerializedName("call_log_agent_mobile")
        @Expose
        public String callLogAgentMobile;
        @SerializedName("call_log_status")
        @Expose
        public String callLogStatus;
        @SerializedName("call_log_update_at")
        @Expose
        public String callLogUpdateAt;
        @SerializedName("invoiceId")
        @Expose
        public String invoiceId;
        @SerializedName("call_log_pickup_time")
        @Expose
        public String callLogPickupTime;
        @SerializedName("call_log_end_time")
        @Expose
        public String callLogEndTime;
        @SerializedName("call_log_duration_min")
        @Expose
        public String callLogDurationMin;
        @SerializedName("call_log_charge_amount")
        @Expose
        public String callLogChargeAmount;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("owner_name")
        @Expose
        public String ownerName;
        @SerializedName("address")
        @Expose
        public String address;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("pincode")
        @Expose
        public String pincode;
        @SerializedName("experience")
        @Expose
        public String experience;
        @SerializedName("working_ex")
        @Expose
        public String workingEx;
        @SerializedName("call_price_m")
        @Expose
        public String callPriceM;
        @SerializedName("chat_price_m")
        @Expose
        public String chatPriceM;
        @SerializedName("chat_commission")
        @Expose
        public String chatCommission;
        @SerializedName("call_commission")
        @Expose
        public String callCommission;
        @SerializedName("device_id")
        @Expose
        public String deviceId;
        @SerializedName("device_token")
        @Expose
        public String deviceToken;
        @SerializedName("token")
        @Expose
        public String token;
        @SerializedName("current_status")
        @Expose
        public String currentStatus;
        @SerializedName("current_status_call")
        @Expose
        public String currentStatusCall;
        @SerializedName("seen")
        @Expose
        public String seen;
        @SerializedName("wait_time")
        @Expose
        public String waitTime;
        @SerializedName("wait_time_call")
        @Expose
        public String waitTimeCall;
        @SerializedName("call_randval")
        @Expose
        public String callRandval;
        @SerializedName("report_randval")
        @Expose
        public String reportRandval;
        @SerializedName("comment_randval")
        @Expose
        public String commentRandval;

        @SerializedName("img_url")
        @Expose
        public String imgUrl;
    }
}
