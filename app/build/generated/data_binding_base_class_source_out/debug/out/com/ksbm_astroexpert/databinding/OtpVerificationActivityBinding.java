// Generated by data binding compiler. Do not edit!
package com.ksbm_astroexpert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.view.BoldTextView;
import com.ksbm_astroexpert.view.NormalTextView;
import in.aabhasjindal.otptextview.OtpTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class OtpVerificationActivityBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatImageView backarrow;

  @NonNull
  public final ProgressBar loaderResend;

  @NonNull
  public final OtpTextView otpView;

  @NonNull
  public final NormalTextView textView;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final NormalTextView txtPhoneNo;

  @NonNull
  public final BoldTextView txtResend;

  @NonNull
  public final NormalTextView txtTimer;

  @NonNull
  public final NormalTextView txtTitle;

  protected OtpVerificationActivityBinding(Object _bindingComponent, View _root,
      int _localFieldCount, AppCompatImageView backarrow, ProgressBar loaderResend,
      OtpTextView otpView, NormalTextView textView, Toolbar toolbar, NormalTextView txtPhoneNo,
      BoldTextView txtResend, NormalTextView txtTimer, NormalTextView txtTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.backarrow = backarrow;
    this.loaderResend = loaderResend;
    this.otpView = otpView;
    this.textView = textView;
    this.toolbar = toolbar;
    this.txtPhoneNo = txtPhoneNo;
    this.txtResend = txtResend;
    this.txtTimer = txtTimer;
    this.txtTitle = txtTitle;
  }

  @NonNull
  public static OtpVerificationActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.otp_verification_activity, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static OtpVerificationActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<OtpVerificationActivityBinding>inflateInternal(inflater, R.layout.otp_verification_activity, root, attachToRoot, component);
  }

  @NonNull
  public static OtpVerificationActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.otp_verification_activity, null, false, component)
   */
  @NonNull
  @Deprecated
  public static OtpVerificationActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<OtpVerificationActivityBinding>inflateInternal(inflater, R.layout.otp_verification_activity, null, false, component);
  }

  public static OtpVerificationActivityBinding bind(@NonNull View view) {
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
  public static OtpVerificationActivityBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (OtpVerificationActivityBinding)bind(component, view, R.layout.otp_verification_activity);
  }
}
