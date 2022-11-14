package com.ksbm_astroexpert.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class RemediesResponseModel {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<RemediesModel> data = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RemediesModel> getData() {
        return data;
    }

    public void setData(List<RemediesModel> data) {
        this.data = data;
    }

    public static class RemediesModel implements Parcelable {

        @SerializedName("remedies_id")
        @Expose
        private String remediesId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("icon")
        @Expose
        private String icon;


        @SerializedName("icon2")
        @Expose
        private String icon2;

        @SerializedName("content")
        @Expose
        private String content;

        protected RemediesModel(Parcel in) {
            remediesId = in.readString();
            title = in.readString();
            icon = in.readString();
            icon2 = in.readString();
            content = in.readString();
        }


        public static final Creator<RemediesResponseModel.RemediesModel> CREATOR = new Creator<RemediesResponseModel.RemediesModel>() {
            @Override
            public RemediesResponseModel.RemediesModel createFromParcel(Parcel in) {
                return new RemediesResponseModel.RemediesModel(in);
            }

            @Override
            public RemediesResponseModel.RemediesModel[] newArray(int size) {
                return new RemediesResponseModel.RemediesModel[size];
            }
        };

        public String getRemediesId() {
            return remediesId;
        }

        public void setRemediesId(String remediesId) {
            this.remediesId = remediesId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon2() {
            return icon2;
        }

        public void setIcon2(String icon2) {
            this.icon2 = icon2;
        }


        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }



        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public int describeContents() {
            return 0;
        }


        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(remediesId);
            dest.writeString(title);
            dest.writeString(icon2);
            dest.writeString(icon);
            dest.writeString(content);
        }

        /*@Override
        public int describeContents() {
            return 0;
        }*/

      /*  @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(icon2);
            dest.writeString(title);
            dest.writeString(content);
            dest.writeString(icon);
            dest.writeString(remediesId);
        }*/
    }

    public boolean hasList(){
       return  (getData() != null && !getData().isEmpty());
    }
}
