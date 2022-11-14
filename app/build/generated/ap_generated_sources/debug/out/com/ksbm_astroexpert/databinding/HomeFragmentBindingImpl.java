package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HomeFragmentBindingImpl extends HomeFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.expertise, 1);
        sViewsWithIds.put(R.id.flBanner, 2);
        sViewsWithIds.put(R.id.viewPagerBanner, 3);
        sViewsWithIds.put(R.id.tabLayout, 4);
        sViewsWithIds.put(R.id.loaderBanner, 5);
        sViewsWithIds.put(R.id.loaderBanner1, 6);
        sViewsWithIds.put(R.id.llRemedies, 7);
        sViewsWithIds.put(R.id.txtTitleRemedies, 8);
        sViewsWithIds.put(R.id.txtViewAllRemedies, 9);
        sViewsWithIds.put(R.id.rvMagicalRemedies, 10);
        sViewsWithIds.put(R.id.recyclerviewastro, 11);
        sViewsWithIds.put(R.id.loaderRemedies, 12);
        sViewsWithIds.put(R.id.loaderRemedies1, 13);
        sViewsWithIds.put(R.id.llOurExperts, 14);
        sViewsWithIds.put(R.id.txtTitleExperts, 15);
        sViewsWithIds.put(R.id.txtViewAllExperts, 16);
        sViewsWithIds.put(R.id.rvExpertList, 17);
        sViewsWithIds.put(R.id.rvAstroList, 18);
        sViewsWithIds.put(R.id.loaderOurExperts, 19);
        sViewsWithIds.put(R.id.loaderOurExperts1, 20);
        sViewsWithIds.put(R.id.txtNoExpertData, 21);
        sViewsWithIds.put(R.id.bi, 22);
        sViewsWithIds.put(R.id.testimonials, 23);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HomeFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private HomeFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[22]
            , (androidx.recyclerview.widget.RecyclerView) bindings[1]
            , (android.widget.FrameLayout) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[14]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (android.widget.ProgressBar) bindings[5]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ProgressBar) bindings[19]
            , (android.widget.ImageView) bindings[20]
            , (android.widget.ProgressBar) bindings[12]
            , (android.widget.ImageView) bindings[13]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[0]
            , (androidx.recyclerview.widget.RecyclerView) bindings[11]
            , (androidx.recyclerview.widget.RecyclerView) bindings[18]
            , (androidx.recyclerview.widget.RecyclerView) bindings[17]
            , (androidx.recyclerview.widget.RecyclerView) bindings[10]
            , (com.google.android.material.tabs.TabLayout) bindings[4]
            , (androidx.recyclerview.widget.RecyclerView) bindings[23]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[21]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[15]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[8]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[16]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[9]
            , (androidx.viewpager.widget.ViewPager) bindings[3]
            );
        this.pullToRefresh.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}