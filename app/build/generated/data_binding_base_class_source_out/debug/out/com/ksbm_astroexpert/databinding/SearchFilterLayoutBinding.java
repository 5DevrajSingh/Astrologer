// Generated by data binding compiler. Do not edit!
package com.ksbm_astroexpert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.innovattic.rangeseekbar.RangeSeekBar;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.view.NormalTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class SearchFilterLayoutBinding extends ViewDataBinding {
  @NonNull
  public final MaterialButton btnFilter;

  @NonNull
  public final CardView cvPricing;

  @NonNull
  public final CardView cvRating;

  @NonNull
  public final RangeSeekBar rangeSeekBar;

  @NonNull
  public final NormalTextView txt3Plus;

  @NonNull
  public final NormalTextView txt5Start;

  @NonNull
  public final NormalTextView txtAllRating;

  @NonNull
  public final NormalTextView txtMaxPrice;

  @NonNull
  public final NormalTextView txtMinPrice;

  @NonNull
  public final NormalTextView txtPricing;

  @NonNull
  public final NormalTextView txtRating;

  protected SearchFilterLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount,
      MaterialButton btnFilter, CardView cvPricing, CardView cvRating, RangeSeekBar rangeSeekBar,
      NormalTextView txt3Plus, NormalTextView txt5Start, NormalTextView txtAllRating,
      NormalTextView txtMaxPrice, NormalTextView txtMinPrice, NormalTextView txtPricing,
      NormalTextView txtRating) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnFilter = btnFilter;
    this.cvPricing = cvPricing;
    this.cvRating = cvRating;
    this.rangeSeekBar = rangeSeekBar;
    this.txt3Plus = txt3Plus;
    this.txt5Start = txt5Start;
    this.txtAllRating = txtAllRating;
    this.txtMaxPrice = txtMaxPrice;
    this.txtMinPrice = txtMinPrice;
    this.txtPricing = txtPricing;
    this.txtRating = txtRating;
  }

  @NonNull
  public static SearchFilterLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.search_filter_layout, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static SearchFilterLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<SearchFilterLayoutBinding>inflateInternal(inflater, R.layout.search_filter_layout, root, attachToRoot, component);
  }

  @NonNull
  public static SearchFilterLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.search_filter_layout, null, false, component)
   */
  @NonNull
  @Deprecated
  public static SearchFilterLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<SearchFilterLayoutBinding>inflateInternal(inflater, R.layout.search_filter_layout, null, false, component);
  }

  public static SearchFilterLayoutBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static SearchFilterLayoutBinding bind(@NonNull View view, @Nullable Object component) {
    return (SearchFilterLayoutBinding)bind(component, view, R.layout.search_filter_layout);
  }
}
