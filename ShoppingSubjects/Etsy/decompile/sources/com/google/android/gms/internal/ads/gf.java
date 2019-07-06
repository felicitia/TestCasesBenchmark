package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.GuardedBy;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

@bu
public final class gf implements ha {
    /* access modifiers changed from: private */
    public final Object a = new Object();
    private aen b;
    private final gm c = new gm();
    private final gw d = new gw();
    private boolean e = false;
    /* access modifiers changed from: private */
    public Context f;
    /* access modifiers changed from: private */
    public zzang g;
    /* access modifiers changed from: private */
    public ako h = null;
    private age i = null;
    private afz j = null;
    private Boolean k = null;
    private String l;
    private final AtomicInteger m = new AtomicInteger(0);
    private final gi n = new gi(null);
    private final Object o = new Object();
    @GuardedBy("mGrantedPermissionLock")
    private kt<ArrayList<String>> p;

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.age a(android.content.Context r4, boolean r5, boolean r6) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.akl.Q
            com.google.android.gms.internal.ads.akj r1 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r0 = r1.a(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 0
            if (r0 != 0) goto L_0x0014
            return r1
        L_0x0014:
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()
            if (r0 != 0) goto L_0x001b
            return r1
        L_0x001b:
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.akl.Y
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r0 = r2.a(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0040
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.akl.W
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r0 = r2.a(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0040
            return r1
        L_0x0040:
            if (r5 == 0) goto L_0x0045
            if (r6 == 0) goto L_0x0045
            return r1
        L_0x0045:
            java.lang.Object r5 = r3.a
            monitor-enter(r5)
            android.os.Looper r6 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x007f }
            if (r6 == 0) goto L_0x007d
            if (r4 != 0) goto L_0x0051
            goto L_0x007d
        L_0x0051:
            com.google.android.gms.internal.ads.afz r6 = r3.j     // Catch:{ all -> 0x007f }
            if (r6 != 0) goto L_0x005c
            com.google.android.gms.internal.ads.afz r6 = new com.google.android.gms.internal.ads.afz     // Catch:{ all -> 0x007f }
            r6.<init>()     // Catch:{ all -> 0x007f }
            r3.j = r6     // Catch:{ all -> 0x007f }
        L_0x005c:
            com.google.android.gms.internal.ads.age r6 = r3.i     // Catch:{ all -> 0x007f }
            if (r6 != 0) goto L_0x006f
            com.google.android.gms.internal.ads.age r6 = new com.google.android.gms.internal.ads.age     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.ads.afz r0 = r3.j     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.ads.zzang r1 = r3.g     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.ads.bs r4 = com.google.android.gms.internal.ads.bo.a(r4, r1)     // Catch:{ all -> 0x007f }
            r6.<init>(r0, r4)     // Catch:{ all -> 0x007f }
            r3.i = r6     // Catch:{ all -> 0x007f }
        L_0x006f:
            com.google.android.gms.internal.ads.age r4 = r3.i     // Catch:{ all -> 0x007f }
            r4.a()     // Catch:{ all -> 0x007f }
            java.lang.String r4 = "start fetching content..."
            com.google.android.gms.internal.ads.gv.d(r4)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.ads.age r4 = r3.i     // Catch:{ all -> 0x007f }
            monitor-exit(r5)     // Catch:{ all -> 0x007f }
            return r4
        L_0x007d:
            monitor-exit(r5)     // Catch:{ all -> 0x007f }
            return r1
        L_0x007f:
            r4 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x007f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.gf.a(android.content.Context, boolean, boolean):com.google.android.gms.internal.ads.age");
    }

    @TargetApi(16)
    private static ArrayList<String> b(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (packageInfo.requestedPermissions == null || packageInfo.requestedPermissionsFlags == null) {
                return arrayList;
            }
            for (int i2 = 0; i2 < packageInfo.requestedPermissions.length; i2++) {
                if ((packageInfo.requestedPermissionsFlags[i2] & 2) != 0) {
                    arrayList.add(packageInfo.requestedPermissions[i2]);
                }
            }
            return arrayList;
        } catch (NameNotFoundException unused) {
        }
    }

