package com.ksbm_astroexpert.ui.blog;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogsResponseModel {

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
    private List<BlogModel> data = null;

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

    public List<BlogModel> getData() {
        return data;
    }

    public void setData(List<BlogModel> data) {
        this.data = data;
    }

    public static class BlogModel implements Parcelable {

        @SerializedName("blog_id")
        @Expose
        private String blogId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("blog_icon")
        @Expose
        private String blogIcon;

        protected BlogModel(Parcel in) {
            blogId = in.readString();
            title = in.readString();
            description = in.readString();
            icon = in.readString();
            blogIcon = in.readString();
        }

        public static final Creator<BlogModel> CREATOR = new Creator<BlogModel>() {
            @Override
            public BlogModel createFromParcel(Parcel in) {
                return new BlogModel(in);
            }

            @Override
            public BlogModel[] newArray(int size) {
                return new BlogModel[size];
            }
        };

        public String getBlogId() {
            return blogId;
        }

        public void setBlogId(String blogId) {
            this.blogId = blogId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getBlogIcon() {
            return blogIcon;
        }

        public void setBlogIcon(String blogIcon) {
            this.blogIcon = blogIcon;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(blogId);
            dest.writeString(title);
            dest.writeString(description);
            dest.writeString(icon);
            dest.writeString(blogIcon);
        }
    }


}
