package com.ngstudio.wayphoto.ui.activities;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.ui.adapters.ItemsAdapter;
import com.ngstudio.wayphoto.ui.fragments.GalleryFragment;
import com.ngstudio.wayphoto.ui.fragments.MapFragment;
import com.ngstudio.wayphoto.ui.fragments.PhotoFragment;
import com.ngstudio.wayphoto.ui.widgets.AdapterLinearLayout;

public class MainActivity extends SidebarActivity {

    private AdapterLinearLayout sliderMenu;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initializeMenu(R.id.lnSliderMenu);
        initializeContent(R.id.content);

        sliderMenu = (AdapterLinearLayout) findViewById(R.id.lnSliderMenu);
        sliderMenu.setAdapter(adapter = ItemsAdapter.getSideMenuAdapter(this));
        sliderMenu.setOnItemClickListener(new AdapterLinearLayout.OnItemClickListener() {
            @Override
            public void onItemClick(Adapter adapter, int pos, View v) {
                selectItem(pos);
            }
        });

        /*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //txtLat = (TextView) findViewById(R.id.textview1);
                int i = 0;
                Log.d("MAP_CURRENT_LOCATION", "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Latitude","disable");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("Latitude","enable");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("Latitude","status");
            }
        }); */

        selectItem(1);
    }

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



    private void selectItem(int position) {
        Fragment fragment;

        if(!sliderMenu.setSelected(position))
            return;

        ItemsAdapter.MenuItem item = adapter.getItem(position);

        switch (position) {
            case 0:
                fragment = new PhotoFragment();

                break;
            case 1:
                fragment = new MapFragment();
                break;
            case 2:
                fragment = new GalleryFragment();
                break;
            default:
                return;
        }

        /*switch(item.getIconId()) {
            case R.drawable.drawable_item_menu_map:
                fragment = FragmentPool.getInstance().newObject(MapFragment.class);
                break;

            case R.drawable.drawable_item_menu_contacts:
                fragment = FragmentPool.getInstance().newObject(ContactsFragment.class);
                break;

            case R.drawable.drawable_item_requests:
                fragment = FragmentPool.getInstance().newObject(RequestFragment.class);
                break;

            case R.drawable.drawable_item_menu_settings:
                fragment = FragmentPool.getInstance().newObject(SettingsFragment.class);
                break;
            default:
                return;
        } */

        switchFragment(fragment,true);
        setStateAnimation(false);
    }




}

