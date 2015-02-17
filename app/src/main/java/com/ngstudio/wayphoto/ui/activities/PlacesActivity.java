package com.ngstudio.wayphoto.ui.activities;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.ui.fragments.PlacesFragment;

public class PlacesActivity extends BaseActivity {




    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        if (savedInstanceState == null)
            addFragment(new PlacesFragment(), false);
    }

    public void startMainActivity() {
        finish();
        MainActivity.startActivity(this);
    }
}
