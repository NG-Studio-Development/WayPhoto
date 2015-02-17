package com.ngstudio.wayphoto.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.ngstudio.wayphoto.R;

public class LoginActivity extends BaseActivity {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        boolean isLoggedIn = WhereAreYouApplication.getInstance().getApplicationPreferences().getBoolean(WhereAreYouAppConstants.PREF_KEY_IS_LOGGED_IN,false);
        if (savedInstanceState == null && !isLoggedIn) {
            addFragment(new RegisterFragment(),false);
        } else if(isLoggedIn) {
            WhereAreYouApplication.getInstance().setCurrentMobile(WhereAreYouApplication.getPrefString(WhereAreYouAppConstants.PREF_KEY_MOBILE_NUMBER,"380955941708"));
            startMainActivity();
        }
    }
}
