package com.ksbm_astroexpert.databinding;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BlogItemBindingImpl extends BlogItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BlogItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private BlogItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.imageview.ShapeableImageView) bindings[1]
            , (androidx.cardview.widget.CardView) bindings[0]
            , (com.ksbm_astroexpert.view.NormalTextView) bindings[3]
            , (com.ksbm_astroexpert.view.BoldTextView) bindings[2]
            );
        this.imgBlogImage.setTag(null);
        this.item.setTag(null);
        this.txtBlogDescription.setTag(null);
        this.txtBlogTitle.setTag(null);
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
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.blogModel);
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
        com.ksbm_astroexpert.ui.blog.BlogsResponseModel.BlogModel blogModel = mBlogModel;
        android.text.Spanned htmlFromHtmlBlogModelDescription = null;
        java.lang.String blogModelBlogIcon = null;
        java.lang.String blogModelTitle = null;
        java.lang.String blogModelDescription = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (blogModel != null) {
                    // read blogModel.blogIcon
                    blogModelBlogIcon = blogModel.getBlogIcon();
                    // read blogModel.title
                    blogModelTitle = blogModel.getTitle();
                    // read blogModel.description
                    blogModelDescription = blogModel.getDescription();
                }


                // read Html.fromHtml(blogModel.description)
                htmlFromHtmlBlogModelDescription = android.text.Html.fromHtml(blogModelDescription);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.ksbm_astroexpert.ui.utils.UserUtils.loadImageOnPicasso(this.imgBlogImage, blogModelBlogIcon);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtBlogDescription, htmlFromHtmlBlogModelDescription);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtBlogTitle, blogModelTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): blogModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}