// Generated by data binding compiler. Do not edit!
package com.ksbm_astroexpert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.ExpertsResponseModel;
import com.ksbm_astroexpert.view.NormalTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class AgentProfileActivityBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final CollapsingToolbarLayout ctToolbarLayout;

  @NonNull
  public final View deviderExpertExperiance;

  @NonNull
  public final View dvdrConsulationCharge;

  @NonNull
  public final View dvdrExpertise;

  @NonNull
  public final View dvdrLocation;

  @NonNull
  public final View dvdrProfileSummary;

  @NonNull
  public final View dvdrReviews;

  @NonNull
  public final View dvdrSpokenLanguage;

  @NonNull
  public final View dvdrTimings;

  @NonNull
  public final FloatingActionButton fabCall;

  @NonNull
  public final FloatingActionButton fabChat;

  @NonNull
  public final AppCompatImageView imgBack;

  @NonNull
  public final AppCompatImageView imgConsultationCharges;

  @NonNull
  public final ShapeableImageView imgExpert;

  @NonNull
  public final AppCompatImageView imgExpertise;

  @NonNull
  public final AppCompatImageView imgLocation;

  @NonNull
  public final AppCompatImageView imgProfileSummary;

  @NonNull
  public final AppCompatImageView imgReviews;

  @NonNull
  public final AppCompatImageView imgShare;

  @NonNull
  public final AppCompatImageView imgSpokenLanguage;

  @NonNull
  public final AppCompatImageView imgTimings;

  @NonNull
  public final AppCompatImageView imgvExperiance;

  @NonNull
  public final AppCompatRatingBar rating;

  @NonNull
  public final NormalTextView tvAstroExperiance;

  @NonNull
  public final NormalTextView tvConsulationCharge;

  @NonNull
  public final NormalTextView tvConsulationChargeHeader;

  @NonNull
  public final NormalTextView tvExpertExperianceHeader;

  @NonNull
  public final NormalTextView tvExpertise;

  @NonNull
  public final NormalTextView tvExpertiseHeader;

  @NonNull
  public final NormalTextView tvLocation;

  @NonNull
  public final NormalTextView tvLocationHeader;

  @NonNull
  public final NormalTextView tvProfileSummary;

  @NonNull
  public final NormalTextView tvProfileSummaryHeader;

  @NonNull
  public final NormalTextView tvReviews;

  @NonNull
  public final NormalTextView tvReviewsHeader;

  @NonNull
  public final NormalTextView tvSpokenLanguage;

  @NonNull
  public final NormalTextView tvSpokenLanguageHeader;

  @NonNull
  public final NormalTextView tvTimings;

  @NonNull
  public final NormalTextView tvTimingsHeader;

  @NonNull
  public final NormalTextView txtAstrologerName;

  @Bindable
  protected ExpertsResponseModel.RelatedVendor mRelatedVendor;

  protected AgentProfileActivityBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppBarLayout appbar, CollapsingToolbarLayout ctToolbarLayout, View deviderExpertExperiance,
      View dvdrConsulationCharge, View dvdrExpertise, View dvdrLocation, View dvdrProfileSummary,
      View dvdrReviews, View dvdrSpokenLanguage, View dvdrTimings, FloatingActionButton fabCall,
      FloatingActionButton fabChat, AppCompatImageView imgBack,
      AppCompatImageView imgConsultationCharges, ShapeableImageView imgExpert,
      AppCompatImageView imgExpertise, AppCompatImageView imgLocation,
      AppCompatImageView imgProfileSummary, AppCompatImageView imgReviews,
      AppCompatImageView imgShare, AppCompatImageView imgSpokenLanguage,
      AppCompatImageView imgTimings, AppCompatImageView imgvExperiance, AppCompatRatingBar rating,
      NormalTextView tvAstroExperiance, NormalTextView tvConsulationCharge,
      NormalTextView tvConsulationChargeHeader, NormalTextView tvExpertExperianceHeader,
      NormalTextView tvExpertise, NormalTextView tvExpertiseHeader, NormalTextView tvLocation,
      NormalTextView tvLocationHeader, NormalTextView tvProfileSummary,
      NormalTextView tvProfileSummaryHeader, NormalTextView tvReviews,
      NormalTextView tvReviewsHeader, NormalTextView tvSpokenLanguage,
      NormalTextView tvSpokenLanguageHeader, NormalTextView tvTimings,
      NormalTextView tvTimingsHeader, NormalTextView txtAstrologerName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appbar = appbar;
    this.ctToolbarLayout = ctToolbarLayout;
    this.deviderExpertExperiance = deviderExpertExperiance;
    this.dvdrConsulationCharge = dvdrConsulationCharge;
    this.dvdrExpertise = dvdrExpertise;
    this.dvdrLocation = dvdrLocation;
    this.dvdrProfileSummary = dvdrProfileSummary;
    this.dvdrReviews = dvdrReviews;
    this.dvdrSpokenLanguage = dvdrSpokenLanguage;
    this.dvdrTimings = dvdrTimings;
    this.fabCall = fabCall;
    this.fabChat = fabChat;
    this.imgBack = imgBack;
    this.imgConsultationCharges = imgConsultationCharges;
    this.imgExpert = imgExpert;
    this.imgExpertise = imgExpertise;
    this.imgLocation = imgLocation;
    this.imgProfileSummary = imgProfileSummary;
    this.imgReviews = imgReviews;
    this.imgShare = imgShare;
    this.imgSpokenLanguage = imgSpokenLanguage;
    this.imgTimings = imgTimings;
    this.imgvExperiance = imgvExperiance;
    this.rating = rating;
    this.tvAstroExperiance = tvAstroExperiance;
    this.tvConsulationCharge = tvConsulationCharge;
    this.tvConsulationChargeHeader = tvConsulationChargeHeader;
    this.tvExpertExperianceHeader = tvExpertExperianceHeader;
    this.tvExpertise = tvExpertise;
    this.tvExpertiseHeader = tvExpertiseHeader;
    this.tvLocation = tvLocation;
    this.tvLocationHeader = tvLocationHeader;
    this.tvProfileSummary = tvProfileSummary;
    this.tvProfileSummaryHeader = tvProfileSummaryHeader;
    this.tvReviews = tvReviews;
    this.tvReviewsHeader = tvReviewsHeader;
    this.tvSpokenLanguage = tvSpokenLanguage;
    this.tvSpokenLanguageHeader = tvSpokenLanguageHeader;
    this.tvTimings = tvTimings;
    this.tvTimingsHeader = tvTimingsHeader;
    this.txtAstrologerName = txtAstrologerName;
  }

  public abstract void setRelatedVendor(@Nullable ExpertsResponseModel.RelatedVendor relatedVendor);

  @Nullable
  public ExpertsResponseModel.RelatedVendor getRelatedVendor() {
    return mRelatedVendor;
  }

  @NonNull
  public static AgentProfileActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.agent_profile_activity, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static AgentProfileActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<AgentProfileActivityBinding>inflateInternal(inflater, R.layout.agent_profile_activity, root, attachToRoot, component);
  }

  @NonNull
  public static AgentProfileActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.agent_profile_activity, null, false, component)
   */
  @NonNull
  @Deprecated
  public static AgentProfileActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<AgentProfileActivityBinding>inflateInternal(inflater, R.layout.agent_profile_activity, null, false, component);
  }

  public static AgentProfileActivityBinding bind(@NonNull View view) {
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
  public static AgentProfileActivityBinding bind(@NonNull View view, @Nullable Object component) {
    return (AgentProfileActivityBinding)bind(component, view, R.layout.agent_profile_activity);
  }
}
