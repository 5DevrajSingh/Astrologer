package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SignInActivityBindingImpl extends SignInActivityBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imgClose, 1);
        sViewsWithIds.put(R.id.txtSignUp, 2);
        sViewsWithIds.put(R.id.txtLogin, 3);
        sViewsWithIds.put(R.id.txtLoginMsg, 4);
        sViewsWithIds.put(R.id.edtMobileNumber, 5);
        sViewsWithIds.put(R.id.txtErrMobile, 6);
        sViewsWithIds.put(R.id.tncll, 7);
        sViewsWithIds.put(R.id.tnccheck, 8);
        sViewsWithIds.put(R.id.txtcheckbox, 9);
        sViewsWithIds.put(R.id.btnLogin, 10);
        sViewsWithIds.put(R.id.loaderSignin, 11);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SignInActivityBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private SignInActivityBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.button.MaterialButton) bindings[10]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[5]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[1]
            , (android.widget.ProgressBar) bindings[11]
            , (android.widget.CheckBox) bindings[8]
            , (android.widget.LinearLayout) bindings[7]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[6]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[3]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[4]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[2]
            , (android.widget.TextView) bindings[9]
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