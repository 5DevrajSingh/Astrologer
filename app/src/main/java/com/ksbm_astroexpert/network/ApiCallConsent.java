package com.ksbm_astroexpert.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ksbm_astroexpert.network.APIInterface.BASE_URLForRechargeWallet;
import static com.ksbm_astroexpert.network.APIInterface.BASE_URLl;

public interface ApiCallConsent {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URLl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    APIInterface apiInterface = retrofit.create(APIInterface.class);

}

