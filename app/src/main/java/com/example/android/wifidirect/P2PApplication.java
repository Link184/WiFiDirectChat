package com.example.android.wifidirect;

import android.app.Application;

import com.example.android.wifidirect.daemon.ReceiverManager;

/**
 * Created by AMD on 11/6/16.
 */

public class P2PApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        new ReceiverManager(this);
    }
}
