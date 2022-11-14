// Generated by data binding compiler. Do not edit!
package com.ksbm_astroexpert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.RemediesResponseModel;
import com.ksbm_astroexpert.view.NormalTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class HomeMagicalRemediesItemBinding extends ViewDataBinding {
  @NonNull
  public final CardView cvRemedies;

  @NonNull
  public final AppCompatImageView imgRemedies;

  @NonNull
  public final ConstraintLayout item;

  @NonNull
  public final NormalTextView txtCaption;

  @Bindable
  protected RemediesResponseModel.RemediesModel mRemediesModel;

  protected HomeMagicalRemediesItemBinding(Object _bindingComponent, View _root,
      int _localFieldCount, CardView cvRemedies, AppCompatImageView imgRemedies,
      ConstraintLayout item, NormalTextView txtCaption) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cvRemedies = cvRemedies;
    this.imgRemedies = imgRemedies;
    this.item = item;
    this.txtCaption = txtCaption;
  }

  public abstract void setRemediesModel(
      @Nullable RemediesResponseModel.RemediesModel remediesModel);

  @Nullable
  public RemediesResponseModel.RemediesModel getRemediesModel() {
    return mRemediesModel;
  }

  @NonNull
  public static HomeMagicalRemediesItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_magical_remedies_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static HomeMagicalRemediesItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<HomeMagicalRemediesItemBinding>inflateInternal(inflater, R.layout.home_magical_remedies_item, root, attachToRoot, component);
  }

  @NonNull
  public static HomeMagicalRemediesItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.home_magical_remedies_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static HomeMagicalRemediesItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<HomeMagicalRemediesItemBinding>inflateInternal(inflater, R.layout.home_magical_remedies_item, null, false, component);
  }

  public static HomeMagicalRemediesItemBinding bind(@NonNull View view) {
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
  public static HomeMagicalRemediesItemBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (HomeMagicalRemediesItemBinding)bind(component, view, R.layout.home_magical_remedies_item);
  }
}