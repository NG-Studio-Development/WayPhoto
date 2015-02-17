package com.ngstudio.wayphoto.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.model.PlacePhotoModel;
import com.ngstudio.wayphoto.ui.activities.MainActivity;
import com.ngstudio.wayphoto.ui.activities.SidebarActivity;
import com.ngstudio.wayphoto.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends BaseMapFragment {

    MapView mapView;

    private LocationManager locationManager; // *** Refactor ***//

    /* @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SidebarActivity mainActivity = (SidebarActivity) activity;
        mainActivity.setAnimationStateListener(new SidebarActivity.AnimationStateListener() {
            @Override
            public void onAnimationRepeat(Animation animation) {  }

            @Override
            public void onAnimationStart(Animation animation) { mapView.setVisibility(View.INVISIBLE); }

            @Override
            public void onAnimationEnd(Animation animation) {
                mapView.setVisibility(View.VISIBLE);
            }
        });
    } */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setListMarkers( toMarkerOption(DBUtil.getAllPlacesWithLocation()) );
    }

    private List<MarkerOptions> toMarkerOption(List<PlacePhotoModel> listPlaces) {
        List<MarkerOptions>  listMarkerOptions = new ArrayList<>();

        for(PlacePhotoModel placePhotoModel : listPlaces) {
            listMarkerOptions.add(new MarkerOptions()
                    .position(new LatLng(placePhotoModel.latitude,
                                        placePhotoModel.longitude))
                    .title(placePhotoModel.name)
                    .snippet("Count photo = " + placePhotoModel.count));
        }

        return listMarkerOptions;
    }
}
