package com.ngstudio.wayphoto.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.ngstudio.wayphoto.Constants;
import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.ui.fragments.ImagePagerFragment;

public class ImageActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
		Fragment fr;
		String tag;
		int titleRes;
		switch (frIndex) {
			default:
			/*case ImageListFragment.INDEX:
				tag = ImageListFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImageListFragment();
				}
				titleRes = R.string.ac_name_image_list;
				break;*/
			//case ImageGridFragment.INDEX:
			/*case PicturesGalleryFragment.INDEX:
				tag = PicturesGalleryFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new PicturesGalleryFragment();
				}
				titleRes = R.string.ac_name_image_grid;

				break;*/
			case ImagePagerFragment.INDEX:
				tag = ImagePagerFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImagePagerFragment();
					fr.setArguments(getIntent().getExtras());
				}
				titleRes = R.string.ac_name_image_pager;
				break;
			/*case ImageGalleryFragment.INDEX:
				tag = ImageGalleryFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImageGalleryFragment();
				}
				titleRes = R.string.ac_name_image_gallery;
				break; */
		}

		setTitle(titleRes);
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
	}
}