package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import org.apache.commons.lang3.time.DateUtils;

public final class eo extends s {
    private Handler a;
    @VisibleForTesting
    private long b = m().elapsedRealtime();
    private final ae c = new ep(this, this.q);
    private final ae d = new eq(this, this.q);

    eo(bu buVar) {
        super(buVar);
    }

    private final void C() {
        synchronized (this) {
            if (this.a == null) {
                this.a = new Handler(Looper.getMainLooper());
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void D() {
        d();
        a(false);
        e().a(m().elapsedRealtime());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void a(long j) {
        ae aeVar;
        long j2;
        d();
        C();
        this.c.c();
        this.d.c();
        r().w().a("Activity resumed, time", Long.valueOf(j));
        this.b = j;
        if (m().currentTimeMillis() - s().l.a() > s().n.a()) {
            s().m.a(true);
            s().o.a(0);
        }
        if (s().m.a()) {
            aeVar = this.c;
            j2 = s().k.a();
        } else {
            aeVar = this.d;
            j2 = DateUtils.MILLIS_PER_HOUR;
        }
        aeVar.a(Math.max(0, j2 - s().o.a()));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void b(long j) {
        d();
        C();
        this.c.c();
        this.d.c();
        r().w().a("Activity paused, time", Long.valueOf(j));
        if (this.b != 0) {
            s().o.a(s().o.a() + (j - this.b));
        }
    }

    /* access modifiers changed from: 0000 */
    public final void B() {
        this.c.c();
        this.d.c();
        this.b = 0;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @WorkerThread
    public final boolean a(boolean z) {
        d();
        w();
        long elapsedRealtime = m().elapsedRealtime();
        s().n.a(m().currentTimeMillis());
        long j = elapsedRealtime - this.b;
        if (z || j >= 1000) {
            s().o.a(j);
            r().w().a("Recording user engagement, ms", Long.valueOf(j));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            dn.a(i().B(), bundle, true);
            f().a("auto", "_e", bundle);
            this.b = elapsedRealtime;
            this.d.c();
            this.d.a(Math.max(0, DateUtils.MILLIS_PER_HOUR - s().o.a()));
            return true;
        }
        r().w().a("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j));
        return false;
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
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

    /* access modifiers changed from: protected */
    public final boolean z() {
        return false;
    }
}
