package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.k;
import java.util.concurrent.Future;

@bu
public final class em extends gq implements es, eu, ez {
    public final String a;
    private final gb b;
    /* access modifiers changed from: private */
    public final Context c;
    private final fa d;
    private final eu e;
    private final Object f;
    /* access modifiers changed from: private */
    public final String g;
    private final aqy h;
    private final long i;
    private int j = 0;
    private int k = 3;
    private ep l;
    private Future m;
    private volatile k n;

    public em(Context context, String str, String str2, aqy aqy, gb gbVar, fa faVar, eu euVar, long j2) {
        this.c = context;
        this.a = str;
        this.g = str2;
        this.h = aqy;
        this.b = gbVar;
        this.d = faVar;
        this.f = new Object();
        this.e = euVar;
        this.i = j2;
    }

    /* access modifiers changed from: private */
    public final void a(zzjj zzjj, zzxq zzxq) {
        this.d.b().zza((eu) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.a)) {
                zzxq.zza(zzjj, this.g, this.h.a);
            } else {
                zzxq.zzc(zzjj, this.g);
            }
        } catch (RemoteException e2) {
            gv.c("Fail to load ad from adapter.", e2);
            a(this.a, 0);
        }
    }

    private final boolean a(long j2) {
        int i2;
        long elapsedRealtime = this.i - (ao.l().elapsedRealtime() - j2);
        if (elapsedRealtime <= 0) {
            i2 = 4;
        } else {
            try {
                this.f.wait(elapsedRealtime);
                return true;
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                i2 = 5;
            }
        }
        this.k = i2;
        return false;
    }

    public final void a() {
        Handler handler;
        Runnable eoVar;
        if (this.d != null && this.d.b() != null && this.d.a() != null) {
            zzahv b2 = this.d.b();
            b2.zza((eu) null);
            b2.zza((es) this);
            b2.zza((ez) this);
            zzjj zzjj = this.b.a.zzccv;
            zzxq a2 = this.d.a();
            try {
                if (a2.isInitialized()) {
                    handler = jp.a;
                    eoVar = new en(this, zzjj, a2);
                } else {
                    handler = jp.a;
                    eoVar = new eo(this, a2, zzjj, b2);
                }
                handler.post(eoVar);
            } catch (RemoteException e2) {
                gv.c("Fail to check if adapter is initialized.", e2);
                a(this.a, 0);
            }
            long elapsedRealtime = ao.l().elapsedRealtime();
            while (true) {
                synchronized (this.f) {
                    if (this.j != 0) {
                        this.l = new er().a(ao.l().elapsedRealtime() - elapsedRealtime).a(1 == this.j ? 6 : this.k).a(this.a).b(this.h.d).a();
                    } else if (!a(elapsedRealtime)) {
                        this.l = new er().a(this.k).a(ao.l().elapsedRealtime() - elapsedRealtime).a(this.a).b(this.h.d).a();
                        break;
                    }
                }
            }
            b2.zza((eu) null);
            b2.zza((es) null);
            if (this.j == 1) {
                this.e.a(this.a);
            } else {
                this.e.a(this.a, this.k);
            }
        }
    }

    public final void a(int i2) {
        a(this.a, 0);
    }

    public final void a(Bundle bundle) {
        k kVar = this.n;
        if (kVar != null) {
            kVar.zza("", bundle);
        }
    }

    public final void a(k kVar) {
        this.n = kVar;
    }

    public final void a(String str) {
        synchronized (this.f) {
            this.j = 1;
            this.f.notify();
        }
    }

    public final void a(String str, int i2) {
        synchronized (this.f) {
            this.j = 2;
            this.k = i2;
            this.f.notify();
        }
    }

    public final void c_() {
    }

    public final Future d() {
        if (this.m != null) {
            return this.m;
        }
        kt ktVar = (kt) c();
        this.m = ktVar;
        return ktVar;
    }

    public final ep e() {
        ep epVar;
        synchronized (this.f) {
            epVar = this.l;
        }
        return epVar;
    }

    public final aqy f() {
        return this.h;
    }

    public final void g() {
        a(this.b.a.zzccv, this.d.a());
    }
}
