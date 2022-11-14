package com.ksbm_astroexpert.ui.blog;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.BlogFragmentBinding;
import com.ksbm_astroexpert.databinding.BlogItemBinding;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.base.BaseFragment;
import com.ksbm_astroexpert.ui.base.BaseProgressRecyclerListAdapter;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.splash.SplashActivity;
import com.ksbm_astroexpert.ui.utils.UserUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ksbm_astroexpert.ui.home.HomeActivity.ChanFrag;

public class BlogFragment extends BaseFragment {

    private BlogFragmentBinding binding;
    private APIInterface apiInterface;

    @Override
    protected int getLayout() {
        return R.layout.blog_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = (BlogFragmentBinding) getBinding();
        init();


        ChanFrag="Blog";

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });



    }

    private void init() {
        apiInterface = ((BaseActivity) mContext).getApiInterface1();
        getBlogs();
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void getBlogs() {
        UserUtils.visible(binding.loaderBlog);
        Call<BlogsResponseModel> call = apiInterface.getBlogs();
        call.enqueue(new Callback<BlogsResponseModel>() {
            @Override
            public void onResponse(Call<BlogsResponseModel> call, Response<BlogsResponseModel> response) {

                BlogsResponseModel blogsResponseModel = null;
                if (response.isSuccessful()) {
                    blogsResponseModel = response.body();
                }
                if (blogsResponseModel != null
                        && blogsResponseModel.getData() != null) {
                    initBlogList(blogsResponseModel.getData());
                } else {
                    initBlogList(null);
                }
            }

            @Override
            public void onFailure(Call<BlogsResponseModel> call, Throwable t) {
                call.cancel();
                initBlogList(null);
            }
        });
    }

    private BaseProgressRecyclerListAdapter blogsBaseProgressRecyclerListAdapter;

    private void initBlogList(List<BlogsResponseModel.BlogModel> blogModels) {
        UserUtils.gone(binding.loaderBlog);
        if (blogModels == null) {
            UserUtils.visible(binding.txtNoBlogData);
            return;
        }
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        binding.rvBlogList.setLayoutManager(manager);
        binding.rvBlogList.setNestedScrollingEnabled(false);
        blogsBaseProgressRecyclerListAdapter = new BaseProgressRecyclerListAdapter(blogModels) {

            @Override
            public int getLayout(int viewType) {
                return R.layout.blog_item;
            }

            @Override
            public int getViewType(int position) {
                return position;
            }

            @Override
            public void onBind(BaseProgressRecyclerListAdapter.ViewHolder holder) {
                BlogItemBinding binding = (BlogItemBinding) holder.getBinder();
                binding.setBlogModel((BlogsResponseModel.BlogModel) getItem(holder.getAdapterPosition()));
                binding.executePendingBindings();
                binding.item.setOnClickListener(v -> {
                    Intent blogDetailIntent = new Intent(mContext, BlogDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("blog", binding.getBlogModel());
                    blogDetailIntent.putExtra("data", bundle);
                    startActivity(blogDetailIntent);
                });
            }
        };
        binding.rvBlogList.setAdapter(blogsBaseProgressRecyclerListAdapter);
        blogsBaseProgressRecyclerListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                //Scrolling to starting position
                try {
                    if (binding.rvBlogList.getLayoutManager() != null) {
                        binding.rvBlogList.getLayoutManager().scrollToPosition(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
