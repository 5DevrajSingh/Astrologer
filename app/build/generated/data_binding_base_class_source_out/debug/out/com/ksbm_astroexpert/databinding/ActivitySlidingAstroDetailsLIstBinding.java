// Generated by data binding compiler. Do not edit!
package com.ksbm_astroexpert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.view.BoldTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivitySlidingAstroDetailsLIstBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final RelativeLayout callbtn;

  @NonNull
  public final TextView callme;

  @NonNull
  public final LinearLayout callme1;

  @NonNull
  public final ImageView callmed;

  @NonNull
  public final TextView callprice;

  @NonNull
  public final RelativeLayout chatbtn;

  @NonNull
  public final TextView chatme;

  @NonNull
  public final LinearLayout chatme1;

  @NonNull
  public final ImageView chatmed;

  @NonNull
  public final TextView chatprice;

  @NonNull
  public final AppCompatImageView imgSupport;

  @NonNull
  public final LinearLayout llwallet;

  @NonNull
  public final LinearLayout lybutton;

  @NonNull
  public final RelativeLayout mainContent;

  @NonNull
  public final TextView permin;

  @NonNull
  public final TextView perminchat;

  @NonNull
  public final RecyclerView recyclerview;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final BoldTextView txtBalance;

  protected ActivitySlidingAstroDetailsLIstBinding(Object _bindingComponent, View _root,
      int _localFieldCount, AppBarLayout appbar, ImageButton backBtn, RelativeLayout callbtn,
      TextView callme, LinearLayout callme1, ImageView callmed, TextView callprice,
      RelativeLayout chatbtn, TextView chatme, LinearLayout chatme1, ImageView chatmed,
      TextView chatprice, AppCompatImageView imgSupport, LinearLayout llwallet,
      LinearLayout lybutton, RelativeLayout mainContent, TextView permin, TextView perminchat,
      RecyclerView recyclerview, Toolbar toolbar, BoldTextView txtBalance) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appbar = appbar;
    this.backBtn = backBtn;
    this.callbtn = callbtn;
    this.callme = callme;
    this.callme1 = callme1;
    this.callmed = callmed;
    this.callprice = callprice;
    this.chatbtn = chatbtn;
    this.chatme = chatme;
    this.chatme1 = chatme1;
    this.chatmed = chatmed;
    this.chatprice = chatprice;
    this.imgSupport = imgSupport;
    this.llwallet = llwallet;
    this.lybutton = lybutton;
    this.mainContent = mainContent;
    this.permin = permin;
    this.perminchat = perminchat;
    this.recyclerview = recyclerview;
    this.toolbar = toolbar;
    this.txtBalance = txtBalance;
  }

  @NonNull
  public static ActivitySlidingAstroDetailsLIstBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_sliding_astro_details_l_ist, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySlidingAstroDetailsLIstBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivitySlidingAstroDetailsLIstBinding>inflateInternal(inflater, R.layout.activity_sliding_astro_details_l_ist, root, attachToRoot, component);
  }

  @NonNull
  public static ActivitySlidingAstroDetailsLIstBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_sliding_astro_details_l_ist, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySlidingAstroDetailsLIstBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivitySlidingAstroDetailsLIstBinding>inflateInternal(inflater, R.layout.activity_sliding_astro_details_l_ist, null, false, component);
  }

  public static ActivitySlidingAstroDetailsLIstBinding bind(@NonNull View view) {
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
  public static ActivitySlidingAstroDetailsLIstBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivitySlidingAstroDetailsLIstBinding)bind(component, view, R.layout.activity_sliding_astro_details_l_ist);
  }
}
