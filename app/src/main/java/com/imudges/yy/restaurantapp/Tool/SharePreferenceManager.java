package com.imudges.yy.restaurantapp.Tool;

import android.content.Context;
import android.content.SharedPreferences;
import com.imudges.yy.restaurantapp.Bean.User;

/**
 * Created by HUPENG on 2017/2/24.
 */
public class SharePreferenceManager {
    public static void writeLong(Context context, String key, long value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key,value);
        editor.commit();
    }

    public static long readLong(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key,-1);
    }

    public static void writeString(Context context,String key,String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String readString(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
}
