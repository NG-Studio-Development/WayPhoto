package com.ngstudio.wayphoto.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.model.PlacePhotoModel;
import java.util.List;

public class GalleryItemAdapter extends BaseArrayAdapter<PlacePhotoModel> {

    private LayoutInflater inflater;

    public GalleryItemAdapter(Context context, int style, List<PlacePhotoModel> list) {
        super(context, style, list);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GalleryHolder holder;
        Log.d("SCROLL", "Position = " + position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gallery, parent, false);
            holder = holderInitialise(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GalleryHolder) convertView.getTag();
        }

        PlacePhotoModel place = getItem(position);
        holder.tvName.setText(place.name);

        return convertView;
    }

    @Override
    public PlacePhotoModel getItem(int position) {
        return super.getItem(position);
    }

    private GalleryHolder holderInitialise(View view) {
        GalleryHolder holder = new GalleryHolder();
        holder.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
        holder.tvName = (TextView) view.findViewById(R.id.tvName);
        //holder.view = view.findViewById(R.id.chatDivider);
        return holder;
    }

    static class GalleryHolder {
        ImageView ivIcon;
        TextView tvName;
        //View view;
    }
}
