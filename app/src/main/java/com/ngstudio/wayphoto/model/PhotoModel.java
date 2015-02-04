package com.ngstudio.wayphoto.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.List;

@Table(name = "Photo")
public class PhotoModel extends Model implements Serializable {

    @Column(name="Place")
    public PlacePhotoModel place;

    @Column(name="Attributes")
    public String attributes;

    @Column(name="Path")
    public String path;

    public static List<PhotoModel> getAll(PlacePhotoModel place) {
        return new Select()
                .from(PhotoModel.class)
                .where("Place = ?", place.getId())
                .execute();
    }


}
