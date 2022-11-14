package com.ksbm_astroexpert.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.ksbm_astroexpert.R;
import com.mikepenz.materialdrawer.model.BaseDescribeableDrawerItem;
import com.mikepenz.materialdrawer.model.BaseViewHolder;

import java.util.List;

public class DrawerItem extends BaseDescribeableDrawerItem<DrawerItem, DrawerItem.ViewHolder> {

    private int textResId;
    private int drawableResId;
    private boolean isSubMenuItem;

    DrawerItem isSubMenu(boolean isSubMenuItem){
        this.isSubMenuItem = isSubMenuItem;
        return this;
    }

    DrawerItem withText(int textResId) {
        this.textResId = textResId;
        return this;
    }

    DrawerItem witIcon(int drawableResId) {
        this.drawableResId = drawableResId;
        return this;
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.material_drawer_item_expandable;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.material_drawer_item_expandable;
    }

    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        super.bindView(viewHolder, payloads);
        Context context = viewHolder.itemView.getContext();
        viewHolder.material_drawer_name.setText(textResId);
        viewHolder.material_drawer_icon.setImageResource(drawableResId);
        if(isSubMenuItem){
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.subMenuItemBg));
        }else{
            viewHolder.itemView.setBackgroundResource(0);
        }
        onPostBindView(this, viewHolder.itemView);
    }

    static class ViewHolder extends BaseViewHolder {

        private TextView material_drawer_name;
        private ImageView material_drawer_icon;


        private ViewHolder(View v) {
            super(v);
            material_drawer_name = v.findViewById(R.id.material_drawer_name);
            material_drawer_icon = v.findViewById(R.id.material_drawer_icon);
        }
    }
}
