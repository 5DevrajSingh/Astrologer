package com.ksbm_astroexpert.ui.contactus;

import android.os.Bundle;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.ContactUsActivityBinding;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.base.BaseProgressRecyclerListAdapter;

public class ContactUsActivity extends BaseActivity {

    private ContactUsActivityBinding binding;
    private BaseProgressRecyclerListAdapter baseRecyclerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.contact_us_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ContactUsActivityBinding) getBinding();
        init();
    }

    private void init() {
        actionBar(binding.actionbar.toolbar , R.drawable.ic_arrow_back, false, true, true,true);
        binding.actionbar.txtTitle.setText(R.string.lbl_contact_us);
    }
}
