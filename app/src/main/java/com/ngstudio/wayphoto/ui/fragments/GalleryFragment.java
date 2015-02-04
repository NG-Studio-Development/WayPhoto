package com.ngstudio.wayphoto.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.model.PlacePhotoModel;
import com.ngstudio.wayphoto.ui.adapters.GalleryItemAdapter;
import com.ngstudio.wayphoto.utils.DBUtil;

public class GalleryFragment extends BaseFragment {


    private GalleryItemAdapter adapter;

    @Override
    public int getLayoutResID() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        adapter = new GalleryItemAdapter(getActivity(), R.layout.item_gallery, DBUtil.getAllPlaces());
        GridView gridView =  (GridView) view.findViewById(R.id.gvItemGallery);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlacePhotoModel place = (PlacePhotoModel) adapterView.getAdapter().getItem(i);
                PicturesGalleryFragment fragment = PicturesGalleryFragment.newInstance(place);
                getHostActivity().switchFragment(fragment,true);
            }
        });
        return view;
    }
}
