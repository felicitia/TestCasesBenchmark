package com.google.android.gms.internal.ads;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class acz extends Thread {
    private static final boolean a = ct.a;
    private final BlockingQueue<amf<?>> b;
    /* access modifiers changed from: private */
    public final BlockingQueue<amf<?>> c;
    private final vx d;
    /* access modifiers changed from: private */
    public final a e;
    private volatile boolean f = false;
    private final aeu g;

    public acz(BlockingQueue<amf<?>> blockingQueue, BlockingQueue<amf<?>> blockingQueue2, vx vxVar, a aVar) {
        this.b = blockingQueue;
        this.c = blockingQueue2;
        this.d = vxVar;
        this.e = aVar;
        this.g = new aeu(this);
    }

    private final void b() throws InterruptedException {
        amf amf = (amf) this.b.take();
        amf.b("cache-queue-take");
        amf.g();
        acb a2 = this.d.a(amf.e());
        if (a2 == null) {
            amf.b("cache-miss");
            if (!this.g.b(amf)) {
                this.c.put(amf);
            }
        } else if (a2.a()) {
            amf.b("cache-hit-expired");
            amf.a(a2);
            if (!this.g.b(amf)) {
                this.c.put(amf);
            }
        } else {
            amf.b("cache-hit");
            arb a3 = amf.a(new all(a2.a, a2.g));
            amf.b("cache-hit-parsed");
            if (a2.f < System.currentTimeMillis()) {
                amf.b("cache-hit-refresh-needed");
                amf.a(a2);
                a3.d = true;
                if (!this.g.b(amf)) {
                    this.e.a(amf, a3, new adz(this, amf));
                    return;
                }
            }
            this.e.a(amf, a3);
        }
    }

    public final void a() {
        this.f = true;
        interrupt();
    }

    public final void run() {
        if (a) {
            ct.a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.d.a();
        while (true) {
            try {
                b();
            } catch (InterruptedException unused) {
                if (this.f) {
                    return;
                }
            }
        }
    }
}
