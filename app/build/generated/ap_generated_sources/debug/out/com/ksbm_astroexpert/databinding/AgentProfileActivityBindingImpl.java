package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AgentProfileActivityBindingImpl extends AgentProfileActivityBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appbar, 5);
        sViewsWithIds.put(R.id.ctToolbarLayout, 6);
        sViewsWithIds.put(R.id.rating, 7);
        sViewsWithIds.put(R.id.imgBack, 8);
        sViewsWithIds.put(R.id.imgShare, 9);
        sViewsWithIds.put(R.id.imgvExperiance, 10);
        sViewsWithIds.put(R.id.tvExpertExperianceHeader, 11);
        sViewsWithIds.put(R.id.deviderExpertExperiance, 12);
        sViewsWithIds.put(R.id.imgConsultationCharges, 13);
        sViewsWithIds.put(R.id.tvConsulationChargeHeader, 14);
        sViewsWithIds.put(R.id.dvdrConsulationCharge, 15);
        sViewsWithIds.put(R.id.imgTimings, 16);
        sViewsWithIds.put(R.id.tvTimingsHeader, 17);
        sViewsWithIds.put(R.id.tvTimings, 18);
        sViewsWithIds.put(R.id.dvdrTimings, 19);
        sViewsWithIds.put(R.id.imgReviews, 20);
        sViewsWithIds.put(R.id.tvReviewsHeader, 21);
        sViewsWithIds.put(R.id.tvReviews, 22);
        sViewsWithIds.put(R.id.dvdrReviews, 23);
        sViewsWithIds.put(R.id.imgLocation, 24);
        sViewsWithIds.put(R.id.tvLocationHeader, 25);
        sViewsWithIds.put(R.id.tvLocation, 26);
        sViewsWithIds.put(R.id.dvdrLocation, 27);
        sViewsWithIds.put(R.id.imgSpokenLanguage, 28);
        sViewsWithIds.put(R.id.tvSpokenLanguageHeader, 29);
        sViewsWithIds.put(R.id.tvSpokenLanguage, 30);
        sViewsWithIds.put(R.id.dvdrSpokenLanguage, 31);
        sViewsWithIds.put(R.id.imgExpertise, 32);
        sViewsWithIds.put(R.id.tvExpertiseHeader, 33);
        sViewsWithIds.put(R.id.tvExpertise, 34);
        sViewsWithIds.put(R.id.dvdrExpertise, 35);
        sViewsWithIds.put(R.id.imgProfileSummary, 36);
        sViewsWithIds.put(R.id.tvProfileSummaryHeader, 37);
        sViewsWithIds.put(R.id.tvProfileSummary, 38);
        sViewsWithIds.put(R.id.dvdrProfileSummary, 39);
        sViewsWithIds.put(R.id.fabChat, 40);
        sViewsWithIds.put(R.id.fabCall, 41);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AgentProfileActivityBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 42, sIncludes, sViewsWithIds));
    }
    private AgentProfileActivityBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[5]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[6]
            , (android.view.View) bindings[12]
            , (android.view.View) bindings[15]
            , (android.view.View) bindings[35]
            , (android.view.View) bindings[27]
            , (android.view.View) bindings[39]
            , (android.view.View) bindings[23]
            , (android.view.View) bindings[31]
            , (android.view.View) bindings[19]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[41]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[40]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[8]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[13]
            , (com.google.android.material.imageview.ShapeableImageView) bindings[1]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[32]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[24]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[36]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[20]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[9]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[28]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[16]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[10]
            , (androidx.appcompat.widget.AppCompatRatingBar) bindings[7]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[3]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[4]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[14]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[11]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[34]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[33]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[26]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[25]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[38]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[37]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[22]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[21]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[30]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[29]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[18]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[17]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[2]
            );
        this.imgExpert.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAstroExperiance.setTag(null);
        this.tvConsulationCharge.setTag(null);
        this.txtAstrologerName.setTag(null);
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
        java.lang.String relatedVendorExp = null;
        java.lang.String relatedVendorOwnerName = null;
        java.lang.String relatedVendorStartingPrice = null;
        com.ksbm_astroexpert.ui.home.ExpertsResponseModel.RelatedVendor relatedVendor = mRelatedVendor;
        java.lang.String relatedVendorVendorImage = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (relatedVendor != null) {
                    // read relatedVendor.exp
                    relatedVendorExp = relatedVendor.getExp();
                    // read relatedVendor.ownerName
                    relatedVendorOwnerName = relatedVendor.getOwnerName();
                    // read relatedVendor.startingPrice
                    relatedVendorStartingPrice = relatedVendor.getStartingPrice();
                    // read relatedVendor.vendorImage
                    relatedVendorVendorImage = relatedVendor.getVendorImage();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.ksbm_astroexpert.ui.utils.UserUtils.loadImageOnPicasso(this.imgExpert, relatedVendorVendorImage);
            com.ksbm_astroexpert.ui.utils.UserUtils.setExpertExp(this.tvAstroExperiance, relatedVendorExp, tvAstroExperiance.getResources().getString(R.string.Dot));
            com.ksbm_astroexpert.ui.utils.UserUtils.setExpertPrice(this.tvConsulationCharge, relatedVendorStartingPrice, tvConsulationCharge.getResources().getString(R.string.Dot));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtAstrologerName, relatedVendorOwnerName);
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