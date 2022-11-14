package com.ksbm_astroexpert.network;

import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.ModelClass.AcceptModel;
import com.ksbm_astroexpert.ModelClass.ApplyCouponModel;
import com.ksbm_astroexpert.ModelClass.RechargeCustomerModel;
import com.ksbm_astroexpert.ModelClass.WalletModel;
import com.ksbm_astroexpert.ui.Astrodetails.ModelAstrologerList;
import com.ksbm_astroexpert.ui.blog.BlogsResponseModel;
import com.ksbm_astroexpert.ui.home.BannerResponseModel;
import com.ksbm_astroexpert.ui.home.ExpertsResponseModel;
import com.ksbm_astroexpert.ui.home.RemediesResponseModel;
import com.ksbm_astroexpert.ui.signin.SignInResponseModel;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    public static final String BASE_URLl = RootURL.Base_URL+"/Astroksbmadmin/user/";

    public static final String BASE_URLForRechargeWallet = RootURL.Base_URL+"/Astroksbmadmin/api/api/";
    @GET("get_banner.php")
    Single<BannerResponseModel> getBanners();

    @GET("remedies.php")
    Single<RemediesResponseModel> getRemedies();



    @GET("api/api/astrodetails_list")
    Single<ModelAstrologerList>astrodetails_list();


//    @FormUrlEncoded
//    @POST("login.php")
//    Call<SignInResponseModel> login(@Field("phone") String phoneNumber);
    @FormUrlEncoded
    @POST("login.php")
    Single<SignInResponseModel> login(@Field("phone") String phoneNumber);


    @FormUrlEncoded
    @POST("verifyCoupon.php")
     Call<ApplyCouponModel> postCoupon(@Field("coupons_code") String coupon_code);


    @GET("recharge_details.php")
    Call<WalletModel> getRchage();


    @FormUrlEncoded
    @POST("recharge_by_customer.php")
    Call<RechargeCustomerModel> postRechageAmt(@Field("user_id") String id, @Field("amount") String amount, @Field("transid") String transid);


    @FormUrlEncoded
    @POST("getRechargeplans")
    Call<WalletModel> postRechargePlan(@Field("user_id") String id);



    @FormUrlEncoded
    @POST("regi.php")
    Call<SignInResponseModel> signUp(@Field("phone") String phoneNumber,@Field("name") String name,@Field("clientId") String clientId);

    @GET("our_experts.php")
    Single<ExpertsResponseModel> getExperts();

    @GET("api/api/all_blog")
    Call<BlogsResponseModel> getBlogs();

    @FormUrlEncoded
    @POST("expert_filter.php")
    Call<ExpertsResponseModel> getExpertFilter(@Field("rating") String rating,@Field("price") String price);
}