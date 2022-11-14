package com.ksbm_astroexpert.ui.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.ksbm_astroexpert.network.APIClient;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.utils.SessionManger;
import com.ksbm_astroexpert.ui.utils.UserConstants;

public abstract class BaseActivity extends AppCompatActivity {

    private ViewDataBinding binding;
    private SessionManger sessionManger;

    protected abstract int getLayout();

    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayout());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void actionBar(Toolbar toolbar, int drawableResId, boolean displayShowTitle, boolean displayHomeAsUpEnable, boolean displayHomeShowHome,boolean isBackPressFinish) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(this, drawableResId));
            getSupportActionBar().setDisplayShowTitleEnabled(displayShowTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnable);
            getSupportActionBar().setDisplayShowHomeEnabled(displayHomeShowHome);
        }
        if(isBackPressFinish){
            toolbar.setNavigationOnClickListener(view -> onBackPressed());
        }
    }

    public APIInterface getApiInterface() {
        return APIClient.getClient(UserConstants.BASE_URL).create(APIInterface.class);
    }


    public APIInterface getApiInterface1() {
        return APIClient.getClient(UserConstants.BASE_URL2).create(APIInterface.class);
    }


    public SessionManger getSessionManger() {
        if(sessionManger == null){
            sessionManger = new SessionManger(this);
        }
        return sessionManger;
    }
}
