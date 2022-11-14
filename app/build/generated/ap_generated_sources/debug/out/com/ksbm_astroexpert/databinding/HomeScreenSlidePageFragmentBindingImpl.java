package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HomeScreenSlidePageFragmentBindingImpl extends HomeScreenSlidePageFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HomeScreenSlidePageFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }
    private HomeScreenSlidePageFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatImageView) bindings[1]
            );
        this.imgBanner.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.bannerModel == variableId) {
            setBannerModel((com.ksbm_astroexpert.ui.home.BannerResponseModel.BannerModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBannerModel(@Nullable com.ksbm_astroexpert.ui.home.BannerResponseModel.BannerModel BannerModel) {
        this.mBannerModel = BannerModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.bannerModel);
        super.requestRebind();
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
        java.lang.String bannerModelSubCatImg = null;
        com.ksbm_astroexpert.ui.home.BannerResponseModel.BannerModel bannerModel = mBannerModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (bannerModel != null) {
                    // read bannerModel.subCatImg
                    bannerModelSubCatImg = bannerModel.getSubCatImg();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.ksbm_astroexpert.ui.utils.UserUtils.loadImageOnPicasso(this.imgBanner, bannerModelSubCatImg);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): bannerModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}