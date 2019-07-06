package com.google.ads.conversiontracking;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class n implements ServiceConnection {
    boolean a = false;
    private final BlockingQueue<IBinder> b = new LinkedBlockingQueue();

    public IBinder a() throws InterruptedException {
        if (this.a) {
            throw new IllegalStateException();
        }
        this.a = true;
        return (IBinder) this.b.take();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.b.put(iBinder);
        } catch (InterruptedException unused) {
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
