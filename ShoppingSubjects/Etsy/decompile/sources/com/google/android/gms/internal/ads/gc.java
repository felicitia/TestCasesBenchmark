package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@bu
public final class gc {
    private final go a;
    private final LinkedList<gd> b;
    private final Object c;
    private final String d;
    private final String e;
    private long f;
    private long g;
    private boolean h;
    private long i;
    private long j;
    private long k;
    private long l;

    private gc(go goVar, String str, String str2) {
        this.c = new Object();
        this.f = -1;
        this.g = -1;
        this.h = false;
        this.i = -1;
        this.j = 0;
        this.k = -1;
        this.l = -1;
        this.a = goVar;
        this.d = str;
        this.e = str2;
        this.b = new LinkedList<>();
    }

    public gc(String str, String str2) {
        this(ao.j(), str, str2);
    }

    public final void a() {
        synchronized (this.c) {
            if (this.l != -1 && this.g == -1) {
                this.g = SystemClock.elapsedRealtime();
                this.a.a(this);
            }
            this.a.b();
        }
    }

    public final void a(long j2) {
        synchronized (this.c) {
            this.l = j2;
            if (this.l != -1) {
                this.a.a(this);
            }
        }
    }

    public final void a(zzjj zzjj) {
        synchronized (this.c) {
            this.k = SystemClock.elapsedRealtime();
            this.a.a(zzjj, this.k);
        }
    }

    public final void a(boolean z) {
        synchronized (this.c) {
            if (this.l != -1) {
                this.i = SystemClock.elapsedRealtime();
                if (!z) {
                    this.g = this.i;
                    this.a.a(this);
                }
            }
        }
    }

    public final void b() {
        synchronized (this.c) {
            if (this.l != -1) {
                gd gdVar = new gd();
                gdVar.c();
                this.b.add(gdVar);
                this.j++;
                this.a.a();
                this.a.a(this);
            }
        }
    }

    public final void b(long j2) {
        synchronized (this.c) {
            if (this.l != -1) {
                this.f = j2;
                this.a.a(this);
            }
        }
    }

    public final void b(boolean z) {
        synchronized (this.c) {
            if (this.l != -1) {
                this.h = z;
                this.a.a(this);
            }
        }
    }

    public final void c() {
        synchronized (this.c) {
            if (this.l != -1 && !this.b.isEmpty()) {
                gd gdVar = (gd) this.b.getLast();
                if (gdVar.a() == -1) {
                    gdVar.b();
                    this.a.a(this);
                }
            }
        }
    }

    public final Bundle d() {
        Bundle bundle;
        synchronized (this.c) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.d);
            bundle.putString("slotid", this.e);
            bundle.putBoolean("ismediation", this.h);
            bundle.putLong("treq", this.k);
            bundle.putLong("tresponse", this.l);
            bundle.putLong("timp", this.g);
            bundle.putLong("tload", this.i);
            bundle.putLong("pcc", this.j);
            bundle.putLong("tfetch", this.f);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                arrayList.add(((gd) it.next()).d());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }
}
