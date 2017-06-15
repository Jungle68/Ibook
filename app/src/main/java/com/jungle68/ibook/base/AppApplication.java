package com.jungle68.ibook.base;

import android.app.Application;

/**
 * Created by jungle on 2017/6/15.
 */
public class AppApplication extends Application {

    public static AppApplication sAppApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppApplication = this;
    }

}
