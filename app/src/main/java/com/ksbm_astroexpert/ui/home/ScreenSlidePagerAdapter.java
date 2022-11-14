package com.ksbm_astroexpert.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {


    private List<BannerResponseModel.BannerModel> items = null;


    ScreenSlidePagerAdapter(FragmentManager fm, List<BannerResponseModel.BannerModel> items) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.items = items;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        if (position == 0) {
//            return new ScreenSliderPageFragment();
//        }
        ScreenSliderPageFragment screenSliderPageFragment = new ScreenSliderPageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", items.get(position));
        screenSliderPageFragment.setArguments(bundle);
        return screenSliderPageFragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }
}

