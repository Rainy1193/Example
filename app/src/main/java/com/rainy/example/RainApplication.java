package com.rainy.example;

import android.app.Application;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import dagger.ObjectGraph;

/**
 * Created by RAINY on 12/14/2016.
 */

public class RainApplication extends Application {
    private ObjectGraph mObjectGraph;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
        mObjectGraph = ObjectGraph.create();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    public ObjectGraph getmObjectGraph() {
        return mObjectGraph;
    }
}
