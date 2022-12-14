// Generated by data binding compiler. Do not edit!
package com.ksbm_astroexpert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.view.NormalTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActionbarLayoutBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatImageView backarrow;

  @NonNull
  public final AppCompatImageView imgNotification;

  @NonNull
  public final AppCompatImageView imgSupport;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final NormalTextView txtTitle;

  @NonNull
  public final View viewWallet;

  protected ActionbarLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatImageView backarrow, AppCompatImageView imgNotification,
      AppCompatImageView imgSupport, Toolbar toolbar, NormalTextView txtTitle, View viewWallet) {
    super(_bindingComponent, _root, _localFieldCount);
    this.backarrow = backarrow;
    this.imgNotification = imgNotification;
    this.imgSupport = imgSupport;
    this.toolbar = toolbar;
    this.txtTitle = txtTitle;
    this.viewWallet = viewWallet;
  }

  @NonNull
  public static ActionbarLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.actionbar_layout, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActionbarLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActionbarLayoutBinding>inflateInternal(inflater, R.layout.actionbar_layout, root, attachToRoot, component);
  }

  @NonNull
  public static ActionbarLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.actionbar_layout, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActionbarLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActionbarLayoutBinding>inflateInternal(inflater, R.layout.actionbar_layout, null, false, component);
  }

  public static ActionbarLayoutBinding bind(@NonNull View view) {
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
  public static ActionbarLayoutBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActionbarLayoutBinding)bind(component, view, R.layout.actionbar_layout);
  }
}
