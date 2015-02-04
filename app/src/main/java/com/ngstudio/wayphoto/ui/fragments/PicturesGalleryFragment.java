package com.ngstudio.wayphoto.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ngstudio.wayphoto.Constants;
import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.model.PhotoModel;
//import com.ngstudio.wayphoto.ui.activities.BaseActivity;
import com.ngstudio.wayphoto.model.PlacePhotoModel;
import com.ngstudio.wayphoto.ui.activities.BaseActivity;
import com.ngstudio.wayphoto.ui.activities.ImageActivity;
import com.ngstudio.wayphoto.ui.adapters.ImageGalleryItemAdapter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.List;

public class PicturesGalleryFragment extends Fragment {
    //private static String LIST_PHOTO = "listPhoto";

    private static List<PhotoModel> listPhoto; // Delete later //
    public static String PLACE_MODEL = "place_model";
    public static String PLACE_MODEL_ID = "place_model_id";

    private ImageGalleryItemAdapter adapter;
    public static PicturesGalleryFragment newInstance (PlacePhotoModel place /*List<PhotoModel> listPhoto*/) {
        PicturesGalleryFragment fragment = new PicturesGalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLACE_MODEL,place);
        fragment.setArguments(bundle);
        //fragment.listPhoto = listPhoto;
        return fragment;
    }

    public PicturesGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        //ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getActivity()));

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

        PlacePhotoModel place = (PlacePhotoModel) getArguments().get(PLACE_MODEL);
        listPhoto = PhotoModel.getAll(place);
        adapter = new ImageGalleryItemAdapter(getActivity(), R.layout.item_gallery, listPhoto);
        GridView gridView =  (GridView) view.findViewById(R.id.gvItemGallery);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startImagePagerActivity(i);
            }
        });
        return view;
    }

    protected void startImagePagerActivity(int position) {
        Intent intent = new Intent(getActivity(), ImageActivity.class);
        long id = ((PlacePhotoModel)getArguments().get(PLACE_MODEL)).getId();
        intent.putExtra(PLACE_MODEL_ID, id);
        intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
        intent.putExtra(Constants.Extra.IMAGE_POSITION, position);
        startActivity(intent);
    }



    //public static final int INDEX = 1;

}
