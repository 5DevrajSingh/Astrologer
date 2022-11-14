package com.ksbm_astroexpert.ui.search;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.SearchFilterLayoutBinding;
import com.ksbm_astroexpert.ui.base.BaseBottomSheetDialogFragment;
import com.ksbm_astroexpert.ui.utils.UserConstants;
import com.innovattic.rangeseekbar.RangeSeekBar;

public class FilterBottomSheetDialogFragment extends BaseBottomSheetDialogFragment {

    private SearchFilterLayoutBinding binding;
    private FilterModel filterModel;
    private int minValue = 0;
    private int maxValue = 0;
    private int ratingType = 0;

    @Override
    protected int getLayout() {
        return R.layout.search_filter_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = (SearchFilterLayoutBinding) getBinding();
        init();
    }

    private void init() {
        minValue = filterModel.getMinPrice();
        maxValue = filterModel.getMaxPrice();
        ratingType = filterModel.getRatingType();
        setRatingView();
        setPricingView();
        binding.btnFilter.setOnClickListener(v -> {
            filterModel.setMinPrice(minValue);
            filterModel.setMaxPrice(maxValue);
            filterModel.setRatingType(ratingType);
            dismissListener.onDismiss(filterModel);
            dismiss();
        });
        binding.rangeSeekBar.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
            @Override
            public void onStartedSeeking() {

            }

            @Override
            public void onStoppedSeeking() {

            }

            @Override
            public void onValueChanged(int i, int i1) {
                binding.txtMinPrice.setText(UserConstants.RUPEES + i);
                binding.txtMaxPrice.setText(UserConstants.RUPEES + i);
                minValue = i;
                maxValue = i;
            }
        });
        binding.txtAllRating.setOnClickListener(v -> {
            if (filterModel.getRatingType() != 1) {
                filterModel.setRatingType(1);
                setRatingView();
            }
        });
        binding.txt3Plus.setOnClickListener(v -> {
            if (filterModel.getRatingType() != 2) {
                filterModel.setRatingType(2);
                setRatingView();
            }
        });
        binding.txt5Start.setOnClickListener(v -> {
            if (filterModel.getRatingType() != 3) {
                filterModel.setRatingType(3);
                setRatingView();
            }
        });
    }

    private void setPricingView() {

        binding.rangeSeekBar.setMinRange(filterModel.getMinPrice());
        binding.rangeSeekBar.setMax(filterModel.getMaxPrice());
        binding.txtMinPrice.setText(UserConstants.RUPEES + filterModel.getMinPrice());
        binding.txtMaxPrice.setText(UserConstants.RUPEES + filterModel.getMaxPrice());
    }

    private void setRatingView() {
        binding.txtAllRating.setBackgroundResource(0);
        binding.txt3Plus.setBackgroundResource(0);
        binding.txt5Start.setBackgroundResource(0);
        binding.txtAllRating.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        binding.txt3Plus.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        binding.txt5Start.setTextColor(ContextCompat.getColor(mContext, R.color.black));

        if (filterModel.getRatingType() == 1) {
            binding.txtAllRating.setBackgroundResource(R.drawable.selected_rating_filter_bg);
            binding.txtAllRating.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else if (filterModel.getRatingType() == 2) {
            binding.txt3Plus.setBackgroundResource(R.drawable.selected_rating_filter_bg);
            binding.txt3Plus.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else if (filterModel.getRatingType() == 3) {
            binding.txt5Start.setBackgroundResource(R.drawable.selected_rating_filter_bg);
            binding.txt5Start.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }

    }

    public void setFilterModel(FilterModel filterModel) {
        this.filterModel = filterModel;
    }
}
