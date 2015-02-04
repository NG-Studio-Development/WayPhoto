package com.ngstudio.wayphoto.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.model.PhotoModel;
import com.ngstudio.wayphoto.model.PlacePhotoModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

public class ImageGalleryItemAdapter extends BaseArrayAdapter<PhotoModel> {

    private LayoutInflater inflater;

    public ImageGalleryItemAdapter(Context context, int style, List<PhotoModel> list) {
        super(context, style, list);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PhotoModel getItem(int position) {
        return super.getItem(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ImageGalleryHolder holder;
        PhotoModel photo = getItem(position);
        //Log.d("SCROLL", "Position = " + position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gallery, parent, false);
            holder = holderInitialise(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ImageGalleryHolder) convertView.getTag();
        }

        String path = "file://"+photo.path;
        holder.tvName.setText(photo.attributes);
        Log.d("IMAGE_ADAPTER","Path = "+photo.path);
        //ImageLoader.getInstance().displayImage(photo.path, holder.ivIcon);
        ImageLoader.getInstance().displayImage(path, holder.ivIcon);

        return convertView;
    }

    private ImageGalleryHolder holderInitialise(View view) {
        ImageGalleryHolder holder = new ImageGalleryHolder();
        holder.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
        holder.tvName = (TextView) view.findViewById(R.id.tvName);
        //holder.view = view.findViewById(R.id.chatDivider);
        return holder;
    }

    static class ImageGalleryHolder {
        ImageView ivIcon;
        TextView tvName;
        //View view;
    }
}
