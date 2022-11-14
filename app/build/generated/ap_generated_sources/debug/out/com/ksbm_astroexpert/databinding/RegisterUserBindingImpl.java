package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class RegisterUserBindingImpl extends RegisterUserBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.skip, 1);
        sViewsWithIds.put(R.id.textsign, 2);
        sViewsWithIds.put(R.id.textsign1, 3);
        sViewsWithIds.put(R.id.name, 4);
        sViewsWithIds.put(R.id.lastname, 5);
        sViewsWithIds.put(R.id.email, 6);
        sViewsWithIds.put(R.id.mobile, 7);
        sViewsWithIds.put(R.id.dob, 8);
        sViewsWithIds.put(R.id.timeofbith, 9);
        sViewsWithIds.put(R.id.placeofbirth, 10);
        sViewsWithIds.put(R.id.radiogroup, 11);
        sViewsWithIds.put(R.id.male, 12);
        sViewsWithIds.put(R.id.female, 13);
        sViewsWithIds.put(R.id.register, 14);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public RegisterUserBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private RegisterUserBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[8]
            , (android.widget.EditText) bindings[6]
            , (android.widget.RadioButton) bindings[13]
            , (android.widget.EditText) bindings[5]
            , (android.widget.RadioButton) bindings[12]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[10]
            , (android.widget.RadioGroup) bindings[11]
            , (android.widget.Button) bindings[14]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[9]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
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