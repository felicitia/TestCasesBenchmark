package com.google.android.gms.ads.internal;

import android.os.Debug;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.gv;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

final class u extends TimerTask {
    private final /* synthetic */ CountDownLatch a;
    private final /* synthetic */ Timer b;
    private final /* synthetic */ zza c;

    u(zza zza, CountDownLatch countDownLatch, Timer timer) {
        this.c = zza;
        this.a = countDownLatch;
        this.b = timer;
    }

    public final void run() {
        if (((long) ((Integer) ajh.f().a(akl.cp)).intValue()) != this.a.getCount()) {
            gv.b("Stopping method tracing");
            Debug.stopMethodTracing();
            if (this.a.getCount() == 0) {
                this.b.cancel();
                return;
            }
        }
        String concat = String.valueOf(this.c.zzvw.zzrt.getPackageName()).concat("_adsTrace_");
        try {
            gv.b("Starting method tracing");
            this.a.countDown();
            long currentTimeMillis = ao.l().currentTimeMillis();
            StringBuilder sb = new StringBuilder(20 + String.valueOf(concat).length());
            sb.append(concat);
            sb.append(currentTimeMillis);
            Debug.startMethodTracing(sb.toString(), ((Integer) ajh.f().a(akl.cq)).intValue());
        } catch (Exception e) {
            gv.d("#007 Could not call remote method.", e);
        }
    }
}
