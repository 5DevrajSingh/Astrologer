package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SearchActivityBindingImpl extends SearchActivityBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.viewWallet, 2);
        sViewsWithIds.put(R.id.appbar, 3);
        sViewsWithIds.put(R.id.toolbar, 4);
        sViewsWithIds.put(R.id.imgBack, 5);
        sViewsWithIds.put(R.id.txtTitle, 6);
        sViewsWithIds.put(R.id.edtSearch, 7);
        sViewsWithIds.put(R.id.imgClose, 8);
        sViewsWithIds.put(R.id.imgSearch, 9);
        sViewsWithIds.put(R.id.rvExpertList, 10);
        sViewsWithIds.put(R.id.loaderSearchExperts, 11);
        sViewsWithIds.put(R.id.txtNoExpertData, 12);
        sViewsWithIds.put(R.id.fabFilter, 13);
        sViewsWithIds.put(R.id.d, 14);
        sViewsWithIds.put(R.id.toolbard, 15);
        sViewsWithIds.put(R.id.backarrow, 16);
        sViewsWithIds.put(R.id.txtTitled, 17);
        sViewsWithIds.put(R.id.fillter, 18);
        sViewsWithIds.put(R.id.imgSupport, 19);
        sViewsWithIds.put(R.id.vvv, 20);
        sViewsWithIds.put(R.id.header, 21);
        sViewsWithIds.put(R.id.ivSearch, 22);
        sViewsWithIds.put(R.id.etSearch, 23);
        sViewsWithIds.put(R.id.genderspiner, 24);
        sViewsWithIds.put(R.id.recharegewallet, 25);
        sViewsWithIds.put(R.id.recyclerviewastro, 26);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SearchActivityBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }
    private SearchActivityBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[3]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[16]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[14]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[7]
            , (android.widget.EditText) bindings[23]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[13]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[18]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[24]
            , (android.widget.LinearLayout) bindings[21]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[5]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[8]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[9]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[19]
            , (android.widget.ImageView) bindings[22]
            , (android.widget.ProgressBar) bindings[11]
            , (android.widget.TextView) bindings[25]
            , (androidx.recyclerview.widget.RecyclerView) bindings[26]
            , (androidx.recyclerview.widget.RecyclerView) bindings[10]
            , (androidx.appcompat.widget.Toolbar) bindings[4]
            , (androidx.appcompat.widget.Toolbar) bindings[15]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[12]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[6]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[17]
            , (android.view.View) bindings[2]
            , (android.widget.LinearLayout) bindings[20]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
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