package com.ngstudio.wayphoto.ui.activities.test;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.ui.activities.BaseActivity;
import com.ngstudio.wayphoto.ui.fragments.GalleryFragment;
import com.ngstudio.wayphoto.ui.fragments.MapFragment;
import com.ngstudio.wayphoto.ui.fragments.PhotoFragment;

    public class TabActivity extends BaseActivity {

    @Override
    protected int getFragmentContainerId() { return 0; }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Tab1"),
                PhotoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Tab2"),
                GalleryFragment.class, null);


        tabHost.setCurrentTabByTag("tag2");
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {}
        });
    }
}
