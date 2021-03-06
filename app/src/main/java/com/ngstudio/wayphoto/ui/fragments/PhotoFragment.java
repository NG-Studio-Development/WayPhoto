package com.ngstudio.wayphoto.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.model.PlacePhotoModel;
import com.ngstudio.wayphoto.ui.activities.MainActivity;
import com.ngstudio.wayphoto.utils.DBUtil;
import com.ngstudio.wayphoto.utils.ManageLocation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PhotoFragment extends BaseFragment<MainActivity> {

    final int REQUEST_CODE_PHOTO = 1;
    final String DIRECTORY_NAME = "FinalWayPhoto";
    File directory;

    private ImageView ivPhoto;
    private Uri resultPhotoUri;
    private View photoTabView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDirectory();
        /*getHostActivity().getFragmentTabHost().setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Log.d("PHOTO_TAB_IN_FRAGMENT","s = "+s);
            }
        });*/
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_photo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutResID(), container, false);
        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);

        //Log.d("PHOTO_TAB_IN_FRAGMENT","Tag = "+getHostActivity().getFragmentTabHost().getTag());
        photoTabView = getHostActivity().getFragmentTabHost().getCurrentTabView();
        photoTabView.findViewById(R.id.tabsLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PHOTO_TAB_IN_FRAGMENT", "TabView");
                createPhoto();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("PHOTO_TAB_IN_FRAGMENT", "onResume");
        photoTabView.findViewById(R.id.tabsLayout).setClickable(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("PHOTO_TAB_IN_FRAGMENT", "onPause");
        photoTabView.findViewById(R.id.tabsLayout).setClickable(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_PHOTO)
            if (resultCode == Activity.RESULT_OK)
                if (intent == null)
                    processResultFromUri();
                else
                    processResultFromBitmap(intent);
    }

    private void processResultFromUri() {
        ivPhoto.setImageURI(resultPhotoUri);

        if (resultPhotoUri != null) {

            Location location = getCurrentLocation();
            PlacePhotoModel placePhotoModel = null;
            if (location != null) {
                placePhotoModel = DBUtil.addPlace(getString(R.string.debug_name),
                        location.getLatitude(),
                        location.getLongitude());
            } else {
                Toast.makeText(getActivity(),"No Internet connection",Toast.LENGTH_LONG).show();
                placePhotoModel = DBUtil.addPlace(getString(R.string.debug_name));
            }

            DBUtil.addPhoto(placePhotoModel, resultPhotoUri.getPath());
        }
    }

    private void processResultFromBitmap(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object obj = intent.getExtras().get("data");
            if (obj instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) obj;
                ivPhoto.setImageBitmap(bitmap);
                Uri uri = saveImage(bitmap);
                if (uri != null) {

                    Location location = getCurrentLocation();
                    PlacePhotoModel placePhotoModel = null;
                    if (location != null) {
                        placePhotoModel = DBUtil.addPlace(getString(R.string.debug_name),
                                location.getLatitude(),
                                location.getLongitude());
                    } else {
                        Toast.makeText(getActivity(),"No Internet connection",Toast.LENGTH_LONG).show();
                        placePhotoModel = DBUtil.addPlace(getString(R.string.debug_name));
                    }

                    DBUtil.addPhoto(placePhotoModel, uri.getPath());
                }
            }
        }
    }

    public Location getCurrentLocation () {
        Location location = null;
        if (isOnline())
            location = ManageLocation.getInstance().getLastKnownLocation();
        return location;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void createPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //resultPhotoUri = generateFileUri();
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, resultPhotoUri);
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

     private Uri saveImage(Bitmap bitmap) {
        FileOutputStream out = null;
        File file = new File(directory.getPath() + "/" + "photo_"
                + System.currentTimeMillis() + ".png");
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Uri.fromFile(file);
    }

    private Uri generateFileUri() {
        File file = null;
                file = new File(directory.getPath() + "/" + "photo_"
                        + System.currentTimeMillis() + ".jpg");
        return Uri.fromFile(file);
    }

     private void createDirectory() {
        directory = new File( Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                DIRECTORY_NAME);
        if (!directory.exists())
            directory.mkdirs();
    }
}
