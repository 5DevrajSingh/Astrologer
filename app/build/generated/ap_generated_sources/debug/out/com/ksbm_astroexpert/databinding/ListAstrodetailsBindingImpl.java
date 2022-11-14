package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListAstrodetailsBindingImpl extends ListAstrodetailsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(21);
        sIncludes.setIncludes(1, 
            new String[] {"content_profile"},
            new int[] {2},
            new int[] {com.ksbm_astroexpert.R.layout.content_profile});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appbar, 3);
        sViewsWithIds.put(R.id.toolbars, 4);
        sViewsWithIds.put(R.id.toolbar, 5);
        sViewsWithIds.put(R.id.back_btn, 6);
        sViewsWithIds.put(R.id.scrollview, 7);
        sViewsWithIds.put(R.id.imgExpert, 8);
        sViewsWithIds.put(R.id.ll_name, 9);
        sViewsWithIds.put(R.id.astroname, 10);
        sViewsWithIds.put(R.id.astrolang, 11);
        sViewsWithIds.put(R.id.experties, 12);
        sViewsWithIds.put(R.id.online, 13);
        sViewsWithIds.put(R.id.recyclerviewplans, 14);
        sViewsWithIds.put(R.id.following_layout, 15);
        sViewsWithIds.put(R.id.comments, 16);
        sViewsWithIds.put(R.id.fans_layout, 17);
        sViewsWithIds.put(R.id.calls, 18);
        sViewsWithIds.put(R.id.reports, 19);
        sViewsWithIds.put(R.id.astroexp, 20);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListAstrodetailsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private ListAstrodetailsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.appbar.AppBarLayout) bindings[3]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (android.widget.ImageButton) bindings[6]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[16]
            , (com.ksbm_astroexpert.databinding.ContentProfileBinding) bindings[2]
            , (android.widget.TextView) bindings[12]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[15]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[8]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.RelativeLayout) bindings[0]
            , (android.widget.TextView) bindings[13]
            , (androidx.recyclerview.widget.RecyclerView) bindings[14]
            , (android.widget.TextView) bindings[19]
            , (androidx.core.widget.NestedScrollView) bindings[7]
            , (androidx.appcompat.widget.Toolbar) bindings[5]
            , (androidx.appcompat.widget.Toolbar) bindings[4]
            );
        setContainedBinding(this.contentprofile);
        this.mainContent.setTag(null);
        this.mboundView1 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        contentprofile.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (contentprofile.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        contentprofile.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeContentprofile((com.ksbm_astroexpert.databinding.ContentProfileBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeContentprofile(com.ksbm_astroexpert.databinding.ContentProfileBinding Contentprofile, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        executeBindingsOn(contentprofile);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): contentprofile
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}