package com.ksbm_astroexpert.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.signin.SignInResponseModel;

public abstract class BaseFragment extends Fragment {

    private ViewDataBinding binding;
    protected Context mContext;
    protected SignInResponseModel.User loginUser;

    protected abstract int getLayout();

    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        if(mContext instanceof HomeActivity){
            loginUser = ((HomeActivity)mContext).getSessionManger().getUser();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), getLayout(), container, false);
        return binding.getRoot();
    }
}
