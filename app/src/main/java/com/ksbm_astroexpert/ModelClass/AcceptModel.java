package com.ksbm_astroexpert.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AcceptModel implements Serializable {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("result")
    @Expose
    public Result result;
    
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
        @SerializedName("call_log_agent_mobile")
        @Expose
        public String callLogAgentMobile;
        @SerializedName("call_log_status")
        @Expose
        public String callLogStatus;
        @SerializedName("status")
        @Expose
        public Object status;
        @SerializedName("url")
        @Expose
        public Object url;
        @SerializedName("call_log_update_at")
        @Expose
        public Object callLogUpdateAt;
        @SerializedName("call_log_pickup_time")
        @Expose
        public String callLogPickupTime;
        @SerializedName("call_log_end_time")
        @Expose
        public Object callLogEndTime;
        @SerializedName("call_log_duration_min")
        @Expose
        public Object callLogDurationMin;
        @SerializedName("call_log_charge_amount")
        @Expose
        public Object callLogChargeAmount;
        @SerializedName("transid")
        @Expose
        public String transid;
        @SerializedName("type")
        @Expose
        public String type;
    }
}
