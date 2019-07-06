package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.Map;

public final class n extends r {
    private final Map<String, Long> a = new ArrayMap();
    private final Map<String, Integer> b = new ArrayMap();
    private long c;

    public n(bu buVar) {
        super(buVar);
    }

    @WorkerThread
    private final void a(long j, dm dmVar) {
        if (dmVar == null) {
            r().w().a("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            r().w().a("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            dn.a(dmVar, bundle, true);
            f().a("am", "_xa", bundle);
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void a(String str, long j) {
        b();
        d();
        Preconditions.checkNotEmpty(str);
        if (this.b.isEmpty()) {
            this.c = j;
        }
        Integer num = (Integer) this.b.get(str);
        if (num != null) {
            this.b.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (this.b.size() >= 100) {
            r().i().a("Too many ads visible");
        } else {
            this.b.put(str, Integer.valueOf(1));
            this.a.put(str, Long.valueOf(j));
        }
    }

    @WorkerThread
    private final void a(String str, long j, dm dmVar) {
        if (dmVar == null) {
            r().w().a("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            r().w().a("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            dn.a(dmVar, bundle, true);
            f().a("am", "_xu", bundle);
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void b(long j) {
        for (String put : this.a.keySet()) {
            this.a.put(put, Long.valueOf(j));
        }
        if (!this.a.isEmpty()) {
            this.c = j;
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void b(String str, long j) {
        b();
        d();
        Preconditions.checkNotEmpty(str);
        Integer num = (Integer) this.b.get(str);
        if (num != null) {
            dm B = i().B();
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.b.remove(str);
                Long l = (Long) this.a.get(str);
                if (l == null) {
                    r().h_().a("First ad unit exposure time was never set");
                } else {
                    long longValue = j - l.longValue();
                    this.a.remove(str);
                    a(str, longValue, B);
                }
                if (this.b.isEmpty()) {
                    if (this.c == 0) {
                        r().h_().a("First ad exposure time was never set");
                        return;
                    } else {
                        a(j - this.c, B);
                        this.c = 0;
                    }
                }
                return;
            }
            this.b.put(str, Integer.valueOf(intValue));
            return;
        }
        r().h_().a("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @WorkerThread
    public final void a(long j) {
        dm B = i().B();
        for (String str : this.a.keySet()) {
            a(str, j - ((Long) this.a.get(str)).longValue(), B);
        }
        if (!this.a.isEmpty()) {
            a(j - this.c, B);
        }
        b(j);
    }

    public final void a(String str) {
        if (str == null || str.length() == 0) {
            r().h_().a("Ad unit id must be a non-empty string");
            return;
        }
        q().a((Runnable) new o(this, str, m().elapsedRealtime()));
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final void b(String str) {
        if (str == null || str.length() == 0) {
            r().h_().a("Ad unit id must be a non-empty string");
            return;
        }
        q().a((Runnable) new p(this, str, m().elapsedRealtime()));
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    public final /* bridge */ /* synthetic */ n e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ cs f() {
        return super.f();
    }

    public final /* bridge */ /* synthetic */ al g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ dq h() {
        return super.h();
    }

    public final /* bridge */ /* synthetic */ dn i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ am j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ eo k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ag l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ Clock m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ Context n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ao o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ fg p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ bq q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ aq r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ bb s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ w t() {
        return super.t();
    }

    public final /* bridge */ /* synthetic */ v u() {
        return super.u();
    }
}
