package com.ksbm_astroexpert.ui.about;

import android.os.Bundle;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.AboutActivityBinding;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.base.BaseProgressRecyclerListAdapter;

public class AboutActivity extends BaseActivity {

    private AboutActivityBinding aboutActivityBinding;
    private BaseProgressRecyclerListAdapter baseRecyclerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.about_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutActivityBinding = (AboutActivityBinding) getBinding();
        init();
    }

    private void init() {
        actionBar(aboutActivityBinding.actionbar.toolbar , R.drawable.ic_arrow_back, false, true, true,true);
        aboutActivityBinding.actionbar.txtTitle.setText(R.string.lbl_about_astrology);
    }
}
