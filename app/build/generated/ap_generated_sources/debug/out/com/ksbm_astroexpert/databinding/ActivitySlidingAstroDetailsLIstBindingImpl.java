package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivitySlidingAstroDetailsLIstBindingImpl extends ActivitySlidingAstroDetailsLIstBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appbar, 1);
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.back_btn, 3);
        sViewsWithIds.put(R.id.llwallet, 4);
        sViewsWithIds.put(R.id.txtBalance, 5);
        sViewsWithIds.put(R.id.imgSupport, 6);
        sViewsWithIds.put(R.id.recyclerview, 7);
        sViewsWithIds.put(R.id.lybutton, 8);
        sViewsWithIds.put(R.id.callbtn, 9);
        sViewsWithIds.put(R.id.callme1, 10);
        sViewsWithIds.put(R.id.callmed, 11);
        sViewsWithIds.put(R.id.callme, 12);
        sViewsWithIds.put(R.id.callprice, 13);
        sViewsWithIds.put(R.id.permin, 14);
        sViewsWithIds.put(R.id.chatbtn, 15);
        sViewsWithIds.put(R.id.chatme1, 16);
        sViewsWithIds.put(R.id.chatmed, 17);
        sViewsWithIds.put(R.id.chatme, 18);
        sViewsWithIds.put(R.id.chatprice, 19);
        sViewsWithIds.put(R.id.perminchat, 20);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivitySlidingAstroDetailsLIstBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private ActivitySlidingAstroDetailsLIstBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[1]
            , (android.widget.ImageButton) bindings[3]
            , (android.widget.RelativeLayout) bindings[9]
            , (android.widget.TextView) bindings[12]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.TextView) bindings[13]
            , (android.widget.RelativeLayout) bindings[15]
            , (android.widget.TextView) bindings[18]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.TextView) bindings[19]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[6]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.RelativeLayout) bindings[0]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[20]
            , (androidx.recyclerview.widget.RecyclerView) bindings[7]
            , (androidx.appcompat.widget.Toolbar) bindings[2]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[5]
            );
        this.mainContent.setTag(null);
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