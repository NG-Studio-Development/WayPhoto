package com.ngstudio.wayphoto.utils;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.annotation.Column;
import com.activeandroid.query.Select;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ngstudio.wayphoto.model.PhotoModel;
import com.ngstudio.wayphoto.model.PlacePhotoModel;

import java.util.List;

public class DBUtil {
    final static String LOG_DB_UTIL = "log_db_util";

    public static PlacePhotoModel addPlace(String name, double latitude, double longitude) {

        PlacePhotoModel placePhotoModel = getByLatLng(latitude, longitude);

        if (placePhotoModel == null) {
                placePhotoModel = new PlacePhotoModel();
                placePhotoModel.name = name;
                placePhotoModel.count = 1;
                placePhotoModel.latitude = latitude;
                placePhotoModel.longitude = longitude;
        } else {
                placePhotoModel.count += 1;
            }
        placePhotoModel.save();

        return placePhotoModel;
    }

    public static PlacePhotoModel addPlace(String name) {
        PlacePhotoModel placePhotoModel = new PlacePhotoModel();
        placePhotoModel.name = name;
        placePhotoModel.count = 1;
        placePhotoModel.latitude = Double.MAX_VALUE;
        placePhotoModel.longitude = Double.MAX_VALUE;
        placePhotoModel.save();

        return placePhotoModel;
    }

    public static long addPhoto(PlacePhotoModel placePhotoModel, String path ) {
        PhotoModel photoModel = new PhotoModel();
        photoModel.place = placePhotoModel;
        photoModel.path = path;

        return photoModel.save();
    }

    /*public static List<PhotoModel> getAllPhotoByPlace(PlacePhotoModel place) {
        return new Select()
                .from(PhotoModel.class)
                .where("Place = ?", place.getId())
                .execute();
    } */

    private static PlacePhotoModel getByLatLng(double latitude, double longitude) {
        return new Select()
                .from(PlacePhotoModel.class)
                .where("Latitude = ? AND Longitude = ?", latitude, longitude)
                .executeSingle();
    }

    public static List<PlacePhotoModel> getAllPlacesWithLocation() {
        return new Select()
                .from(PlacePhotoModel.class)
                .where("Latitude < ? AND Longitude < ?", Double.MAX_VALUE, Double.MAX_VALUE)
                .execute();
    }

    public static List<PlacePhotoModel> getAllPlaces() {
        return PlacePhotoModel.getAll();
    }
}
