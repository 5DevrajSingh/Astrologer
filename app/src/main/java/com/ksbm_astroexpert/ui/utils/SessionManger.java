package com.ksbm_astroexpert.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ksbm_astroexpert.ui.signin.SignInResponseModel;

public class SessionManger {

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    public SessionManger(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUser(SignInResponseModel.User user) {
        editor.putString("user", UserUtils.getString(user));
        editor.commit();
        editor.apply();
    }

    public void logout(){
        editor.clear();
        editor.apply();
    }

    public SignInResponseModel.User getUser() {
        try {
            if (sharedPreferences.contains("user")) {
                return UserUtils.getObject(sharedPreferences.getString("user", ""), SignInResponseModel.User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
