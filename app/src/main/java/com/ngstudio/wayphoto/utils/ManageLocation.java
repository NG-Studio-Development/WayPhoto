package com.ngstudio.wayphoto.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.ngstudio.wayphoto.WayPhotoApplication;

import java.util.ArrayList;
import java.util.List;

public class ManageLocation {

    public static ManageLocation getInstance() { return ManageLocationHolder.HOLDER_INSTANCE; }

    public static class ManageLocationHolder {
        public static final ManageLocation HOLDER_INSTANCE = new ManageLocation();
    }

    private List<String> debugPlacesArray = new ArrayList<>();
    private boolean selectedState;
    private String selectedPlace;

    private ManageLocation() {
        selectedState = false;
        debugInitializing();
    }

    public Location getLastKnownLocation() {

        LocationManager mLocationManager = (LocationManager) WayPhotoApplication.getAppContext().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);

            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }


    public List<String> getListNearPlaces() { return debugPlacesArray; }

    public void selectPlace(int number) {
        selectedPlace = debugPlacesArray.get(number);
        selectedState = true;
    }

   // *** Method only for debug *** //
   private void debugInitializing() {
       debugPlacesArray.add("Place_1");
       debugPlacesArray.add("Place_2");
       debugPlacesArray.add("Place_3");
       debugPlacesArray.add("Place_4");
       debugPlacesArray.add("Place_5");
   }
}
