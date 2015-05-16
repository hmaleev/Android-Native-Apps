package com.hmaleev.sofiaairport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by www on 16.5.2015 ã..
 */
public class Utils {

    public static boolean IsNetworkConnected(Context ctx) {
        ConnectivityManager connectionManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInformation = connectionManager.getActiveNetworkInfo();
        if (networkInformation != null) {
            // There are no active networks.
            return true;
        } else
            return false;
    }
}
