
package com.ksbm_astroexpert.ui.Astrodetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelAstrologerList {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("records")
    @Expose
    private List<Record> records = null;
    @SerializedName("counts")
    @Expose
    private Integer counts;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

public class Record {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("status")
    @Expose
    private String status;
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
    @SerializedName("alternate_mobile")
    @Expose
    private String alternateMobile;
    @SerializedName("currency")
    @Expose
    private String currency;
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
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("id_proof")
    @Expose
    private String idProof;
    @SerializedName("bank_proof")
    @Expose
    private String bankProof;
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
    @SerializedName("skill")
    @Expose
    private String skill;
    @SerializedName("remedies")
    @Expose
    private Object remedies;
    @SerializedName("subskill")
    @Expose
    private String subskill;
    @SerializedName("experties")
    @Expose
    private String experties;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("working_ex")
    @Expose
    private Object workingEx;
    @SerializedName("ctc")
    @Expose
    private String ctc;
    @SerializedName("portalName")
    @Expose
    private String portalName;
    @SerializedName("bank_account_number")
    @Expose
    private String bankAccountNumber;
    @SerializedName("accountType")
    @Expose
    private String accountType;
    @SerializedName("ifscCode")
    @Expose
    private String ifscCode;
    @SerializedName("bankAccountName")
    @Expose
    private String bankAccountName;
    @SerializedName("panCardNo")
    @Expose
    private String panCardNo;
    @SerializedName("aadharCardNumber")
    @Expose
    private String aadharCardNumber;
    @SerializedName("call_price_m")
    @Expose
    private String callPriceM;
    @SerializedName("chat_price_m")
    @Expose
    private String chatPriceM;
    @SerializedName("chat_commission")
    @Expose
    private String chatCommission;
    @SerializedName("call_commission")
    @Expose
    private String callCommission;
    @SerializedName("comm_desc")
    @Expose
    private Object commDesc;
    @SerializedName("short_bio")
    @Expose
    private String shortBio;
    @SerializedName("long_bio")
    @Expose
    private String longBio;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("token")
    @Expose
    private Object token;
    @SerializedName("current_status")
    @Expose
    private String currentStatus;
    @SerializedName("current_status_call")
    @Expose
    private String currentStatusCall;
    @SerializedName("seen")
    @Expose
    private String seen;
    @SerializedName("wait_time")
    @Expose
    private Object waitTime;
    @SerializedName("wait_time_call")
    @Expose
    private Object waitTimeCall;
    @SerializedName("call_randval")
    @Expose
    private Object callRandval;
    @SerializedName("report_randval")
    @Expose
    private Object reportRandval;
    @SerializedName("comment_randval")
    @Expose
    private Object commentRandval;
    @SerializedName("astro_device_token")
    @Expose
    private Object astroDeviceToken;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("chat_token")
    @Expose
    private Object chatToken;

    @SerializedName("rating")
    @Expose
    private List<Rating> rating = null;

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAlternateMobile() {
        return alternateMobile;
    }

    public void setAlternateMobile(String alternateMobile) {
        this.alternateMobile = alternateMobile;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getBankProof() {
        return bankProof;
    }

    public void setBankProof(String bankProof) {
        this.bankProof = bankProof;
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

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Object getRemedies() {
        return remedies;
    }

    public void setRemedies(Object remedies) {
        this.remedies = remedies;
    }

    public String getSubskill() {
        return subskill;
    }

    public void setSubskill(String subskill) {
        this.subskill = subskill;
    }

    public String getExperties() {
        return experties;
    }

    public void setExperties(String experties) {
        this.experties = experties;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Object getWorkingEx() {
        return workingEx;
    }

    public void setWorkingEx(Object workingEx) {
        this.workingEx = workingEx;
    }

    public String getCtc() {
        return ctc;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public String getPortalName() {
        return portalName;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getPanCardNo() {
        return panCardNo;
    }

    public void setPanCardNo(String panCardNo) {
        this.panCardNo = panCardNo;
    }

    public String getAadharCardNumber() {
        return aadharCardNumber;
    }

    public void setAadharCardNumber(String aadharCardNumber) {
        this.aadharCardNumber = aadharCardNumber;
    }

    public String getCallPriceM() {
        return callPriceM;
    }

    public void setCallPriceM(String callPriceM) {
        this.callPriceM = callPriceM;
    }

    public String getChatPriceM() {
        return chatPriceM;
    }

    public void setChatPriceM(String chatPriceM) {
        this.chatPriceM = chatPriceM;
    }

    public String getChatCommission() {
        return chatCommission;
    }

    public void setChatCommission(String chatCommission) {
        this.chatCommission = chatCommission;
    }

    public String getCallCommission() {
        return callCommission;
    }

    public void setCallCommission(String callCommission) {
        this.callCommission = callCommission;
    }

    public Object getCommDesc() {
        return commDesc;
    }

    public void setCommDesc(Object commDesc) {
        this.commDesc = commDesc;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public String getLongBio() {
        return longBio;
    }

    public void setLongBio(String longBio) {
        this.longBio = longBio;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCurrentStatusCall() {
        return currentStatusCall;
    }

    public void setCurrentStatusCall(String currentStatusCall) {
        this.currentStatusCall = currentStatusCall;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public Object getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Object waitTime) {
        this.waitTime = waitTime;
    }

    public Object getWaitTimeCall() {
        return waitTimeCall;
    }

    public void setWaitTimeCall(Object waitTimeCall) {
        this.waitTimeCall = waitTimeCall;
    }

    public Object getCallRandval() {
        return callRandval;
    }

    public void setCallRandval(Object callRandval) {
        this.callRandval = callRandval;
    }

    public Object getReportRandval() {
        return reportRandval;
    }

    public void setReportRandval(Object reportRandval) {
        this.reportRandval = reportRandval;
    }

    public Object getCommentRandval() {
        return commentRandval;
    }

    public void setCommentRandval(Object commentRandval) {
        this.commentRandval = commentRandval;
    }

    public Object getAstroDeviceToken() {
        return astroDeviceToken;
    }

    public void setAstroDeviceToken(Object astroDeviceToken) {
        this.astroDeviceToken = astroDeviceToken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getChatToken() {
        return chatToken;
    }

    public void setChatToken(Object chatToken) {
        this.chatToken = chatToken;
    }

}



    public class Rating {

        @SerializedName("rating_id")
        @Expose
        private String ratingId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("vendor_id")
        @Expose
        private String vendorId;
        @SerializedName("listing_id")
        @Expose
        private String listingId;
        @SerializedName("child_cat_id")
        @Expose
        private String childCatId;
        @SerializedName("star")
        @Expose
        private String star;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("date")
        @Expose
        private String date;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @SerializedName("username")
        @Expose
        private String username;


        public String getRatingId() {
            return ratingId;
        }

        public void setRatingId(String ratingId) {
            this.ratingId = ratingId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVendorId() {
            return vendorId;
        }

        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
        }

        public String getListingId() {
            return listingId;
        }

        public void setListingId(String listingId) {
            this.listingId = listingId;
        }

        public String getChildCatId() {
            return childCatId;
        }

        public void setChildCatId(String childCatId) {
            this.childCatId = childCatId;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

    }



}
