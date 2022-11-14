package com.ksbm_astroexpert.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerResponseModel {

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
    private List<BannerModel> data = null;

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

    public List<BannerModel> getData() {
        return data;
    }

    public void setData(List<BannerModel> data) {
        this.data = data;
    }


    public static class BannerModel implements Parcelable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("banner")
        @Expose
        private String banner;
        @SerializedName("sub_cat_img")
        @Expose
        private String subCatImg;

        protected BannerModel(Parcel in) {
            id = in.readString();
            image = in.readString();
            link = in.readString();
            banner = in.readString();
            subCatImg = in.readString();
        }

        public static final Creator<BannerModel> CREATOR = new Creator<BannerModel>() {
            @Override
            public BannerModel createFromParcel(Parcel in) {
                return new BannerModel(in);
            }

            @Override
            public BannerModel[] newArray(int size) {
                return new BannerModel[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getSubCatImg() {
            return subCatImg;
        }

        public void setSubCatImg(String subCatImg) {
            this.subCatImg = subCatImg;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(image);
            dest.writeString(link);
            dest.writeString(banner);
            dest.writeString(subCatImg);
        }
    }
}