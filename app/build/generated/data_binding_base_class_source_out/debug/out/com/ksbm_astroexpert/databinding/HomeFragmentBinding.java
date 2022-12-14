// Generated by data binding compiler. Do not edit!
package com.ksbm_astroexpert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.view.BoldTextView;
import com.ksbm_astroexpert.view.NormalTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class HomeFragmentBinding extends ViewDataBinding {
  @NonNull
  public final BoldTextView bi;

  @NonNull
  public final RecyclerView expertise;

  @NonNull
  public final FrameLayout flBanner;

  @NonNull
  public final ConstraintLayout llOurExperts;

  @NonNull
  public final ConstraintLayout llRemedies;

  @NonNull
  public final ProgressBar loaderBanner;

  @NonNull
  public final ImageView loaderBanner1;

  @NonNull
  public final ProgressBar loaderOurExperts;

  @NonNull
  public final ImageView loaderOurExperts1;

  @NonNull
  public final ProgressBar loaderRemedies;

  @NonNull
  public final ImageView loaderRemedies1;

  @NonNull
  public final SwipeRefreshLayout pullToRefresh;

  @NonNull
  public final RecyclerView recyclerviewastro;

  @NonNull
  public final RecyclerView rvAstroList;

  @NonNull
  public final RecyclerView rvExpertList;

  @NonNull
  public final RecyclerView rvMagicalRemedies;

  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final RecyclerView testimonials;

  @NonNull
  public final NormalTextView txtNoExpertData;

  @NonNull
  public final BoldTextView txtTitleExperts;

  @NonNull
  public final BoldTextView txtTitleRemedies;

  @NonNull
  public final BoldTextView txtViewAllExperts;

  @NonNull
  public final BoldTextView txtViewAllRemedies;

  @NonNull
  public final ViewPager viewPagerBanner;

  protected HomeFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      BoldTextView bi, RecyclerView expertise, FrameLayout flBanner, ConstraintLayout llOurExperts,
      ConstraintLayout llRemedies, ProgressBar loaderBanner, ImageView loaderBanner1,
      ProgressBar loaderOurExperts, ImageView loaderOurExperts1, ProgressBar loaderRemedies,
      ImageView loaderRemedies1, SwipeRefreshLayout pullToRefresh, RecyclerView recyclerviewastro,
      RecyclerView rvAstroList, RecyclerView rvExpertList, RecyclerView rvMagicalRemedies,
      TabLayout tabLayout, RecyclerView testimonials, NormalTextView txtNoExpertData,
      BoldTextView txtTitleExperts, BoldTextView txtTitleRemedies, BoldTextView txtViewAllExperts,
      BoldTextView txtViewAllRemedies, ViewPager viewPagerBanner) {
    super(_bindingComponent, _root, _localFieldCount);
    this.bi = bi;
    this.expertise = expertise;
    this.flBanner = flBanner;
    this.llOurExperts = llOurExperts;
    this.llRemedies = llRemedies;
    this.loaderBanner = loaderBanner;
    this.loaderBanner1 = loaderBanner1;
    this.loaderOurExperts = loaderOurExperts;
    this.loaderOurExperts1 = loaderOurExperts1;
    this.loaderRemedies = loaderRemedies;
    this.loaderRemedies1 = loaderRemedies1;
    this.pullToRefresh = pullToRefresh;
    this.recyclerviewastro = recyclerviewastro;
    this.rvAstroList = rvAstroList;
    this.rvExpertList = rvExpertList;
    this.rvMagicalRemedies = rvMagicalRemedies;
    this.tabLayout = tabLayout;
    this.testimonials = testimonials;
    this.txtNoExpertData = txtNoExpertData;
    this.txtTitleExperts = txtTitleExperts;
    this.txtTitleRemedies = txtTitleRemedies;
    this.txtViewAllExperts = txtViewAllExperts;
    this.txtViewAllRemedies = txtViewAllRemedies;
    this.viewPagerBanner = viewPagerBanner;
  }

  @NonNull
  public static HomeFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_fragment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static HomeFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<HomeFragmentBinding>inflateInternal(inflater, R.layout.home_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static HomeFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_fragment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static HomeFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<HomeFragmentBinding>inflateInternal(inflater, R.layout.home_fragment, null, false, component);
  }

  public static HomeFragmentBinding bind(@NonNull View view) {
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
  public static HomeFragmentBinding bind(@NonNull View view, @Nullable Object component) {
    return (HomeFragmentBinding)bind(component, view, R.layout.home_fragment);
  }
}
