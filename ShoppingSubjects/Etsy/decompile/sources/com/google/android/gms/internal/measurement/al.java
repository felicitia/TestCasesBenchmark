package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

public final class al extends s {
    private String a;
    private String b;
    private int c;
    private String d;
    private String e;
    private long f;
    private long g;
    private int h;
    private String i;

    al(bu buVar) {
        super(buVar);
    }

    @WorkerThread
    private final String G() {
        d();
        b();
        if (t().j(this.a) && !this.q.y()) {
            return null;
        }
        try {
            return FirebaseInstanceId.a().c();
        } catch (IllegalStateException unused) {
            r().i().a("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x014c A[SYNTHETIC, Splitter:B:55:0x014c] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0191  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A() {
        /*
            r10 = this;
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "Unknown"
            java.lang.String r2 = "Unknown"
            android.content.Context r3 = r10.n()
            java.lang.String r3 = r3.getPackageName()
            android.content.Context r4 = r10.n()
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            r5 = 0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 != 0) goto L_0x002d
            com.google.android.gms.internal.measurement.aq r4 = r10.r()
            com.google.android.gms.internal.measurement.as r4 = r4.h_()
            java.lang.String r7 = "PackageManager is null, app identity information might be inaccurate. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r3)
            r4.a(r7, r8)
            goto L_0x008b
        L_0x002d:
            java.lang.String r7 = r4.getInstallerPackageName(r3)     // Catch:{ IllegalArgumentException -> 0x0033 }
            r0 = r7
            goto L_0x0044
        L_0x0033:
            com.google.android.gms.internal.measurement.aq r7 = r10.r()
            com.google.android.gms.internal.measurement.as r7 = r7.h_()
            java.lang.String r8 = "Error retrieving app installer package name. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.aq.a(r3)
            r7.a(r8, r9)
        L_0x0044:
            if (r0 != 0) goto L_0x0049
            java.lang.String r0 = "manual_install"
            goto L_0x0053
        L_0x0049:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0053
            java.lang.String r0 = ""
        L_0x0053:
            android.content.Context r7 = r10.n()     // Catch:{ NameNotFoundException -> 0x007a }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x007a }
            android.content.pm.PackageInfo r7 = r4.getPackageInfo(r7, r5)     // Catch:{ NameNotFoundException -> 0x007a }
            if (r7 == 0) goto L_0x008b
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x007a }
            java.lang.CharSequence r4 = r4.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x007a }
            boolean r8 = android.text.TextUtils.isEmpty(r4)     // Catch:{ NameNotFoundException -> 0x007a }
            if (r8 != 0) goto L_0x0072
            java.lang.String r4 = r4.toString()     // Catch:{ NameNotFoundException -> 0x007a }
            r2 = r4
        L_0x0072:
            java.lang.String r4 = r7.versionName     // Catch:{ NameNotFoundException -> 0x007a }
            int r1 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x0079 }
            r6 = r1
            r1 = r4
            goto L_0x008b
        L_0x0079:
            r1 = r4
        L_0x007a:
            com.google.android.gms.internal.measurement.aq r4 = r10.r()
            com.google.android.gms.internal.measurement.as r4 = r4.h_()
            java.lang.String r7 = "Error retrieving package info. appId, appName"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r3)
            r4.a(r7, r8, r2)
        L_0x008b:
            r10.a = r3
            r10.d = r0
            r10.b = r1
            r10.c = r6
            r10.e = r2
            r0 = 0
            r10.f = r0
            r10.u()
            android.content.Context r2 = r10.n()
            com.google.android.gms.common.api.Status r2 = com.google.android.gms.common.api.internal.GoogleServices.initialize(r2)
            r4 = 1
            if (r2 == 0) goto L_0x00af
            boolean r6 = r2.isSuccess()
            if (r6 == 0) goto L_0x00af
            r6 = r4
            goto L_0x00b0
        L_0x00af:
            r6 = r5
        L_0x00b0:
            if (r6 != 0) goto L_0x00db
            if (r2 != 0) goto L_0x00c2
            com.google.android.gms.internal.measurement.aq r2 = r10.r()
            com.google.android.gms.internal.measurement.as r2 = r2.h_()
            java.lang.String r7 = "GoogleService failed to initialize (no status)"
            r2.a(r7)
            goto L_0x00db
        L_0x00c2:
            com.google.android.gms.internal.measurement.aq r7 = r10.r()
            com.google.android.gms.internal.measurement.as r7 = r7.h_()
            java.lang.String r8 = "GoogleService failed to initialize, status"
            int r9 = r2.getStatusCode()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = r2.getStatusMessage()
            r7.a(r8, r9, r2)
        L_0x00db:
            if (r6 == 0) goto L_0x0131
            com.google.android.gms.internal.measurement.w r2 = r10.t()
            java.lang.Boolean r2 = r2.i()
            com.google.android.gms.internal.measurement.w r6 = r10.t()
            boolean r6 = r6.h()
            if (r6 == 0) goto L_0x00fd
            com.google.android.gms.internal.measurement.aq r2 = r10.r()
            com.google.android.gms.internal.measurement.as r2 = r2.k()
            java.lang.String r4 = "Collection disabled with firebase_analytics_collection_deactivated=1"
        L_0x00f9:
            r2.a(r4)
            goto L_0x0131
        L_0x00fd:
            if (r2 == 0) goto L_0x0110
            boolean r6 = r2.booleanValue()
            if (r6 != 0) goto L_0x0110
            com.google.android.gms.internal.measurement.aq r2 = r10.r()
            com.google.android.gms.internal.measurement.as r2 = r2.k()
            java.lang.String r4 = "Collection disabled with firebase_analytics_collection_enabled=0"
            goto L_0x00f9
        L_0x0110:
            if (r2 != 0) goto L_0x0123
            boolean r2 = com.google.android.gms.common.api.internal.GoogleServices.isMeasurementExplicitlyDisabled()
            if (r2 == 0) goto L_0x0123
            com.google.android.gms.internal.measurement.aq r2 = r10.r()
            com.google.android.gms.internal.measurement.as r2 = r2.k()
            java.lang.String r4 = "Collection disabled with google_app_measurement_enable=0"
            goto L_0x00f9
        L_0x0123:
            com.google.android.gms.internal.measurement.aq r2 = r10.r()
            com.google.android.gms.internal.measurement.as r2 = r2.w()
            java.lang.String r6 = "Collection enabled"
            r2.a(r6)
            goto L_0x0132
        L_0x0131:
            r4 = r5
        L_0x0132:
            java.lang.String r2 = ""
            r10.i = r2
            r10.g = r0
            r10.u()
            com.google.android.gms.internal.measurement.bu r0 = r10.q
            java.lang.String r0 = r0.p()
            if (r0 == 0) goto L_0x014c
            com.google.android.gms.internal.measurement.bu r0 = r10.q
            java.lang.String r0 = r0.p()
            r10.i = r0
            goto L_0x0180
        L_0x014c:
            java.lang.String r0 = com.google.android.gms.common.api.internal.GoogleServices.getGoogleAppId()     // Catch:{ IllegalStateException -> 0x016e }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IllegalStateException -> 0x016e }
            if (r1 == 0) goto L_0x0158
            java.lang.String r0 = ""
        L_0x0158:
            r10.i = r0     // Catch:{ IllegalStateException -> 0x016e }
            if (r4 == 0) goto L_0x0180
            com.google.android.gms.internal.measurement.aq r0 = r10.r()     // Catch:{ IllegalStateException -> 0x016e }
            com.google.android.gms.internal.measurement.as r0 = r0.w()     // Catch:{ IllegalStateException -> 0x016e }
            java.lang.String r1 = "App package, google app id"
            java.lang.String r2 = r10.a     // Catch:{ IllegalStateException -> 0x016e }
            java.lang.String r4 = r10.i     // Catch:{ IllegalStateException -> 0x016e }
            r0.a(r1, r2, r4)     // Catch:{ IllegalStateException -> 0x016e }
            goto L_0x0180
        L_0x016e:
            r0 = move-exception
            com.google.android.gms.internal.measurement.aq r1 = r10.r()
            com.google.android.gms.internal.measurement.as r1 = r1.h_()
            java.lang.String r2 = "getGoogleAppId or isMeasurementEnabled failed with exception. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.aq.a(r3)
            r1.a(r2, r3, r0)
        L_0x0180:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x0191
            android.content.Context r0 = r10.n()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r10.h = r0
            return
        L_0x0191:
            r10.h = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.al.A():void");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final String B() {
        byte[] bArr = new byte[16];
        p().h().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: 0000 */
    public final String C() {
        w();
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final String D() {
        w();
        return this.i;
    }

    /* access modifiers changed from: 0000 */
    public final int E() {
        w();
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final int F() {
        w();
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final zzeb a(String str) {
        d();
        b();
        String C = C();
        String D = D();
        w();
        String str2 = this.b;
        long E = (long) E();
        w();
        String str3 = this.d;
        long f2 = t().f();
        w();
        d();
        if (this.f == 0) {
            this.f = this.q.k().a(n(), n().getPackageName());
        }
        long j = this.f;
        boolean y = this.q.y();
        boolean z = !s().p;
        String G = G();
        w();
        boolean z2 = z;
        String str4 = G;
        long j2 = this.g;
        long z3 = this.q.z();
        int F = F();
        w t = t();
        t.b();
        Boolean b2 = t.b("google_analytics_adid_collection_enabled");
        boolean booleanValue = Boolean.valueOf(b2 == null || b2.booleanValue()).booleanValue();
        w t2 = t();
        t2.b();
        Boolean b3 = t2.b("google_analytics_ssaid_collection_enabled");
        zzeb zzeb = new zzeb(C, D, str2, E, str3, f2, j, str, y, z2, str4, j2, z3, F, booleanValue, Boolean.valueOf(b3 == null || b3.booleanValue()).booleanValue(), s().v());
        return zzeb;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
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
        return true;
    }
}
