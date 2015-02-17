package com.ngstudio.wayphoto.ui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.ui.activities.MainActivity;
import com.ngstudio.wayphoto.ui.activities.PlacesActivity;
import com.ngstudio.wayphoto.utils.ManageLocation;


public class PlacesFragment extends BaseFragment<PlacesActivity> {

    private ListView lvPlaces;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_places;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResID(), container, false);
        lvPlaces = (ListView) rootView.findViewById(R.id.lvPlaces);
        lvPlaces.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, ManageLocation.getInstance().getListNearPlaces()));
        lvPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ManageLocation.getInstance().selectPlace(i);
                getHostActivity().startMainActivity();
            }
        });
        return rootView;
    }




}
