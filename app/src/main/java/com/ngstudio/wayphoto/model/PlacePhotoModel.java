package com.ngstudio.wayphoto.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.List;

@Table(name = "PlacePhoto")
public class PlacePhotoModel extends Model implements Serializable {

    @Column(name="Name")
    public String name;

    @Column(name="Count")
    public int count;

    @Column(name="Latitude")
    public double latitude;

    @Column(name="Longitude")
    public double longitude;

    public static List<PlacePhotoModel> getAll() {
        return new Select()
                .from(PlacePhotoModel.class)
                .orderBy("RANDOM()")
                .execute();
    }

    public static PlacePhotoModel newInstanceByID(long id) {

        return new Select()
                .from(PlacePhotoModel.class)
                .where("Id = ?", id)
                .executeSingle();
    }

}
