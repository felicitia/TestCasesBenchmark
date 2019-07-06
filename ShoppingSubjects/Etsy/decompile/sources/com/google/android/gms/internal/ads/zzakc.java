package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.ads.internal.ao;

@bu
public final class zzakc extends Handler {
    public zzakc(Looper looper) {
        super(looper);
    }

    public final void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Throwable th) {
            ao.e();
            hd.a(ao.i().m(), th);
            throw th;
        }
    }

    public final void handleMessage(Message message) {
        try {
            super.handleMessage(message);
        } catch (Exception e) {
            ao.i().a((Throwable) e, "AdMobHandler.handleMessage");
        }
    }
}
