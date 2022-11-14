package com.ksbm_astroexpert;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ksbm_astroexpert.databinding.AboutActivityBindingImpl;
import com.ksbm_astroexpert.databinding.ActionbarLayoutBindingImpl;
import com.ksbm_astroexpert.databinding.ActivityShareJourneyBindingImpl;
import com.ksbm_astroexpert.databinding.ActivitySlidingAstroDetailsLIstBindingImpl;
import com.ksbm_astroexpert.databinding.AgentProfileActivityBindingImpl;
import com.ksbm_astroexpert.databinding.BlogDetailActivityBindingImpl;
import com.ksbm_astroexpert.databinding.BlogFragmentBindingImpl;
import com.ksbm_astroexpert.databinding.BlogItemBindingImpl;
import com.ksbm_astroexpert.databinding.ContactUsActivityBindingImpl;
import com.ksbm_astroexpert.databinding.ContentProfileBindingImpl;
import com.ksbm_astroexpert.databinding.FaqsActivityBindingImpl;
import com.ksbm_astroexpert.databinding.HomeAccountFragmentBindingImpl;
import com.ksbm_astroexpert.databinding.HomeActivityBindingImpl;
import com.ksbm_astroexpert.databinding.HomeFragmentBindingImpl;
import com.ksbm_astroexpert.databinding.HomeMagicalRemediesItemBindingImpl;
import com.ksbm_astroexpert.databinding.HomeOurExpertsItemBindingImpl;
import com.ksbm_astroexpert.databinding.HomeScreenSlidePageFragmentBindingImpl;
import com.ksbm_astroexpert.databinding.HowItWorksActivityBindingImpl;
import com.ksbm_astroexpert.databinding.ListAstrodetailsBindingImpl;
import com.ksbm_astroexpert.databinding.NotificationActivityBindingImpl;
import com.ksbm_astroexpert.databinding.NotificationItemBindingImpl;
import com.ksbm_astroexpert.databinding.OtpVerificationActivityBindingImpl;
import com.ksbm_astroexpert.databinding.PrivacyPolicyActivityBindingImpl;
import com.ksbm_astroexpert.databinding.RegisterUserBindingImpl;
import com.ksbm_astroexpert.databinding.SearchActivityBindingImpl;
import com.ksbm_astroexpert.databinding.SearchExpertsItemBindingImpl;
import com.ksbm_astroexpert.databinding.SearchFilterLayoutBindingImpl;
import com.ksbm_astroexpert.databinding.SearchFragmentBindingImpl;
import com.ksbm_astroexpert.databinding.SignInActivityBindingImpl;
import com.ksbm_astroexpert.databinding.SignUpActivityBindingImpl;
import com.ksbm_astroexpert.databinding.SplashActivityBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ABOUTACTIVITY = 1;

  private static final int LAYOUT_ACTIONBARLAYOUT = 2;

  private static final int LAYOUT_ACTIVITYSHAREJOURNEY = 3;

  private static final int LAYOUT_ACTIVITYSLIDINGASTRODETAILSLIST = 4;

  private static final int LAYOUT_AGENTPROFILEACTIVITY = 5;

  private static final int LAYOUT_BLOGDETAILACTIVITY = 6;

  private static final int LAYOUT_BLOGFRAGMENT = 7;

  private static final int LAYOUT_BLOGITEM = 8;

  private static final int LAYOUT_CONTACTUSACTIVITY = 9;

  private static final int LAYOUT_CONTENTPROFILE = 10;

  private static final int LAYOUT_FAQSACTIVITY = 11;

  private static final int LAYOUT_HOMEACCOUNTFRAGMENT = 12;

  private static final int LAYOUT_HOMEACTIVITY = 13;

  private static final int LAYOUT_HOMEFRAGMENT = 14;

  private static final int LAYOUT_HOMEMAGICALREMEDIESITEM = 15;

  private static final int LAYOUT_HOMEOUREXPERTSITEM = 16;

  private static final int LAYOUT_HOMESCREENSLIDEPAGEFRAGMENT = 17;

  private static final int LAYOUT_HOWITWORKSACTIVITY = 18;

  private static final int LAYOUT_LISTASTRODETAILS = 19;

  private static final int LAYOUT_NOTIFICATIONACTIVITY = 20;

  private static final int LAYOUT_NOTIFICATIONITEM = 21;

  private static final int LAYOUT_OTPVERIFICATIONACTIVITY = 22;

  private static final int LAYOUT_PRIVACYPOLICYACTIVITY = 23;

  private static final int LAYOUT_REGISTERUSER = 24;

  private static final int LAYOUT_SEARCHACTIVITY = 25;

  private static final int LAYOUT_SEARCHEXPERTSITEM = 26;

  private static final int LAYOUT_SEARCHFILTERLAYOUT = 27;

  private static final int LAYOUT_SEARCHFRAGMENT = 28;

  private static final int LAYOUT_SIGNINACTIVITY = 29;

  private static final int LAYOUT_SIGNUPACTIVITY = 30;

  private static final int LAYOUT_SPLASHACTIVITY = 31;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(31);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.about_activity, LAYOUT_ABOUTACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.actionbar_layout, LAYOUT_ACTIONBARLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.activity_share_journey, LAYOUT_ACTIVITYSHAREJOURNEY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.activity_sliding_astro_details_l_ist, LAYOUT_ACTIVITYSLIDINGASTRODETAILSLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.agent_profile_activity, LAYOUT_AGENTPROFILEACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.blog_detail_activity, LAYOUT_BLOGDETAILACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.blog_fragment, LAYOUT_BLOGFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.blog_item, LAYOUT_BLOGITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.contact_us_activity, LAYOUT_CONTACTUSACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.content_profile, LAYOUT_CONTENTPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.faqs_activity, LAYOUT_FAQSACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.home_account_fragment, LAYOUT_HOMEACCOUNTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.home_activity, LAYOUT_HOMEACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.home_fragment, LAYOUT_HOMEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.home_magical_remedies_item, LAYOUT_HOMEMAGICALREMEDIESITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.home_our_experts_item, LAYOUT_HOMEOUREXPERTSITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.home_screen_slide_page_fragment, LAYOUT_HOMESCREENSLIDEPAGEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.how_it_works_activity, LAYOUT_HOWITWORKSACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.list_astrodetails, LAYOUT_LISTASTRODETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.notification_activity, LAYOUT_NOTIFICATIONACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.notification_item, LAYOUT_NOTIFICATIONITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.otp_verification_activity, LAYOUT_OTPVERIFICATIONACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.privacy_policy_activity, LAYOUT_PRIVACYPOLICYACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.register_user, LAYOUT_REGISTERUSER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.search_activity, LAYOUT_SEARCHACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.search_experts_item, LAYOUT_SEARCHEXPERTSITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.search_filter_layout, LAYOUT_SEARCHFILTERLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.search_fragment, LAYOUT_SEARCHFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.sign_in_activity, LAYOUT_SIGNINACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.sign_up_activity, LAYOUT_SIGNUPACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ksbm_astroexpert.R.layout.splash_activity, LAYOUT_SPLASHACTIVITY);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ABOUTACTIVITY: {
          if ("layout/about_activity_0".equals(tag)) {
            return new AboutActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for about_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIONBARLAYOUT: {
          if ("layout/actionbar_layout_0".equals(tag)) {
            return new ActionbarLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for actionbar_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSHAREJOURNEY: {
          if ("layout/activity_share_journey_0".equals(tag)) {
            return new ActivityShareJourneyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_share_journey is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSLIDINGASTRODETAILSLIST: {
          if ("layout/activity_sliding_astro_details_l_ist_0".equals(tag)) {
            return new ActivitySlidingAstroDetailsLIstBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_sliding_astro_details_l_ist is invalid. Received: " + tag);
        }
        case  LAYOUT_AGENTPROFILEACTIVITY: {
          if ("layout/agent_profile_activity_0".equals(tag)) {
            return new AgentProfileActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for agent_profile_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_BLOGDETAILACTIVITY: {
          if ("layout/blog_detail_activity_0".equals(tag)) {
            return new BlogDetailActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for blog_detail_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_BLOGFRAGMENT: {
          if ("layout/blog_fragment_0".equals(tag)) {
            return new BlogFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for blog_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_BLOGITEM: {
          if ("layout/blog_item_0".equals(tag)) {
            return new BlogItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for blog_item is invalid. Received: " + tag);
        }
        case  LAYOUT_CONTACTUSACTIVITY: {
          if ("layout/contact_us_activity_0".equals(tag)) {
            return new ContactUsActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for contact_us_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_CONTENTPROFILE: {
          if ("layout/content_profile_0".equals(tag)) {
            return new ContentProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for content_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_FAQSACTIVITY: {
          if ("layout/faqs_activity_0".equals(tag)) {
            return new FaqsActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for faqs_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEACCOUNTFRAGMENT: {
          if ("layout/home_account_fragment_0".equals(tag)) {
            return new HomeAccountFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_account_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEACTIVITY: {
          if ("layout/home_activity_0".equals(tag)) {
            return new HomeActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEFRAGMENT: {
          if ("layout/home_fragment_0".equals(tag)) {
            return new HomeFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEMAGICALREMEDIESITEM: {
          if ("layout/home_magical_remedies_item_0".equals(tag)) {
            return new HomeMagicalRemediesItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_magical_remedies_item is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEOUREXPERTSITEM: {
          if ("layout/home_our_experts_item_0".equals(tag)) {
            return new HomeOurExpertsItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_our_experts_item is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMESCREENSLIDEPAGEFRAGMENT: {
          if ("layout/home_screen_slide_page_fragment_0".equals(tag)) {
            return new HomeScreenSlidePageFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_screen_slide_page_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_HOWITWORKSACTIVITY: {
          if ("layout/how_it_works_activity_0".equals(tag)) {
            return new HowItWorksActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for how_it_works_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTASTRODETAILS: {
          if ("layout/list_astrodetails_0".equals(tag)) {
            return new ListAstrodetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_astrodetails is invalid. Received: " + tag);
        }
        case  LAYOUT_NOTIFICATIONACTIVITY: {
          if ("layout/notification_activity_0".equals(tag)) {
            return new NotificationActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for notification_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_NOTIFICATIONITEM: {
          if ("layout/notification_item_0".equals(tag)) {
            return new NotificationItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for notification_item is invalid. Received: " + tag);
        }
        case  LAYOUT_OTPVERIFICATIONACTIVITY: {
          if ("layout/otp_verification_activity_0".equals(tag)) {
            return new OtpVerificationActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for otp_verification_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_PRIVACYPOLICYACTIVITY: {
          if ("layout/privacy_policy_activity_0".equals(tag)) {
            return new PrivacyPolicyActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for privacy_policy_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_REGISTERUSER: {
          if ("layout/register_user_0".equals(tag)) {
            return new RegisterUserBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for register_user is invalid. Received: " + tag);
        }
        case  LAYOUT_SEARCHACTIVITY: {
          if ("layout/search_activity_0".equals(tag)) {
            return new SearchActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for search_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_SEARCHEXPERTSITEM: {
          if ("layout/search_experts_item_0".equals(tag)) {
            return new SearchExpertsItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for search_experts_item is invalid. Received: " + tag);
        }
        case  LAYOUT_SEARCHFILTERLAYOUT: {
          if ("layout/search_filter_layout_0".equals(tag)) {
            return new SearchFilterLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for search_filter_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_SEARCHFRAGMENT: {
          if ("layout/search_fragment_0".equals(tag)) {
            return new SearchFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for search_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_SIGNINACTIVITY: {
          if ("layout/sign_in_activity_0".equals(tag)) {
            return new SignInActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for sign_in_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_SIGNUPACTIVITY: {
          if ("layout/sign_up_activity_0".equals(tag)) {
            return new SignUpActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for sign_up_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_SPLASHACTIVITY: {
          if ("layout/splash_activity_0".equals(tag)) {
            return new SplashActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for splash_activity is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(5);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "bannerModel");
      sKeys.put(2, "blogModel");
      sKeys.put(3, "relatedVendor");
      sKeys.put(4, "remediesModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(31);

    static {
      sKeys.put("layout/about_activity_0", com.ksbm_astroexpert.R.layout.about_activity);
      sKeys.put("layout/actionbar_layout_0", com.ksbm_astroexpert.R.layout.actionbar_layout);
      sKeys.put("layout/activity_share_journey_0", com.ksbm_astroexpert.R.layout.activity_share_journey);
      sKeys.put("layout/activity_sliding_astro_details_l_ist_0", com.ksbm_astroexpert.R.layout.activity_sliding_astro_details_l_ist);
      sKeys.put("layout/agent_profile_activity_0", com.ksbm_astroexpert.R.layout.agent_profile_activity);
      sKeys.put("layout/blog_detail_activity_0", com.ksbm_astroexpert.R.layout.blog_detail_activity);
      sKeys.put("layout/blog_fragment_0", com.ksbm_astroexpert.R.layout.blog_fragment);
      sKeys.put("layout/blog_item_0", com.ksbm_astroexpert.R.layout.blog_item);
      sKeys.put("layout/contact_us_activity_0", com.ksbm_astroexpert.R.layout.contact_us_activity);
      sKeys.put("layout/content_profile_0", com.ksbm_astroexpert.R.layout.content_profile);
      sKeys.put("layout/faqs_activity_0", com.ksbm_astroexpert.R.layout.faqs_activity);
      sKeys.put("layout/home_account_fragment_0", com.ksbm_astroexpert.R.layout.home_account_fragment);
      sKeys.put("layout/home_activity_0", com.ksbm_astroexpert.R.layout.home_activity);
      sKeys.put("layout/home_fragment_0", com.ksbm_astroexpert.R.layout.home_fragment);
      sKeys.put("layout/home_magical_remedies_item_0", com.ksbm_astroexpert.R.layout.home_magical_remedies_item);
      sKeys.put("layout/home_our_experts_item_0", com.ksbm_astroexpert.R.layout.home_our_experts_item);
      sKeys.put("layout/home_screen_slide_page_fragment_0", com.ksbm_astroexpert.R.layout.home_screen_slide_page_fragment);
      sKeys.put("layout/how_it_works_activity_0", com.ksbm_astroexpert.R.layout.how_it_works_activity);
      sKeys.put("layout/list_astrodetails_0", com.ksbm_astroexpert.R.layout.list_astrodetails);
      sKeys.put("layout/notification_activity_0", com.ksbm_astroexpert.R.layout.notification_activity);
      sKeys.put("layout/notification_item_0", com.ksbm_astroexpert.R.layout.notification_item);
      sKeys.put("layout/otp_verification_activity_0", com.ksbm_astroexpert.R.layout.otp_verification_activity);
      sKeys.put("layout/privacy_policy_activity_0", com.ksbm_astroexpert.R.layout.privacy_policy_activity);
      sKeys.put("layout/register_user_0", com.ksbm_astroexpert.R.layout.register_user);
      sKeys.put("layout/search_activity_0", com.ksbm_astroexpert.R.layout.search_activity);
      sKeys.put("layout/search_experts_item_0", com.ksbm_astroexpert.R.layout.search_experts_item);
      sKeys.put("layout/search_filter_layout_0", com.ksbm_astroexpert.R.layout.search_filter_layout);
      sKeys.put("layout/search_fragment_0", com.ksbm_astroexpert.R.layout.search_fragment);
      sKeys.put("layout/sign_in_activity_0", com.ksbm_astroexpert.R.layout.sign_in_activity);
      sKeys.put("layout/sign_up_activity_0", com.ksbm_astroexpert.R.layout.sign_up_activity);
      sKeys.put("layout/splash_activity_0", com.ksbm_astroexpert.R.layout.splash_activity);
    }
  }
}
