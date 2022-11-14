// Generated by data binding compiler. Do not edit!
package com.ksbm_astroexpert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.blog.BlogsResponseModel;
import com.ksbm_astroexpert.view.BoldTextView;
import com.ksbm_astroexpert.view.NormalTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class BlogDetailActivityBinding extends ViewDataBinding {
  @NonNull
  public final ActionbarLayoutBinding actionbar;

  @NonNull
  public final FloatingActionButton fabShare;

  @NonNull
  public final AppCompatImageView imgBlogImage;

  @NonNull
  public final NormalTextView txtBlogDescription;

  @NonNull
  public final BoldTextView txtBlogTitle;

  @Bindable
  protected BlogsResponseModel.BlogModel mBlogModel;

  protected BlogDetailActivityBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ActionbarLayoutBinding actionbar, FloatingActionButton fabShare,
      AppCompatImageView imgBlogImage, NormalTextView txtBlogDescription,
      BoldTextView txtBlogTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.actionbar = actionbar;
    this.fabShare = fabShare;
    this.imgBlogImage = imgBlogImage;
    this.txtBlogDescription = txtBlogDescription;
    this.txtBlogTitle = txtBlogTitle;
  }

  public abstract void setBlogModel(@Nullable BlogsResponseModel.BlogModel blogModel);

  @Nullable
  public BlogsResponseModel.BlogModel getBlogModel() {
    return mBlogModel;
  }

  @NonNull
  public static BlogDetailActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.blog_detail_activity, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static BlogDetailActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<BlogDetailActivityBinding>inflateInternal(inflater, R.layout.blog_detail_activity, root, attachToRoot, component);
  }

  @NonNull
  public static BlogDetailActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.blog_detail_activity, null, false, component)
   */
  @NonNull
  @Deprecated
  public static BlogDetailActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<BlogDetailActivityBinding>inflateInternal(inflater, R.layout.blog_detail_activity, null, false, component);
  }

  public static BlogDetailActivityBinding bind(@NonNull View view) {
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
  public static BlogDetailActivityBinding bind(@NonNull View view, @Nullable Object component) {
    return (BlogDetailActivityBinding)bind(component, view, R.layout.blog_detail_activity);
  }
}