package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class OtpVerificationActivityBindingImpl extends OtpVerificationActivityBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.backarrow, 2);
        sViewsWithIds.put(R.id.txtTitle, 3);
        sViewsWithIds.put(R.id.textView, 4);
        sViewsWithIds.put(R.id.txtPhoneNo, 5);
        sViewsWithIds.put(R.id.otp_view, 6);
        sViewsWithIds.put(R.id.loaderResend, 7);
        sViewsWithIds.put(R.id.txtTimer, 8);
        sViewsWithIds.put(R.id.txtResend, 9);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public OtpVerificationActivityBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private OtpVerificationActivityBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatImageView) bindings[2]
            , (android.widget.ProgressBar) bindings[7]
            , (in.aabhasjindal.otptextview.OtpTextView) bindings[6]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[4]
            , (androidx.appcompat.widget.Toolbar) bindings[1]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[5]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[9]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[8]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[3]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
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