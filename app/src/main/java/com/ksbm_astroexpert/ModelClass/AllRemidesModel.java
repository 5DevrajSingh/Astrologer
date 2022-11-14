package com.ksbm_astroexpert.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AllRemidesModel implements Serializable {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("response")
    @Expose
    public ArrayList<Response> response = null;

    public class Response {

        @SerializedName("icon_img_url")
        @Expose
        public String iconImgUrl;
        @SerializedName("icon2_img_url")
        @Expose
        public String icon2ImgUrl;
    }
}
