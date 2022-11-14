package com.ksbm_astroexpert;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.ksbm_astroexpert.Constant.BaseEnvironment;
import com.ksbm_astroexpert.InternetCheck.ConnectivityReceiver;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.splash.SplashActivity;

public class App extends Application {
    BaseEnvironment baseEnvironment;

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();


        baseEnvironment = BaseEnvironment.PRODUCTION;


        ////////////////////

        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread thread, Throwable e) {
                        handleUncaughtException(thread, e);
                    }
                });

        mInstance = this;


    }


    private void handleUncaughtException(Thread thread, Throwable e) {

        // Add some code logic if needed based on your requirement
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public static synchronized App getInstance() {
        return mInstance;
    }

//    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
//        ConnectivityReceiver.connectivityReceiverListener = listener;
//    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
    public BaseEnvironment getBaseEnvironment() {
        return baseEnvironment;
    }

    public void setBaseEnvironment(BaseEnvironment baseEnvironment) {
        this.baseEnvironment = baseEnvironment;
    }
}
