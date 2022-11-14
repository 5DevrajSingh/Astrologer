package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SearchFilterLayoutBindingImpl extends SearchFilterLayoutBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.txtRating, 1);
        sViewsWithIds.put(R.id.cvRating, 2);
        sViewsWithIds.put(R.id.txtAllRating, 3);
        sViewsWithIds.put(R.id.txt3Plus, 4);
        sViewsWithIds.put(R.id.txt5Start, 5);
        sViewsWithIds.put(R.id.txtPricing, 6);
        sViewsWithIds.put(R.id.cvPricing, 7);
        sViewsWithIds.put(R.id.rangeSeekBar, 8);
        sViewsWithIds.put(R.id.txtMinPrice, 9);
        sViewsWithIds.put(R.id.txtMaxPrice, 10);
        sViewsWithIds.put(R.id.btnFilter, 11);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SearchFilterLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private SearchFilterLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.button.MaterialButton) bindings[11]
            , (androidx.cardview.widget.CardView) bindings[7]
            , (androidx.cardview.widget.CardView) bindings[2]
            , (com.innovattic.rangeseekbar.RangeSeekBar) bindings[8]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[4]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[5]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[3]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[10]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[9]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[6]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[1]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
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