package com.ngstudio.wayphoto.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.ui.fragments.GalleryFragment;
import com.ngstudio.wayphoto.ui.fragments.MapFragment;
import com.ngstudio.wayphoto.ui.fragments.PhotoFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MainActivity extends BaseActivity {

    public enum ItemMenu { MAP,PHOTO,GALLERY }

    private FragmentTabHost tabHost;

    public static void startActivity(Context context/*, @Nullable Contact contact*/) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createPhoto();
//                Log.d("PHOTO_TAB_IN_FRAGMENT", "Click");
            }
        });



        setTabs(ItemMenu.MAP.name(), R.drawable.ic_map, MapFragment.class);
        setTabs(ItemMenu.PHOTO.name(), R.drawable.ic_photo, PhotoFragment.class);
        setTabs(ItemMenu.GALLERY.name(), R.drawable.ic_gallery, GalleryFragment.class);
    }


    private void setTabs (String tag,  int resourceIcon, java.lang.Class<? extends Fragment> fragmentClass) {
        final View view = createTabView(tabHost.getContext(), resourceIcon);

        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);


        tabSpec.setIndicator(view);
        tabSpec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {return new View(tabHost.getContext());}
        });
        tabHost.addTab(tabSpec, fragmentClass, null);
    }

    private  View createTabView(final Context context, final int resourceIcon) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_menu, null);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
        ivIcon.setImageResource(resourceIcon);
        return view;
    }

    public FragmentTabHost getFragmentTabHost() { return tabHost; }

    protected  int getFragmentContainerId() { return 0; }

}

