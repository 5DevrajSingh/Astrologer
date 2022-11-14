package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BlogDetailActivityBindingImpl extends BlogDetailActivityBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(6);
        sIncludes.setIncludes(0, 
            new String[] {"actionbar_layout"},
            new int[] {4},
            new int[] {com.ksbm_astroexpert.R.layout.actionbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.fabShare, 5);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BlogDetailActivityBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private BlogDetailActivityBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.ksbm_astroexpert.databinding.ActionbarLayoutBinding) bindings[4]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[5]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[1]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[3]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[2]
            );
        setContainedBinding(this.actionbar);
        this.imgBlogImage.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtBlogDescription.setTag(null);
        this.txtBlogTitle.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        actionbar.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (actionbar.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.blogModel == variableId) {
            setBlogModel((com.ksbm_astroexpert.ui.blog.BlogsResponseModel.BlogModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBlogModel(@Nullable com.ksbm_astroexpert.ui.blog.BlogsResponseModel.BlogModel BlogModel) {
        this.mBlogModel = BlogModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.blogModel);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        actionbar.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActionbar((com.ksbm_astroexpert.databinding.ActionbarLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeActionbar(com.ksbm_astroexpert.databinding.ActionbarLayoutBinding Actionbar, int fieldId) {
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
        com.ksbm_astroexpert.ui.blog.BlogsResponseModel.BlogModel blogModel = mBlogModel;
        android.text.Spanned htmlFromHtmlBlogModelDescription = null;
        java.lang.String blogModelTitle = null;
        java.lang.String blogModelBlogIcon = null;
        java.lang.String blogModelDescription = null;

        if ((dirtyFlags & 0x6L) != 0) {



                if (blogModel != null) {
                    // read blogModel.title
                    blogModelTitle = blogModel.getTitle();
                    // read blogModel.blogIcon
                    blogModelBlogIcon = blogModel.getBlogIcon();
                    // read blogModel.description
                    blogModelDescription = blogModel.getDescription();
                }


                // read Html.fromHtml(blogModel.description)
                htmlFromHtmlBlogModelDescription = android.text.Html.fromHtml(blogModelDescription);
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            com.ksbm_astroexpert.ui.utils.UserUtils.loadImageOnPicasso(this.imgBlogImage, blogModelBlogIcon);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtBlogDescription, htmlFromHtmlBlogModelDescription);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtBlogTitle, blogModelTitle);
        }
        executeBindingsOn(actionbar);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): actionbar
        flag 1 (0x2L): blogModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}