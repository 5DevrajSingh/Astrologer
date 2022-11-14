package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HomeOurExpertsItemBindingImpl extends HomeOurExpertsItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.flrating, 7);
        sViewsWithIds.put(R.id.txtExpertLang, 8);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HomeOurExpertsItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private HomeOurExpertsItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (com.google.android.material.imageview.ShapeableImageView) bindings[1]
            , (androidx.cardview.widget.CardView) bindings[0]
            , (androidx.appcompat.widget.AppCompatRatingBar) bindings[2]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[5]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[8]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[4]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[3]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[6]
            );
        this.imgExpert.setTag(null);
        this.item.setTag(null);
        this.rating.setTag(null);
        this.txtExpertExp.setTag(null);
        this.txtExpertName.setTag(null);
        this.txtExpertRating.setTag(null);
        this.txtRate.setTag(null);
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
        if (BR.relatedVendor == variableId) {
            setRelatedVendor((com.ksbm_astroexpert.ui.home.ExpertsResponseModel.RelatedVendor) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRelatedVendor(@Nullable com.ksbm_astroexpert.ui.home.ExpertsResponseModel.RelatedVendor RelatedVendor) {
        this.mRelatedVendor = RelatedVendor;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.relatedVendor);
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
        com.ksbm_astroexpert.ui.home.ExpertsResponseModel.RelatedVendor relatedVendor = mRelatedVendor;
        java.lang.String relatedVendorOwnerName = null;
        java.lang.String relatedVendorStartingPrice = null;
        java.lang.String relatedVendorVendorImage = null;
        java.lang.String relatedVendorAvgRating = null;
        java.lang.String relatedVendorExp = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (relatedVendor != null) {
                    // read relatedVendor.ownerName
                    relatedVendorOwnerName = relatedVendor.getOwnerName();
                    // read relatedVendor.startingPrice
                    relatedVendorStartingPrice = relatedVendor.getStartingPrice();
                    // read relatedVendor.vendorImage
                    relatedVendorVendorImage = relatedVendor.getVendorImage();
                    // read relatedVendor.avgRating
                    relatedVendorAvgRating = relatedVendor.getAvgRating();
                    // read relatedVendor.exp
                    relatedVendorExp = relatedVendor.getExp();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.ksbm_astroexpert.ui.utils.UserUtils.loadImageOnPicasso(this.imgExpert, relatedVendorVendorImage);
            com.ksbm_astroexpert.ui.utils.UserUtils.setExpertRating(this.rating, relatedVendorAvgRating);
            com.ksbm_astroexpert.ui.utils.UserUtils.setExpertExp(this.txtExpertExp, relatedVendorExp, txtExpertExp.getResources().getString(R.string.new_));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtExpertName, relatedVendorOwnerName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtExpertRating, relatedVendorAvgRating);
            com.ksbm_astroexpert.ui.utils.UserUtils.setExpertPrice(this.txtRate, relatedVendorStartingPrice, txtRate.getResources().getString(R.string.Dot));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): relatedVendor
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}