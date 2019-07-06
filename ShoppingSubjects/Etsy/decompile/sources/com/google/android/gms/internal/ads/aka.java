package com.google.android.gms.internal.ads;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class aka extends Thread {
    private final BlockingQueue<amf<?>> a;
    private final ajo b;
    private final vx c;
    private final a d;
    private volatile boolean e = false;

    public aka(BlockingQueue<amf<?>> blockingQueue, ajo ajo, vx vxVar, a aVar) {
        this.a = blockingQueue;
        this.b = ajo;
        this.c = vxVar;
        this.d = aVar;
    }

    private final void b() throws InterruptedException {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        amf amf = (amf) this.a.take();
        try {
            amf.b("network-queue-take");
            amf.g();
            TrafficStats.setThreadStatsTag(amf.d());
            all a2 = this.b.a(amf);
            amf.b("network-http-complete");
            if (!a2.e || !amf.l()) {
                arb a3 = amf.a(a2);
                amf.b("network-parse-complete");
                if (amf.h() && a3.b != null) {
                    this.c.a(amf.e(), a3.b);
                    amf.b("network-cache-written");
                }
                amf.k();
                this.d.a(amf, a3);
                amf.a(a3);
                return;
            }
            amf.c("not-modified");
            amf.m();
        } catch (zzae e2) {
            e2.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.d.a(amf, e2);
            amf.m();
        } catch (Exception e3) {
            ct.a(e3, "Unhandled exception %s", e3.toString());
            zzae zzae = new zzae((Throwable) e3);
            zzae.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.d.a(amf, zzae);
            amf.m();
        }
    }

    public final void a() {
        this.e = true;
        interrupt();
    }

    public final void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                b();
            } catch (InterruptedException unused) {
                if (this.e) {
                    return;
                }
            }
        }
    }
}
