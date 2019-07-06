package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class apa {
    private final AtomicInteger a;
    private final Set<amf<?>> b;
    private final PriorityBlockingQueue<amf<?>> c;
    private final PriorityBlockingQueue<amf<?>> d;
    private final vx e;
    private final ajo f;
    private final a g;
    private final aka[] h;
    private acz i;
    private final List<aqb> j;

    public apa(vx vxVar, ajo ajo) {
        this(vxVar, ajo, 4);
    }

    private apa(vx vxVar, ajo ajo, int i2) {
        this(vxVar, ajo, 4, new aho(new Handler(Looper.getMainLooper())));
    }

    private apa(vx vxVar, ajo ajo, int i2, a aVar) {
        this.a = new AtomicInteger();
        this.b = new HashSet();
        this.c = new PriorityBlockingQueue<>();
        this.d = new PriorityBlockingQueue<>();
        this.j = new ArrayList();
        this.e = vxVar;
        this.f = ajo;
        this.h = new aka[4];
        this.g = aVar;
    }

    public final <T> amf<T> a(amf<T> amf) {
        amf.a(this);
        synchronized (this.b) {
            this.b.add(amf);
        }
        amf.a(this.a.incrementAndGet());
        amf.b("add-to-queue");
        (!amf.h() ? this.d : this.c).add(amf);
        return amf;
    }

    public final void a() {
        aka[] akaArr;
        if (this.i != null) {
            this.i.a();
        }
        for (aka aka : this.h) {
            if (aka != null) {
                aka.a();
            }
        }
        this.i = new acz(this.c, this.d, this.e, this.g);
        this.i.start();
        for (int i2 = 0; i2 < this.h.length; i2++) {
            aka aka2 = new aka(this.d, this.f, this.e, this.g);
            this.h[i2] = aka2;
            aka2.start();
        }
    }

    /* access modifiers changed from: 0000 */
    public final <T> void b(amf<T> amf) {
        synchronized (this.b) {
            this.b.remove(amf);
        }
        synchronized (this.j) {
            for (aqb a2 : this.j) {
                a2.a(amf);
            }
        }
    }
}