    public final age a(Context context) {
        return a(context, this.d.b(), this.d.d());
    }

    public final gm a() {
        return this.c;
    }

    @TargetApi(23)
    public final void a(Context context, zzang zzang) {
        ako ako;
        synchronized (this.a) {
            if (!this.e) {
                this.f = context.getApplicationContext();
                this.g = zzang;
                ao.h().a((agd) ao.j());
                this.d.a(this.f);
                this.d.a((ha) this);
                bo.a(this.f, this.g);
                this.l = ao.e().b(context, zzang.zzcw);
                this.b = new aen(context.getApplicationContext(), this.g);
                ao.n();
                if (!((Boolean) ajh.f().a(akl.N)).booleanValue()) {
                    gv.a("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                    ako = null;
                } else {
                    ako = new ako();
                }
                this.h = ako;
                kg.a((kt) new gh(this).c(), "AppState.registerCsiReporter");
                this.e = true;
                n();
            }
        }
    }

    public final void a(Bundle bundle) {
        if (bundle.containsKey("content_url_opted_out") && bundle.containsKey("content_vertical_opted_out")) {
            a(this.f, bundle.getBoolean("content_url_opted_out"), bundle.getBoolean("content_vertical_opted_out"));
        }
    }

    public final void a(Boolean bool) {
        synchronized (this.a) {
            this.k = bool;
        }
    }

    public final void a(Throwable th, String str) {
        bo.a(this.f, this.g).a(th, str);
    }

    public final void a(boolean z) {
        this.n.a(z);
    }

    public final ako b() {
        ako ako;
        synchronized (this.a) {
            ako = this.h;
        }
        return ako;
    }

    public final void b(Throwable th, String str) {
        bo.a(this.f, this.g).a(th, str, ((Float) ajh.f().a(akl.f)).floatValue());
    }

    public final Boolean c() {
        Boolean bool;
        synchronized (this.a) {
            bool = this.k;
        }
        return bool;
    }

    public final boolean d() {
        return this.n.a();
    }

    public final boolean e() {
        return this.n.b();
    }

    public final void f() {
        this.n.c();
    }

    public final aen g() {
        return this.b;
    }

    public final Resources h() {
        if (this.g.zzcvg) {
            return this.f.getResources();
        }
        try {
            DynamiteModule a2 = DynamiteModule.a(this.f, DynamiteModule.a, ModuleDescriptor.MODULE_ID);
            if (a2 != null) {
                return a2.a().getResources();
            }
            return null;
        } catch (LoadingException e2) {
            gv.c("Cannot load resource from dynamite apk or local jar", e2);
            return null;
        }
    }

    public final void i() {
        this.m.incrementAndGet();
    }

    public final void j() {
        this.m.decrementAndGet();
    }

    public final int k() {
        return this.m.get();
    }

    public final gw l() {
        gw gwVar;
        synchronized (this.a) {
            gwVar = this.d;
        }
        return gwVar;
    }

    public final Context m() {
        return this.f;
    }

    public final kt<ArrayList<String>> n() {
        if (this.f != null && PlatformVersion.isAtLeastJellyBean()) {
            if (!((Boolean) ajh.f().a(akl.bH)).booleanValue()) {
                synchronized (this.o) {
                    if (this.p != null) {
                        kt<ArrayList<String>> ktVar = this.p;
                        return ktVar;
                    }
                    kt<ArrayList<String>> a2 = hb.a((Callable<T>) new gg<T>(this));
                    this.p = a2;
                    return a2;
                }
            }
        }
        return ki.a(new ArrayList());
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ ArrayList o() throws Exception {
        return b(this.f);
    }
}
