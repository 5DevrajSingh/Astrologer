package com.ksbm_astroexpert.ui.blog;

import android.content.Intent;
import android.os.Bundle;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.BlogDetailActivityBinding;
import com.ksbm_astroexpert.ui.base.BaseActivity;

public class BlogDetailActivity extends BaseActivity {

    private BlogDetailActivityBinding binding;

    @Override
    protected int getLayout() {
        return R.layout.blog_detail_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (BlogDetailActivityBinding) getBinding();
        init();
    }

    private void init() {
        actionBar(binding.actionbar.toolbar, R.drawable.ic_arrow_back, false, true, true, true);
        binding.actionbar.txtTitle.setText(R.string.lbl_blogs);
        BlogsResponseModel.BlogModel blogModel = null;
        try {
            if (getIntent() != null) {
                Bundle bundle = getIntent().getBundleExtra("data");
                if (bundle != null) {
                    if (bundle.containsKey("blog")) {
                        blogModel = bundle.getParcelable("blog");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (blogModel != null) {
            binding.setBlogModel(blogModel);
            binding.fabShare.setOnClickListener(v -> {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                BlogsResponseModel.BlogModel blogModel1 = binding.getBlogModel();
                String appLink = String.format(getString(R.string.lbl_app_link),getPackageName());
                String shareBody = blogModel1.getTitle() + "\n" + blogModel1.getDescription() + "\n\n" + appLink;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.lbl_share_blog));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            });
        }
    }
}
