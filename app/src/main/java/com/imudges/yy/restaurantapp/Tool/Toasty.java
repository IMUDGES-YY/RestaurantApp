package com.imudges.yy.restaurantapp.Tool;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yangyang on 2017/7/1.
 */
public class Toasty {
    public static void toasty(Context context, String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
