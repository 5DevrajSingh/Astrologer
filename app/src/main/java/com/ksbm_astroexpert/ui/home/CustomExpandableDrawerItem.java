package com.ksbm_astroexpert.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.ksbm_astroexpert.R;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.holder.ColorHolder;
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont;
import com.mikepenz.materialdrawer.model.AbstractDrawerItem;
import com.mikepenz.materialdrawer.model.BaseDescribeableDrawerItem;
import com.mikepenz.materialdrawer.model.BaseViewHolder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

public class CustomExpandableDrawerItem extends BaseDescribeableDrawerItem<CustomExpandableDrawerItem, CustomExpandableDrawerItem.ViewHolder> {

    private boolean isArrowHide = true;
    private boolean isSubMenuItem = false;
    private Drawer.OnDrawerItemClickListener mOnDrawerItemClickListener;

    private ColorHolder arrowColor;

    private int arrowRotationAngleStart = 0;

    private int arrowRotationAngleEnd = 180;

    public CustomExpandableDrawerItem withArrowHide(boolean isArrowHide) {
        this.isArrowHide = isArrowHide;
        return this;
    }

    CustomExpandableDrawerItem witSubMenu(boolean isSubMenuItem) {
        this.isSubMenuItem = isSubMenuItem;
        return this;
    }

    public CustomExpandableDrawerItem withArrowColor(@ColorInt int arrowColor) {
        this.arrowColor = ColorHolder.fromColor(arrowColor);
        return this;
    }

    public CustomExpandableDrawerItem withArrowColorRes(@ColorRes int arrowColorRes) {
        this.arrowColor = ColorHolder.fromColorRes(arrowColorRes);
        return this;
    }

    public CustomExpandableDrawerItem withArrowRotationAngleStart(int angle) {
        this.arrowRotationAngleStart = angle;
        return this;
    }

    public CustomExpandableDrawerItem withArrowRotationAngleEnd(int angle) {
        this.arrowRotationAngleEnd = angle;
        return this;
    }

    @Override
    public int getType() {
        return R.id.drawer_item_layout;
    }

    @Override
    @LayoutRes
    public int getLayoutRes() {
        return R.layout.drawer_item_layout;
    }

    @Override
    public CustomExpandableDrawerItem withOnDrawerItemClickListener(Drawer.OnDrawerItemClickListener onDrawerItemClickListener) {
        mOnDrawerItemClickListener = onDrawerItemClickListener;
        return this;
    }

    @Override
    public Drawer.OnDrawerItemClickListener getOnDrawerItemClickListener() {
        return mOnArrowDrawerItemClickListener;
    }

    /**
     * our internal onDrawerItemClickListener which will handle the arrow animation
     */
    private Drawer.OnDrawerItemClickListener mOnArrowDrawerItemClickListener = new Drawer.OnDrawerItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            if (drawerItem instanceof AbstractDrawerItem && drawerItem.isEnabled()) {
                if (((AbstractDrawerItem) drawerItem).getSubItems() != null) {
                    if (((AbstractDrawerItem) drawerItem).isExpanded()) {
                        ViewCompat.animate(view.findViewById(R.id.material_drawer_arrow)).rotation(CustomExpandableDrawerItem.this.arrowRotationAngleEnd).start();
                    } else {
                        ViewCompat.animate(view.findViewById(R.id.material_drawer_arrow)).rotation(CustomExpandableDrawerItem.this.arrowRotationAngleStart).start();
                    }
                }
            }

            return mOnDrawerItemClickListener != null && mOnDrawerItemClickListener.onItemClick(view, position, drawerItem);
        }
    };

    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        super.bindView(viewHolder, payloads);

        Context ctx = viewHolder.itemView.getContext();
        //bind the basic view parts
        bindViewHelper(viewHolder);
//        viewHolder.material_drawer_name.setText(getName().getTextRes());
        viewHolder.material_drawer_name.setTextColor(ContextCompat.getColor(ctx, R.color.black));
        if (viewHolder.material_drawer_icon.getVisibility() == View.GONE) {
            viewHolder.material_drawer_icon.setVisibility(View.INVISIBLE);
        }
        if (isSubMenuItem) {
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(ctx, R.color.subMenuItemBg));
        } else {
            viewHolder.itemView.setBackgroundResource(0);
        }

        if (isArrowHide) {
            viewHolder.arrow.setVisibility(View.GONE);
        } else {
            viewHolder.arrow.setVisibility(View.VISIBLE);
            //make sure all animations are stopped
            if (viewHolder.arrow.getDrawable() instanceof IconicsDrawable) {
                ((IconicsDrawable) viewHolder.arrow.getDrawable()).color(this.arrowColor != null ? this.arrowColor.color(ctx) : getIconColor(ctx));
            }
            viewHolder.arrow.clearAnimation();
            if (!isExpanded()) {
                viewHolder.arrow.setRotation(this.arrowRotationAngleStart);
            } else {
                viewHolder.arrow.setRotation(this.arrowRotationAngleEnd);
            }
        }
        //call the onPostBindView method to trigger post bind view actions (like the listener to modify the item if required)
        onPostBindView(this, viewHolder.itemView);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    public static class ViewHolder extends BaseViewHolder {
        public ImageView arrow;
        public ImageView material_drawer_icon;
        public TextView material_drawer_name;

        public ViewHolder(View view) {
            super(view);
            material_drawer_icon = view.findViewById(R.id.material_drawer_icon);
            material_drawer_name = view.findViewById(R.id.material_drawer_name);
            arrow = view.findViewById(R.id.material_drawer_arrow);
            arrow.setImageDrawable(new IconicsDrawable(view.getContext(), MaterialDrawerFont.Icon.mdf_expand_more).sizeDp(16).paddingDp(2).color(Color.BLACK));
        }
    }
}

