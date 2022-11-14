package com.ksbm_astroexpert.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static final String TAG = "APIClient";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        okhttp3.Response response = chain.proceed(request);
                        // todo deal with the issues the way you need to
                        Log.e(TAG,"Response code " + response.code());
                        if (response.code() == 500) {
                            return response;
                        }

                        return response;
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
//    public static String getRequestError(Throwable e){
//        if(e instanceof TimeoutException){
//            return "Slow internet connection";
//        }else if(e instanceof TimeoutException){
//            return "Slow internet connection";
//        }
//    }
}



