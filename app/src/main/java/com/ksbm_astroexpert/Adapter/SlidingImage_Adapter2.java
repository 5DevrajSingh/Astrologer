package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.ksbm_astroexpert.CustomVolleyRequest;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.BannerResponseModel;
import com.ksbm_astroexpert.ui.home.ScreenSliderPageFragment;
import com.ksbm_astroexpert.ui.home.ViewAllAstrologer;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SlidingImage_Adapter2   extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<BannerResponseModel.BannerModel> sliderImg;
    private ImageLoader imageLoader;
    SharedPreferences sharedPreferences;

    public SlidingImage_Adapter2(List sliderImg,Context context) {
        this.sliderImg = sliderImg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderImg.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.banner_image, null);

        BannerResponseModel.BannerModel utils = sliderImg.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(utils.getImage(), ImageLoader.getImageListener(imageView, R.drawable.logo, android.R.drawable.ic_dialog_alert));

        Glide.with(context)
                .load(utils.getSubCatImg())
                .placeholder(R.drawable.logo)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentViewAll = new Intent(context, ViewAllAstrologer.class);
                intentViewAll.putExtra("type", false);
                context.startActivity(intentViewAll);

//                if(sliderImg.get(position).getType().matches("Category")) {
//                    sharedPreferences = context.getSharedPreferences("TEMP", MODE_PRIVATE);
//
//                    String mmmm = sliderImg.get(position).getCategory();
//                    String mmmm1 = sliderImg.get(position).getcategoryName();
//                    Intent intent = new Intent(context, SubjectList.class);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("paid","0");
//                    intent.putExtra("subject_list",mmmm);
//                    editor.putString("subject_list",mmmm);
//                    intent.putExtra("cname",mmmm1);
//                    intent.putExtra("paid","0");
//                    editor.apply();
//                    context.startActivity(intent);
//
//
//                    //Toast.makeText(context, "Category is clicke", Toast.LENGTH_SHORT).show();
//                } else {
//                    String myyyy = sliderImg.get(position).getUrl();
//                    if (!myyyy.startsWith("http://") && !myyyy.startsWith("https://"))
//                        myyyy = "http://" + myyyy;
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myyyy));
//                    context.startActivity(browserIntent);
//                }
            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
