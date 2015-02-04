package com.ngstudio.wayphoto.ui.adapters;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ngstudio.wayphoto.R;

import java.util.Arrays;

public class ItemsAdapter extends BaseArrayAdapter<ItemsAdapter.MenuItem> {

    private static final int ID_GAP = 0;
    private static final MenuItem[] sideMenuItems = { new MenuItem(R.drawable.ic_launcher, R.string.menu_item_camera),
                                                      new MenuItem(R.drawable.ic_launcher, R.string.menu_item_map),
                                                      new MenuItem(R.drawable.ic_launcher, R.string.menu_item_map) };

    private LayoutInflater inflater;
    private int item;

    private ItemsAdapter(Context context, MenuItem[] items, int item) {
        super(context, 0, Arrays.asList(items));
        this.item = item;
        inflater = LayoutInflater.from(context);
    }

    public static ItemsAdapter getSideMenuAdapter(Context context) {
        return new ItemsAdapter(context,sideMenuItems, R.layout.item_menu);
    }

    /* public static ItemsAdapter getProfileItemsAdapter(Context context) {
        return new ItemsAdapter(context,profileItems, R.layout.item_menu);
    } */

    public ItemsAdapter(Context context, int item) {
        super(context);
        this.item = item;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if(convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(item,null);

            //holder.icon = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.text = (TextView) convertView.findViewById(R.id.tvItem);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        MenuItem item = getItem(position);

        if(item.iconId != ID_GAP) {
            //holder.icon.setImageResource(item.iconId);
            if(item.textResourceId != 0)
                holder.text.setText(item.textResourceId);
            else
                holder.text.setText(item.text);

        } else {
            //holder.icon.setImageBitmap(null);
            holder.text.setText(null);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,0,1);
            convertView.setLayoutParams(params);
        }

        return convertView;
    }

    public static final class MenuItem {

        private int iconId;
        private int textResourceId;
        private String text;

        private MenuItem(int iconId, int textResourceId) {
            this.iconId = iconId;
            this.textResourceId = textResourceId;
        }

        public MenuItem(int iconId, String text) {
            this.text = text;
            this.iconId = iconId;
        }

        public int getIconId() {
            return iconId;
        }
    }


    private static class Holder {
        //ImageView icon;
        TextView text;
    }
}
