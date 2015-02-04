package com.ngstudio.wayphoto;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;

import com.activeandroid.ActiveAndroid;

import org.jetbrains.annotations.NotNull;

public class WayPhotoApplication extends Application {

    private volatile static WayPhotoApplication instance;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        context = getApplicationContext();

        synchronized (WayPhotoApplication.class) {
            if (BuildConfig.DEBUG) {
                if (instance != null)
                    throw new RuntimeException("Something strange: there is another application instance.");
            }
            instance = this;

            WayPhotoApplication.class.notifyAll();
        }

    }

    public static Context getAppContext() {
        return WayPhotoApplication.context;// = getApplicationContext();
    }

    private String activityVisible;
    private String topActivity;

    public boolean isActivityBackground() {
        return TextUtils.isEmpty(activityVisible);
    }

    public void activityResumed(@NotNull String activity) {
        activityVisible = activity;
        topActivity = activity;
    }

    public void activityPaused(@NotNull String activity) {
        if (activity.equals(activityVisible))
            activityVisible = null;
    }

    @NotNull
    @SuppressWarnings({"ConstantConditions", "unchecked"})
    public static WayPhotoApplication getInstance() {
        WayPhotoApplication application = instance;
        if (application == null) {
            synchronized (WayPhotoApplication.class) {
                if (instance == null) {
                    if (BuildConfig.DEBUG) {
                        if (Thread.currentThread() == Looper.getMainLooper().getThread())
                            throw new UnsupportedOperationException(
                                    "Current application's instance has not been initialized yet (wait for onCreate, please).");
                    }
                    try {
                        do {
                            WayPhotoApplication.class.wait();
                        } while ((application = instance) == null);
                    } catch (InterruptedException e) { /* Nothing to do */ }
                }
            }
        }
        return application;
    }
}
