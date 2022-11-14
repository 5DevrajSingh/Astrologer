package com.ksbm_astroexpert.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OnlineRechargeModel implements Serializable {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("response")
    @Expose
    public String response;
    @SerializedName("transid")
    @Expose
    public String transid;

}
