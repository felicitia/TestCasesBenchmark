package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
public abstract class cl implements cj, hw<Void> {
    private final lg<zzaef> a;
    private final cj b;
    private final Object c = new Object();

    public cl(lg<zzaef> lgVar, cj cjVar) {
        this.a = lgVar;
        this.b = cjVar;
    }

    public abstract void a();

    public final void a(zzaej zzaej) {
        synchronized (this.c) {
            this.b.a(zzaej);
            a();
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final boolean a(zzaen zzaen, zzaef zzaef) {
        try {
            zzaen.zza(zzaef, (zzaeq) new zzaei(this));
            return true;
        } catch (Throwable th) {
            gv.c("Could not fetch ad response from ad request service due to an Exception.", th);
            ao.i().a(th, "AdRequestClientTask.getAdResponseFromService");
            this.b.a(new zzaej(0));
            return false;
        }
    }

    public final void b() {
        a();
    }

    public final /* synthetic */ Object c() {
        zzaen d = d();
        if (d == null) {
            this.b.a(new zzaej(0));
            a();
            return null;
        }
        this.a.a(new cm(this, d), new cn(this));
        return null;
    }

    public abstract zzaen d();
}
