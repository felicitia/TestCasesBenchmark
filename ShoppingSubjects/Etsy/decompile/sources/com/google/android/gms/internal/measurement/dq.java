package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.time.DateUtils;

@VisibleForTesting
public final class dq extends s {
    /* access modifiers changed from: private */
    public final zziy a;
    /* access modifiers changed from: private */
    public zzfa b;
    private volatile Boolean c;
    private final ae d;
    private final et e;
    private final List<Runnable> f = new ArrayList();
    private final ae g;

    protected dq(bu buVar) {
        super(buVar);
        this.e = new et(buVar.m());
        this.a = new zziy(this);
        this.d = new dr(this, buVar);
        this.g = new dw(this, buVar);
    }

    private final boolean I() {
        u();
        return true;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void J() {
        d();
        this.e.a();
        this.d.a(((Long) ak.O.b()).longValue());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void K() {
        d();
        if (B()) {
            r().w().a("Inactivity, disconnecting from the service");
            H();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void L() {
        d();
        r().w().a("Processing queued up service tasks", Integer.valueOf(this.f.size()));
        for (Runnable run : this.f) {
            try {
                run.run();
            } catch (Exception e2) {
                r().h_().a("Task exception while flushing queue", e2);
            }
        }
        this.f.clear();
        this.g.c();
    }

    @Nullable
    @WorkerThread
    private final zzeb a(boolean z) {
        u();
        return g().a(z ? r().x() : null);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void a(ComponentName componentName) {
        d();
        if (this.b != null) {
            this.b = null;
            r().w().a("Disconnected from device MeasurementService", componentName);
            d();
            F();
        }
    }

    @WorkerThread
    private final void a(Runnable runnable) throws IllegalStateException {
        d();
        if (B()) {
            runnable.run();
        } else if (((long) this.f.size()) >= 1000) {
            r().h_().a("Discarding data. Max runnable queue size reached");
        } else {
            this.f.add(runnable);
            this.g.a(DateUtils.MILLIS_PER_MINUTE);
            F();
        }
    }

    @WorkerThread
    public final boolean B() {
        d();
        w();
        return this.b != null;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void C() {
        d();
        w();
        a((Runnable) new dx(this, a(true)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void D() {
        d();
        b();
        w();
        zzeb a2 = a(false);
        if (I()) {
            j().B();
        }
        a((Runnable) new ds(this, a2));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void E() {
        d();
        w();
        a((Runnable) new du(this, a(true)));
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c6, code lost:
        r0 = false;
        r3 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x010a  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void F() {
        /*
            r6 = this;
            r6.d()
            r6.w()
            boolean r0 = r6.B()
            if (r0 == 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.Boolean r0 = r6.c
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0117
            r6.d()
            r6.w()
            com.google.android.gms.internal.measurement.bb r0 = r6.s()
            java.lang.Boolean r0 = r0.i()
            if (r0 == 0) goto L_0x002c
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x002c
            r0 = r2
            goto L_0x0111
        L_0x002c:
            r6.u()
            com.google.android.gms.internal.measurement.al r0 = r6.g()
            int r0 = r0.F()
            if (r0 != r2) goto L_0x003d
        L_0x0039:
            r0 = r2
        L_0x003a:
            r3 = r0
            goto L_0x00ee
        L_0x003d:
            com.google.android.gms.internal.measurement.aq r0 = r6.r()
            com.google.android.gms.internal.measurement.as r0 = r0.w()
            java.lang.String r3 = "Checking service availability"
            r0.a(r3)
            com.google.android.gms.internal.measurement.fg r0 = r6.p()
            com.google.android.gms.common.GoogleApiAvailabilityLight r3 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()
            android.content.Context r0 = r0.n()
            r4 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r0 = r3.isGooglePlayServicesAvailable(r0, r4)
            r3 = 9
            if (r0 == r3) goto L_0x00e3
            r3 = 18
            if (r0 == r3) goto L_0x00d8
            switch(r0) {
                case 0: goto L_0x00c9;
                case 1: goto L_0x00b9;
                case 2: goto L_0x0089;
                case 3: goto L_0x007b;
                default: goto L_0x0068;
            }
        L_0x0068:
            com.google.android.gms.internal.measurement.aq r3 = r6.r()
            com.google.android.gms.internal.measurement.as r3 = r3.i()
            java.lang.String r4 = "Unexpected service status"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3.a(r4, r0)
        L_0x0079:
            r0 = r1
            goto L_0x003a
        L_0x007b:
            com.google.android.gms.internal.measurement.aq r0 = r6.r()
            com.google.android.gms.internal.measurement.as r0 = r0.i()
            java.lang.String r3 = "Service disabled"
        L_0x0085:
            r0.a(r3)
            goto L_0x0079
        L_0x0089:
            com.google.android.gms.internal.measurement.aq r0 = r6.r()
            com.google.android.gms.internal.measurement.as r0 = r0.v()
            java.lang.String r3 = "Service container out of date"
            r0.a(r3)
            com.google.android.gms.internal.measurement.fg r0 = r6.p()
            int r0 = r0.j()
            r3 = 12600(0x3138, float:1.7656E-41)
            if (r0 >= r3) goto L_0x00a3
            goto L_0x00c6
        L_0x00a3:
            com.google.android.gms.internal.measurement.bb r0 = r6.s()
            java.lang.Boolean r0 = r0.i()
            if (r0 == 0) goto L_0x00b6
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x00b4
            goto L_0x00b6
        L_0x00b4:
            r0 = r1
            goto L_0x00b7
        L_0x00b6:
            r0 = r2
        L_0x00b7:
            r3 = r1
            goto L_0x00ee
        L_0x00b9:
            com.google.android.gms.internal.measurement.aq r0 = r6.r()
            com.google.android.gms.internal.measurement.as r0 = r0.w()
            java.lang.String r3 = "Service missing"
            r0.a(r3)
        L_0x00c6:
            r0 = r1
            r3 = r2
            goto L_0x00ee
        L_0x00c9:
            com.google.android.gms.internal.measurement.aq r0 = r6.r()
            com.google.android.gms.internal.measurement.as r0 = r0.w()
            java.lang.String r3 = "Service available"
        L_0x00d3:
            r0.a(r3)
            goto L_0x0039
        L_0x00d8:
            com.google.android.gms.internal.measurement.aq r0 = r6.r()
            com.google.android.gms.internal.measurement.as r0 = r0.i()
            java.lang.String r3 = "Service updating"
            goto L_0x00d3
        L_0x00e3:
            com.google.android.gms.internal.measurement.aq r0 = r6.r()
            com.google.android.gms.internal.measurement.as r0 = r0.i()
            java.lang.String r3 = "Service invalid"
            goto L_0x0085
        L_0x00ee:
            if (r0 != 0) goto L_0x0108
            com.google.android.gms.internal.measurement.w r4 = r6.t()
            boolean r4 = r4.x()
            if (r4 == 0) goto L_0x0108
            com.google.android.gms.internal.measurement.aq r3 = r6.r()
            com.google.android.gms.internal.measurement.as r3 = r3.h_()
            java.lang.String r4 = "No way to upload. Consider using the full version of Analytics"
            r3.a(r4)
            r3 = r1
        L_0x0108:
            if (r3 == 0) goto L_0x0111
            com.google.android.gms.internal.measurement.bb r3 = r6.s()
            r3.a(r0)
        L_0x0111:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.c = r0
        L_0x0117:
            java.lang.Boolean r0 = r6.c
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0125
            com.google.android.gms.internal.measurement.zziy r0 = r6.a
            r0.zzkt()
            return
        L_0x0125:
            com.google.android.gms.internal.measurement.w r0 = r6.t()
            boolean r0 = r0.x()
            if (r0 != 0) goto L_0x0185
            r6.u()
            android.content.Context r0 = r6.n()
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            android.content.Context r4 = r6.n()
            java.lang.String r5 = "com.google.android.gms.measurement.AppMeasurementService"
            android.content.Intent r3 = r3.setClassName(r4, r5)
            r4 = 65536(0x10000, float:9.18355E-41)
            java.util.List r0 = r0.queryIntentServices(r3, r4)
            if (r0 == 0) goto L_0x0158
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0158
            r1 = r2
        L_0x0158:
            if (r1 == 0) goto L_0x0178
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.google.android.gms.measurement.START"
            r0.<init>(r1)
            android.content.ComponentName r1 = new android.content.ComponentName
            android.content.Context r2 = r6.n()
            r6.u()
            java.lang.String r3 = "com.google.android.gms.measurement.AppMeasurementService"
            r1.<init>(r2, r3)
            r0.setComponent(r1)
            com.google.android.gms.internal.measurement.zziy r1 = r6.a
            r1.zzc(r0)
            return
        L_0x0178:
            com.google.android.gms.internal.measurement.aq r0 = r6.r()
            com.google.android.gms.internal.measurement.as r0 = r0.h_()
            java.lang.String r1 = "Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest"
            r0.a(r1)
        L_0x0185:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.dq.F():void");
    }

    /* access modifiers changed from: 0000 */
    public final Boolean G() {
        return this.c;
    }

    @WorkerThread
    public final void H() {
        d();
        w();
        try {
            ConnectionTracker.getInstance().unbindService(n(), this.a);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.b = null;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a(dm dmVar) {
        d();
        w();
        a((Runnable) new dv(this, dmVar));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        d();
        w();
        u();
        dz dzVar = new dz(this, true, j().a(zzef), new zzef(zzef), a(true), zzef);
        a((Runnable) dzVar);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a(zzex zzex, String str) {
        Preconditions.checkNotNull(zzex);
        d();
        w();
        boolean I = I();
        dy dyVar = new dy(this, I, I && j().a(zzex), zzex, a(true), str);
        a((Runnable) dyVar);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void a(zzfa zzfa) {
        d();
        Preconditions.checkNotNull(zzfa);
        this.b = zzfa;
        J();
        L();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    @android.support.annotation.WorkerThread
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.gms.internal.measurement.zzfa r12, com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable r13, com.google.android.gms.internal.measurement.zzeb r14) {
        /*
            r11 = this;
            r11.d()
            r11.b()
            r11.w()
            boolean r0 = r11.I()
            r1 = 0
            r2 = 100
            r3 = r1
            r4 = r2
        L_0x0012:
            r5 = 1001(0x3e9, float:1.403E-42)
            if (r3 >= r5) goto L_0x00a2
            if (r4 != r2) goto L_0x00a2
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r0 == 0) goto L_0x0031
            com.google.android.gms.internal.measurement.am r5 = r11.j()
            java.util.List r5 = r5.a(r2)
            if (r5 == 0) goto L_0x0031
            r4.addAll(r5)
            int r5 = r5.size()
            goto L_0x0032
        L_0x0031:
            r5 = r1
        L_0x0032:
            if (r13 == 0) goto L_0x0039
            if (r5 >= r2) goto L_0x0039
            r4.add(r13)
        L_0x0039:
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            int r6 = r4.size()
            r7 = r1
        L_0x0040:
            if (r7 >= r6) goto L_0x009d
            java.lang.Object r8 = r4.get(r7)
            int r7 = r7 + 1
            com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable r8 = (com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable) r8
            boolean r9 = r8 instanceof com.google.android.gms.internal.measurement.zzex
            if (r9 == 0) goto L_0x0063
            com.google.android.gms.internal.measurement.zzex r8 = (com.google.android.gms.internal.measurement.zzex) r8     // Catch:{ RemoteException -> 0x0054 }
            r12.zza(r8, r14)     // Catch:{ RemoteException -> 0x0054 }
            goto L_0x0040
        L_0x0054:
            r8 = move-exception
            com.google.android.gms.internal.measurement.aq r9 = r11.r()
            com.google.android.gms.internal.measurement.as r9 = r9.h_()
            java.lang.String r10 = "Failed to send event to the service"
        L_0x005f:
            r9.a(r10, r8)
            goto L_0x0040
        L_0x0063:
            boolean r9 = r8 instanceof com.google.android.gms.internal.measurement.zzka
            if (r9 == 0) goto L_0x0079
            com.google.android.gms.internal.measurement.zzka r8 = (com.google.android.gms.internal.measurement.zzka) r8     // Catch:{ RemoteException -> 0x006d }
            r12.zza(r8, r14)     // Catch:{ RemoteException -> 0x006d }
            goto L_0x0040
        L_0x006d:
            r8 = move-exception
            com.google.android.gms.internal.measurement.aq r9 = r11.r()
            com.google.android.gms.internal.measurement.as r9 = r9.h_()
            java.lang.String r10 = "Failed to send attribute to the service"
            goto L_0x005f
        L_0x0079:
            boolean r9 = r8 instanceof com.google.android.gms.internal.measurement.zzef
            if (r9 == 0) goto L_0x008f
            com.google.android.gms.internal.measurement.zzef r8 = (com.google.android.gms.internal.measurement.zzef) r8     // Catch:{ RemoteException -> 0x0083 }
            r12.zza(r8, r14)     // Catch:{ RemoteException -> 0x0083 }
            goto L_0x0040
        L_0x0083:
            r8 = move-exception
            com.google.android.gms.internal.measurement.aq r9 = r11.r()
            com.google.android.gms.internal.measurement.as r9 = r9.h_()
            java.lang.String r10 = "Failed to send conditional property to the service"
            goto L_0x005f
        L_0x008f:
            com.google.android.gms.internal.measurement.aq r8 = r11.r()
            com.google.android.gms.internal.measurement.as r8 = r8.h_()
            java.lang.String r9 = "Discarding data. Unrecognized parcel type."
            r8.a(r9)
            goto L_0x0040
        L_0x009d:
            int r3 = r3 + 1
            r4 = r5
            goto L_0x0012
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.dq.a(com.google.android.gms.internal.measurement.zzfa, com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable, com.google.android.gms.internal.measurement.zzeb):void");
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a(zzka zzka) {
        d();
        w();
        a((Runnable) new ec(this, I() && j().a(zzka), zzka, a(true)));
    }

    @WorkerThread
    public final void a(AtomicReference<String> atomicReference) {
        d();
        w();
        a((Runnable) new dt(this, atomicReference, a(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a(AtomicReference<List<zzef>> atomicReference, String str, String str2, String str3) {
        d();
        w();
        ea eaVar = new ea(this, atomicReference, str, str2, str3, a(false));
        a((Runnable) eaVar);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a(AtomicReference<List<zzka>> atomicReference, String str, String str2, String str3, boolean z) {
        d();
        w();
        eb ebVar = new eb(this, atomicReference, str, str2, str3, z, a(false));
        a((Runnable) ebVar);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a(AtomicReference<List<zzka>> atomicReference, boolean z) {
        d();
        w();
        a((Runnable) new ed(this, atomicReference, a(false), z));
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
