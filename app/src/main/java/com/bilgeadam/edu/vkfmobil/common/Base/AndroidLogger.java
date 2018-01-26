package com.bilgeadam.edu.vkfmobil.common.Base;

import android.content.Context;
import android.util.Log;

import com.bilgeadam.edu.vkfmobil.R;


/**
 * Created by 02483564 on 31.5.2016.
 */
public class AndroidLogger {

    public  static  void writeLog(Context context, String message)
    {
        Log.d(context.getString(R.string.app_name), message);
    }

}
