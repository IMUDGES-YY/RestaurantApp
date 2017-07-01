package com.imudges.yy.restaurantapp;

import android.app.Application;
import org.xutils.x;

/**
 * Created by yangyang on 2017/7/1.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
