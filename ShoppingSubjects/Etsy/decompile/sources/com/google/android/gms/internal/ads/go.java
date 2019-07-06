package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

@bu
public final class go implements agd {
    private final Object a;
    @VisibleForTesting
    private final gk b;
    @VisibleForTesting
    private final HashSet<gc> c;
    @VisibleForTesting
    private final HashSet<gn> d;

    public go() {
        this(ajh.c());
    }

    private go(String str) {
        this.a = new Object();
        this.c = new HashSet<>();
        this.d = new HashSet<>();
        this.b = new gk(str);
    }

    public final Bundle a(Context context, gl glVar, String str) {
        Bundle bundle;
        synchronized (this.a) {
            bundle = new Bundle();
            bundle.putBundle("app", this.b.a(context, str));
            Bundle bundle2 = new Bundle();
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                gn gnVar = (gn) it.next();
                bundle2.putBundle(gnVar.a(), gnVar.b());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator it2 = this.c.iterator();
            while (it2.hasNext()) {
                arrayList.add(((gc) it2.next()).d());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            glVar.zza(this.c);
            this.c.clear();
        }
        return bundle;
    }

    public final void a() {
        synchronized (this.a) {
            this.b.a();
        }
    }

    public final void a(gc gcVar) {
        synchronized (this.a) {
            this.c.add(gcVar);
        }
    }

    public final void a(gn gnVar) {
        synchronized (this.a) {
            this.d.add(gnVar);
        }
    }

    public final void a(zzjj zzjj, long j) {
        synchronized (this.a) {
            this.b.a(zzjj, j);
        }
    }

    public final void a(HashSet<gc> hashSet) {
        synchronized (this.a) {
            this.c.addAll(hashSet);
        }
    }

    public final void a(boolean z) {
        long currentTimeMillis = ao.l().currentTimeMillis();
        if (z) {
            if (currentTimeMillis - ao.i().l().i() > ((Long) ajh.f().a(akl.aI)).longValue()) {
                this.b.a = -1;
                return;
            }
            this.b.a = ao.i().l().j();
            return;
        }
        ao.i().l().a(currentTimeMillis);
        ao.i().l().b(this.b.a);
    }

    public final void b() {
        synchronized (this.a) {
            this.b.b();
        }
    }
}
