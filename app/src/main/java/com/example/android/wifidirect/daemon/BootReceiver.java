package com.example.android.wifidirect.daemon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by AMD on 11/6/16.
 */

public class BootReceiver extends BroadcastReceiver{

    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.i(TAG, " Start boot receiver");
        new ReceiverManager(context);
    }
}
