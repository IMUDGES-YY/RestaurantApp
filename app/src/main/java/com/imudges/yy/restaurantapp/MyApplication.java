package com.imudges.yy.restaurantapp;

import android.app.Application;
import com.github.yoojia.anyversion.AnyVersion;
import com.github.yoojia.anyversion.Version;
import com.github.yoojia.anyversion.VersionParser;
import com.imudges.yy.restaurantapp.Tool.Config;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xutils.x;

/**
 * Created by yangyang on 2017/7/1.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        AnyVersion.init(this, new VersionParser() {
            @Override
            public Version onParse(String s) {
                final JSONTokener tokener = new JSONTokener(s);
                try {
                    JSONObject json = (JSONObject) tokener.nextValue();
                    Version version = new Version(
                            json.getString("name"),
                            json.getString("note") + "&&" + json.getInt("forced"),
                            json.getString("url"),
                            json.getInt("code")
                    );
                    return version;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        AnyVersion version = AnyVersion.getInstance();
        version.setURL(Config.BASE_URL + "check_version");
    }
}
