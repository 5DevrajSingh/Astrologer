package com.ksbm_astroexpert.ui.agentprofile;

import android.content.Intent;
import android.os.Bundle;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.AgentProfileActivityBinding;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.home.ExpertsResponseModel;
import com.ksbm_astroexpert.ui.utils.UserUtils;

public class AgentProfileActivity extends BaseActivity {

    private AgentProfileActivityBinding binding;
    private ExpertsResponseModel.RelatedVendor relatedVendor;

    @Override
    protected int getLayout() {
        return R.layout.agent_profile_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (AgentProfileActivityBinding) getBinding();
        Intent data = getIntent();
        if (data != null) {
            String dataStr = getIntent().getStringExtra("data");
            if (dataStr != null) {
                try {
                    relatedVendor = UserUtils.getObject(dataStr, ExpertsResponseModel.RelatedVendor.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        binding.imgBack.setOnClickListener(v -> onBackPressed());
        if (relatedVendor != null) {
            binding.setRelatedVendor(relatedVendor);
            binding.ctToolbarLayout.setTitle(relatedVendor.getOwnerName());
            binding.imgShare.setOnClickListener(view -> {

            });
        }
    }
}
