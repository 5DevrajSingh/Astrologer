package com.ksbm_astroexpert.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.HomeScreenSlidePageFragmentBinding;
import com.ksbm_astroexpert.ui.base.BaseFragment;

public class ScreenSliderPageFragment extends BaseFragment {

    private HomeScreenSlidePageFragmentBinding screenSlidePageFragmentBinding;

    @Override
    protected int getLayout() {
        return R.layout.home_screen_slide_page_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        screenSlidePageFragmentBinding = (HomeScreenSlidePageFragmentBinding) getBinding();
        init();
    }

    private void init() {
        if (getArguments() != null) {
            BannerResponseModel.BannerModel bannerModel = getArguments().getParcelable("data");
            if (bannerModel != null) {
                screenSlidePageFragmentBinding.setBannerModel(bannerModel);
                screenSlidePageFragmentBinding.executePendingBindings();
            }

            screenSlidePageFragmentBinding.imgBanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent blogDetailIntent = new Intent(mContext, ViewAllAstrologer.class);
                    startActivity(blogDetailIntent);
                }
            });
//            UserUtils.renderPhoto(url,screenSlidePageFragmentBinding.imgBanner);
        }
    }
}
