package com.example.android.wifidirect.daemon;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.PowerManager;

/**
 * Created by AMD on 11/6/16.
 */

public class ReceiverManager {
    private static boolean isMessageReceiverStarted = false;

    public ReceiverManager(Context context) {
        if (!isMessageReceiverStarted) {
            startReceiver(context);
        }
    }

    private void startReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        ScreenReceiver receiver = new ScreenReceiver();
        context.registerReceiver(receiver, intentFilter);

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isInteractive;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            isInteractive = pm.isInteractive();
        } else isInteractive = pm.isScreenOn();

        receiver.onReceive(context,
                (isInteractive) ? new Intent(Intent.ACTION_SCREEN_ON) : new Intent(Intent.ACTION_SCREEN_OFF));
        isMessageReceiverStarted = true;
    }

}
