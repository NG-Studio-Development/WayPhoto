package com.ngstudio.wayphoto.ui.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.ui.fragments.GalleryFragment;
import com.ngstudio.wayphoto.ui.fragments.MapFragment;
import com.ngstudio.wayphoto.ui.fragments.PhotoFragment;

public class MainActivity extends SidebarActivity {

    ImageButton ibMap;
    ImageButton ibPhoto;
    ImageButton ibGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        ibMap = (ImageButton) findViewById(R.id.ibMap);
        ibPhoto = (ImageButton) findViewById(R.id.ibPhoto);
        ibGallery = (ImageButton) findViewById(R.id.ibGallery);

        ibMap.setOnClickListener(clickListener);
        ibPhoto.setOnClickListener(clickListener);
        ibGallery.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment;
            switch(view.getId()) {
                case R.id.ibMap:
                    fragment = new PhotoFragment();
                    break;
                case R.id.ibPhoto:
                    fragment = new MapFragment();
                    break;
                case R.id.ibGallery:
                    fragment = new GalleryFragment();
                    break;
                default:
                    return;
            }
            switchFragment(fragment,true);
        }
    };

    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

