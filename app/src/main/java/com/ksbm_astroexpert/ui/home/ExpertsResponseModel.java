package com.ksbm_astroexpert.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpertsResponseModel {
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
    private List<Datum> data = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


    public class Datum {

        @SerializedName("related_vendor")
        @Expose
        private List<RelatedVendor> relatedVendor = null;

        public List<RelatedVendor> getRelatedVendor() {
            return relatedVendor;
        }

        public void setRelatedVendor(List<RelatedVendor> relatedVendor) {
            this.relatedVendor = relatedVendor;
        }

    }


    public static class RelatedVendor implements Parcelable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("owner_name")
        @Expose
        private String ownerName;
        @SerializedName("shop_name")
        @Expose
        private String shopName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("opening")
        @Expose
        private Object opening;
        @SerializedName("closing")
        @Expose
        private Object closing;
        @SerializedName("main_cat")
        @Expose
        private Object mainCat;
        @SerializedName("sub_cat")
        @Expose
        private Object subCat;
        @SerializedName("child_cat")
        @Expose
        private Object childCat;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lon")
        @Expose
        private String lon;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("img_url")
        @Expose
        private String imgUrl;
        @SerializedName("account")
        @Expose
        private Object account;
        @SerializedName("paytm")
        @Expose
        private Object paytm;
        @SerializedName("google_pay")
        @Expose
        private Object googlePay;
        @SerializedName("phone_pay")
        @Expose
        private Object phonePay;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("activate")
        @Expose
        private String activate;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("starting_price")
        @Expose
        private String startingPrice;
        @SerializedName("avg_rating")
        @Expose
        private String avgRating;
        @SerializedName("exp")
        @Expose
        private String exp;
        @SerializedName("current_status")
        @Expose
        private String currentStatus;
        @SerializedName("seen")
        @Expose
        private String seen;
        @SerializedName("vendor_image")
        @Expose
        private String vendorImage;
        @SerializedName("vendor_cat_details_image")
        @Expose
        private String vendorCatDetailsImage;
        @SerializedName("vendor_cat_details_id")
        @Expose
        private String vendorCatDetailsId;
        @SerializedName("child_cat_name")
        @Expose
        private Object childCatName;
        @SerializedName("listing_id")
        @Expose
        private String listingId;
        @SerializedName("vendor_rating_avg")
        @Expose
        private List<Object> vendorRatingAvg = null;

        protected RelatedVendor(Parcel in) {
            id = in.readString();
            ownerName = in.readString();
            shopName = in.readString();
            email = in.readString();
            password = in.readString();
            address = in.readString();
            phone = in.readString();
            city = in.readString();
            lat = in.readString();
            lon = in.readString();
            status = in.readString();
            imgUrl = in.readString();
            state = in.readString();
            activate = in.readString();
            gender = in.readString();
            startingPrice = in.readString();
            avgRating = in.readString();
            exp = in.readString();
            currentStatus = in.readString();
            seen = in.readString();
            vendorImage = in.readString();
            vendorCatDetailsImage = in.readString();
            vendorCatDetailsId = in.readString();
            listingId = in.readString();
        }

        public final Creator<RelatedVendor> CREATOR = new Creator<RelatedVendor>() {
            @Override
            public RelatedVendor createFromParcel(Parcel in) {
                return new RelatedVendor(in);
            }

            @Override
            public RelatedVendor[] newArray(int size) {
                return new RelatedVendor[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getOpening() {
            return opening;
        }

        public void setOpening(Object opening) {
            this.opening = opening;
        }

        public Object getClosing() {
            return closing;
        }

        public void setClosing(Object closing) {
            this.closing = closing;
        }

        public Object getMainCat() {
            return mainCat;
        }

        public void setMainCat(Object mainCat) {
            this.mainCat = mainCat;
        }

        public Object getSubCat() {
            return subCat;
        }

        public void setSubCat(Object subCat) {
            this.subCat = subCat;
        }

        public Object getChildCat() {
            return childCat;
        }

        public void setChildCat(Object childCat) {
            this.childCat = childCat;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Object getAccount() {
            return account;
        }

        public void setAccount(Object account) {
            this.account = account;
        }

        public Object getPaytm() {
            return paytm;
        }

        public void setPaytm(Object paytm) {
            this.paytm = paytm;
        }

        public Object getGooglePay() {
            return googlePay;
        }

        public void setGooglePay(Object googlePay) {
            this.googlePay = googlePay;
        }

        public Object getPhonePay() {
            return phonePay;
        }

        public void setPhonePay(Object phonePay) {
            this.phonePay = phonePay;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getActivate() {
            return activate;
        }

        public void setActivate(String activate) {
            this.activate = activate;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getStartingPrice() {
            return startingPrice;
        }

        public void setStartingPrice(String startingPrice) {
            this.startingPrice = startingPrice;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public String getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(String currentStatus) {
            this.currentStatus = currentStatus;
        }

        public String getSeen() {
            return seen;
        }

        public void setSeen(String seen) {
            this.seen = seen;
        }

        public String getVendorImage() {
            return vendorImage;
        }

        public void setVendorImage(String vendorImage) {
            this.vendorImage = vendorImage;
        }

        public String getVendorCatDetailsImage() {
            return vendorCatDetailsImage;
        }

        public void setVendorCatDetailsImage(String vendorCatDetailsImage) {
            this.vendorCatDetailsImage = vendorCatDetailsImage;
        }

        public String getVendorCatDetailsId() {
            return vendorCatDetailsId;
        }

        public void setVendorCatDetailsId(String vendorCatDetailsId) {
            this.vendorCatDetailsId = vendorCatDetailsId;
        }

        public Object getChildCatName() {
            return childCatName;
        }

        public void setChildCatName(Object childCatName) {
            this.childCatName = childCatName;
        }

        public String getListingId() {
            return listingId;
        }

        public void setListingId(String listingId) {
            this.listingId = listingId;
        }

        public List<Object> getVendorRatingAvg() {
            return vendorRatingAvg;
        }

        public void setVendorRatingAvg(List<Object> vendorRatingAvg) {
            this.vendorRatingAvg = vendorRatingAvg;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(ownerName);
            dest.writeString(shopName);
            dest.writeString(email);
            dest.writeString(password);
            dest.writeString(address);
            dest.writeString(phone);
            dest.writeString(city);
            dest.writeString(lat);
            dest.writeString(lon);
            dest.writeString(status);
            dest.writeString(imgUrl);
            dest.writeString(state);
            dest.writeString(activate);
            dest.writeString(gender);
            dest.writeString(startingPrice);
            dest.writeString(avgRating);
            dest.writeString(exp);
            dest.writeString(currentStatus);
            dest.writeString(seen);
            dest.writeString(vendorImage);
            dest.writeString(vendorCatDetailsImage);
            dest.writeString(vendorCatDetailsId);
            dest.writeString(listingId);
        }
    }
}