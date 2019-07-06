package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public abstract class PlatformServiceClient implements ServiceConnection {
    private final String applicationId;
    private final Context context;
    private final Handler handler;
    private a listener;
    private final int protocolVersion;
    private int replyMessage;
    private int requestMessage;
    private boolean running;
    private Messenger sender;

    public interface a {
        void a(Bundle bundle);
    }

    /* access modifiers changed from: protected */
    public abstract void populateRequestBundle(Bundle bundle);

    public PlatformServiceClient(Context context2, int i, int i2, int i3, String str) {
        Context applicationContext = context2.getApplicationContext();
        if (applicationContext != null) {
            context2 = applicationContext;
        }
        this.context = context2;
        this.requestMessage = i;
        this.replyMessage = i2;
        this.applicationId = str;
        this.protocolVersion = i3;
        this.handler = new Handler() {
            public void handleMessage(Message message) {
                PlatformServiceClient.this.handleMessage(message);
            }
        };
    }

    public void setCompletedListener(a aVar) {
        this.listener = aVar;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    public boolean start() {
        if (this.running || v.b(this.protocolVersion) == -1) {
            return false;
        }
        Intent a2 = v.a(this.context);
        if (a2 == null) {
            return false;
        }
        this.running = true;
        this.context.bindService(a2, this, 1);
        return true;
    }

    public void cancel() {
        this.running = false;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.sender = new Messenger(iBinder);
        sendMessage();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.sender = null;
        try {
            this.context.unbindService(this);
        } catch (IllegalArgumentException unused) {
        }
        callback(null);
    }

    private void sendMessage() {
        Bundle bundle = new Bundle();
        bundle.putString("com.facebook.platform.extra.APPLICATION_ID", this.applicationId);
        populateRequestBundle(bundle);
        Message obtain = Message.obtain(null, this.requestMessage);
        obtain.arg1 = this.protocolVersion;
        obtain.setData(bundle);
        obtain.replyTo = new Messenger(this.handler);
        try {
            this.sender.send(obtain);
        } catch (RemoteException unused) {
            callback(null);
        }
    }

    /* access modifiers changed from: protected */
    public void handleMessage(Message message) {
        if (message.what == this.replyMessage) {
            Bundle data = message.getData();
            if (data.getString("com.facebook.platform.status.ERROR_TYPE") != null) {
                callback(null);
            } else {
                callback(data);
            }
            try {
                this.context.unbindService(this);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    private void callback(Bundle bundle) {
        if (this.running) {
            this.running = false;
            a aVar = this.listener;
            if (aVar != null) {
                aVar.a(bundle);
            }
        }
    }
}
