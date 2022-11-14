package com.ksbm_astroexpert.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ksbm_astroexpert.network.APIInterface.BASE_URLForRechargeWallet;

public interface ApiCallConsent2 {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URLForRechargeWallet)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    APIInterface apiInterface2 = retrofit.create(APIInterface.class);
}