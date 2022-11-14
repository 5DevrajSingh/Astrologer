package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HomeMagicalRemediesItemBindingImpl extends HomeMagicalRemediesItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cvRemedies, 3);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HomeMagicalRemediesItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private HomeMagicalRemediesItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[3]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[2]
            );
        this.imgRemedies.setTag(null);
        this.item.setTag(null);
        this.txtCaption.setTag(null);
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
        if (BR.remediesModel == variableId) {
            setRemediesModel((com.ksbm_astroexpert.ui.home.RemediesResponseModel.RemediesModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRemediesModel(@Nullable com.ksbm_astroexpert.ui.home.RemediesResponseModel.RemediesModel RemediesModel) {
        this.mRemediesModel = RemediesModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.remediesModel);
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
        java.lang.String remediesModelTitle = null;
        java.lang.String remediesModelIcon = null;
        com.ksbm_astroexpert.ui.home.RemediesResponseModel.RemediesModel remediesModel = mRemediesModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (remediesModel != null) {
                    // read remediesModel.title
                    remediesModelTitle = remediesModel.getTitle();
                    // read remediesModel.icon
                    remediesModelIcon = remediesModel.getIcon();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.ksbm_astroexpert.ui.utils.UserUtils.loadImageOnPicasso(this.imgRemedies, remediesModelIcon);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtCaption, remediesModelTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): remediesModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}