package com.example.android.wifidirect.daemon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.example.android.wifidirect.WiFiDirectBroadcastReceiver;

/**
 * Created by AMD on 11/6/16.
 */

public class ScreenReceiver extends BroadcastReceiver{
    AlarmManager alarmManager;
    private static final long SLEEP_DELAY_TIME = 30 * 60 * 1000;
    private static final long ACTIVE_DELAY_TIME = 60 * 1000;
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;

    @Override
    public void onReceive(Context context, Intent intent) {
        wifiP2pManager = (WifiP2pManager) context.getSystemService(Context.WIFI_P2P_SERVICE);
        channel = wifiP2pManager.initialize(context, context.getMainLooper(), null);

        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            runDaemon(context, ACTIVE_DELAY_TIME);
        } else runDaemon(context, SLEEP_DELAY_TIME);
    }

    private void runDaemon(Context context, long interval) {
        Intent intent = new Intent(context, WiFiDirectBroadcastReceiver.class);
        PendingIntent pendingIntentMessages = PendingIntent.getBroadcast(context, 1, intent, 0);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                interval, pendingIntentMessages);
        Log.d("ScreenReceiver", "Daemon was started with " + interval + " milliseconds delay");
    }

}
