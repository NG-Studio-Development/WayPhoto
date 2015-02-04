package com.ngstudio.wayphoto.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.utils.DBUtil;

import java.io.File;

public class MainFragment extends Fragment {

    final int REQUEST_CODE_PHOTO = 1;
    final String DIRECTORY_NAME = "FinalWayPhoto";

    File directory;
    ImageView imageView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //createDirectory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        imageView = (ImageView) view.findViewById(R.id.ivCamera);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), R.string.testing_click, Toast.LENGTH_SHORT).show();
                //createPhoto();
            }
        });
        return view;
    }

    /* @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {

                Location location = BaseMapFragment.getLastKnownLocation();
                DBUtil.addPlace(getString(R.string.debug_name),location.getLatitude(),location.getLongitude());

            } else if (resultCode == Activity.RESULT_CANCELED) {

            }
        }


    } */

    /* public void createPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri());
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    } */

    /* private Uri generateFileUri() {
        File file = null;
        file = new File(directory.getPath() + "/" + "photo_"
                        + System.currentTimeMillis() + ".jpg");
        return Uri.fromFile(file);
    } */

    /* private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                DIRECTORY_NAME);
        if (!directory.exists())
            directory.mkdirs();

    } */
}
