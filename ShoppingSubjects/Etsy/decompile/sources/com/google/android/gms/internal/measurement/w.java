package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.ak.a;
import java.lang.reflect.InvocationTargetException;

public final class w extends co {
    private Boolean a;
    @NonNull
    private y b = x.a;
    private Boolean c;

    w(bu buVar) {
        super(buVar);
    }

    static String e() {
        return (String) ak.i.b();
    }

    public static long j() {
        return ((Long) ak.L.b()).longValue();
    }

    public static long k() {
        return ((Long) ak.l.b()).longValue();
    }

    public static boolean w() {
        return ((Boolean) ak.h.b()).booleanValue();
    }

    @WorkerThread
    public final int a(@Size(min = 1) String str) {
        return b(str, ak.w);
    }

    @WorkerThread
    public final long a(String str, @NonNull a<Long> aVar) {
        if (str != null) {
            String a2 = this.b.a(str, aVar.a());
            if (!TextUtils.isEmpty(a2)) {
                try {
                    return ((Long) aVar.a(Long.valueOf(Long.parseLong(a2)))).longValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Long) aVar.b()).longValue();
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull y yVar) {
        this.b = yVar;
    }

    @WorkerThread
    public final int b(String str, @NonNull a<Integer> aVar) {
        if (str != null) {
            String a2 = this.b.a(str, aVar.a());
            if (!TextUtils.isEmpty(a2)) {
                try {
                    return ((Integer) aVar.a(Integer.valueOf(Integer.parseInt(a2)))).intValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Integer) aVar.b()).intValue();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public final Boolean b(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        try {
            if (n().getPackageManager() == null) {
                r().h_().a("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(n()).getApplicationInfo(n().getPackageName(), 128);
            if (applicationInfo == null) {
                r().h_().a("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData == null) {
                r().h_().a("Failed to load metadata: Metadata bundle is null");
                return null;
            } else if (!applicationInfo.metaData.containsKey(str)) {
                return null;
            } else {
                return Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            }
        } catch (NameNotFoundException e) {
            r().h_().a("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @WorkerThread
    public final double c(String str, @NonNull a<Double> aVar) {
        if (str != null) {
            String a2 = this.b.a(str, aVar.a());
            if (!TextUtils.isEmpty(a2)) {
                try {
                    return ((Double) aVar.a(Double.valueOf(Double.parseDouble(a2)))).doubleValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Double) aVar.b()).doubleValue();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final boolean c(String str) {
        return "1".equals(this.b.a(str, "gaia_collection_enabled"));
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    public final boolean d(String str) {
        return "1".equals(this.b.a(str, "measurement.event_sampling_enabled"));
    }

    @WorkerThread
    public final boolean d(String str, @NonNull a<Boolean> aVar) {
        Object a2;
        if (str != null) {
            String a3 = this.b.a(str, aVar.a());
            if (!TextUtils.isEmpty(a3)) {
                a2 = aVar.a(Boolean.valueOf(Boolean.parseBoolean(a3)));
                return ((Boolean) a2).booleanValue();
            }
        }
        a2 = aVar.b();
        return ((Boolean) a2).booleanValue();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean e(String str) {
        return d(str, ak.U);
    }

    public final long f() {
        u();
        return 12780;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean f(String str) {
        return d(str, ak.W);
    }

    public final boolean g() {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    ApplicationInfo applicationInfo = n().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.c = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.c == null) {
                        this.c = Boolean.TRUE;
                        r().h_().a("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.c.booleanValue();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean g(String str) {
        return d(str, ak.X);
    }

    public final boolean h() {
        u();
        Boolean b2 = b("firebase_analytics_collection_deactivated");
        return b2 != null && b2.booleanValue();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean h(String str) {
        return d(str, ak.P);
    }

    public final Boolean i() {
        u();
        return b("firebase_analytics_collection_enabled");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final String i(String str) {
        a<String> aVar = ak.Q;
        return (String) (str == null ? aVar.b() : aVar.a(this.b.a(str, aVar.a())));
    }

    /* access modifiers changed from: 0000 */
    public final boolean j(String str) {
        return d(str, ak.Y);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean k(String str) {
        return d(str, ak.Z);
    }

    public final /* bridge */ /* synthetic */ ag l() {
        return super.l();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean l(String str) {
        return d(str, ak.ac);
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

    public final String v() {
        String str;
        as asVar;
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            e = e;
            asVar = r().h_();
            str = "Could not find SystemProperties class";
            asVar.a(str, e);
            return "";
        } catch (NoSuchMethodException e2) {
            e = e2;
            asVar = r().h_();
            str = "Could not find SystemProperties.get() method";
            asVar.a(str, e);
            return "";
        } catch (IllegalAccessException e3) {
            e = e3;
            asVar = r().h_();
            str = "Could not access SystemProperties.get()";
            asVar.a(str, e);
            return "";
        } catch (InvocationTargetException e4) {
            e = e4;
            asVar = r().h_();
            str = "SystemProperties.get() threw an exception";
            asVar.a(str, e);
            return "";
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean x() {
        if (this.a == null) {
            this.a = b("app_measurement_lite");
            if (this.a == null) {
                this.a = Boolean.valueOf(false);
            }
        }
        return this.a.booleanValue();
    }
}
