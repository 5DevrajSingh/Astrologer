package com.ksbm_astroexpert.ui.search;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.SearchFragmentBinding;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.base.BaseFragment;

public class SearchFragment extends BaseFragment {

    private SearchFragmentBinding binding;
    private APIInterface apiInterface;

    @Override
    protected int getLayout() {
        return R.layout.search_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = (SearchFragmentBinding) getBinding();
        init();
    }

    private void init() {
        apiInterface = ((BaseActivity) mContext).getApiInterface();

    }
}
