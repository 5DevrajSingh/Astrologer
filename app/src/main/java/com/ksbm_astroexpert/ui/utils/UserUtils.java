package com.ksbm_astroexpert.ui.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;

public class UserUtils {

    private static final String TAG = "UserUtils";

    public static void enable(View view) {
        view.setEnabled(true);
    }

    public static void disable(View view) {
        view.setEnabled(false);
    }

    public static void gone(View view) {
        view.setVisibility(View.GONE);
    }

    public static void invisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    public static void visible(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void renderPhoto(String url, ImageView imageView) {
        Log.d(TAG, "renderPhoto() called with: url = [" + url + "], imageView = [" + imageView + "]");
        Picasso.get().load(url).into(imageView);
    }

    public static void toast(Context context, int resId) {
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show();
    }

    public static String getMaskPhoneNo(String phoneNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = phoneNumber.length();
        char[] ary = phoneNumber.toCharArray();
        for (int i = 0; i < length; i++) {
            if (i == 0 || i == 1 || i == 8 || i == 9) {
                stringBuilder.append(ary[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        Resources resources = context.getResources();
        return resources.getDisplayMetrics();
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * (getDisplayMetrics(context).densityDpi / 160f);
    }

    public static int convertDpToPixelSize(float dp, Context context) {
        float pixels = convertDpToPixel(dp, context);
        final int res = (int) (pixels + 0.5f);
        if (res != 0) {
            return res;
        } else if (pixels == 0) {
            return 0;
        } else if (pixels > 0) {
            return 1;
        }
        return -1;
    }

    public static float getTextWidth(Paint paint, String text) {
        return paint.measureText(text);
    }


    // Gson
    public static String getString(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T getObject(String data, Class<T> clz) {
        return new Gson().fromJson(data, clz);
    }

    public static <T> List<T> getList(String data, Type typeToken) {
        return new Gson().fromJson(data, typeToken);
    }

    // Validators
    public static boolean isValidPhoneNumber(String val) {
        if (val.length() == 10) {
            return false;
        }
        return true;
    }

    public static String isNotNull(EditText editText) {
        if (editText.getText() != null) {
            return editText.getText().toString();
        }
        return null;
    }

    public static String getPhoneNumberFromId(String phoneNumber) {
        try {
            if (phoneNumber != null && phoneNumber.length() >= 10) {
                if (phoneNumber.length() == 10) {
                    return phoneNumber;
                }
                return phoneNumber.substring((phoneNumber.length() - 10), phoneNumber.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Binding Adapters
    @BindingAdapter({"android:picassoImg"})
    public static void loadImageOnPicasso(ImageView imageView, String url) {
//        if (url != null) {
//            if (!url.contains("https://") || !url.contains("http://")) {
//                url = "https://" + url;
//            }
//        }
        renderPhoto(url, imageView);
    }

    @BindingAdapter({"android:exp", "android:defVal"})
    public static void setExpertExp(TextView textView, String exp, String defVal) {
        if (exp != null) {
            textView.setText("Exp : " + exp + "+ years");
        } else {
            textView.setText(defVal);
        }
    }

    @BindingAdapter({"android:expertRating"})
    public static void setExpertRating(RatingBar ratingBar, String rating) {
        try {
            float ratingbar = Float.parseFloat(rating);
            ratingBar.setRating(ratingbar);
        } catch (Exception e) {
            ratingBar.setRating(0f);
        }
    }

    @BindingAdapter({"android:expertPrice", "android:defVal"})
    public static void setExpertPrice(TextView textView, String price, String defVal) {
        if(price != null) {
            textView.setText("₹" + price + "/min");
        }else {
            textView.setText(defVal);
        }
    }


    //Preference
}
