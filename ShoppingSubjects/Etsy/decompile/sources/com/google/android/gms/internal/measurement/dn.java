package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

public final class dn extends s {
    @VisibleForTesting
    protected dm a;
    private volatile dm b;
    private dm c;
    private final Map<Activity, dm> d = new ArrayMap();
    private dm e;
    private String f;

    public dn(bu buVar) {
        super(buVar);
    }

    @VisibleForTesting
    private static String a(String str) {
        String[] split = str.split("\\.");
        String str2 = split.length > 0 ? split[split.length - 1] : "";
        return str2.length() > 100 ? str2.substring(0, 100) : str2;
    }

    @MainThread
    private final void a(Activity activity, dm dmVar, boolean z) {
        dm dmVar2 = this.b == null ? this.c : this.b;
        if (dmVar.b == null) {
            dmVar = new dm(dmVar.a, a(activity.getClass().getCanonicalName()), dmVar.c);
        }
        this.c = this.b;
        this.b = dmVar;
        q().a((Runnable) new Cdo(this, z, dmVar2, dmVar));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void a(@NonNull dm dmVar) {
        e().a(m().elapsedRealtime());
        if (k().a(dmVar.d)) {
            dmVar.d = false;
        }
    }

    public static void a(dm dmVar, Bundle bundle, boolean z) {
        if (bundle == null || dmVar == null || (bundle.containsKey("_sc") && !z)) {
            if (bundle != null && dmVar == null && z) {
                bundle.remove("_sn");
                bundle.remove("_sc");
                bundle.remove("_si");
            }
            return;
        }
        if (dmVar.a != null) {
            bundle.putString("_sn", dmVar.a);
        } else {
            bundle.remove("_sn");
        }
        bundle.putString("_sc", dmVar.b);
        bundle.putLong("_si", dmVar.c);
    }

    @MainThread
    private final dm d(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        dm dmVar = (dm) this.d.get(activity);
        if (dmVar != null) {
            return dmVar;
        }
        dm dmVar2 = new dm(null, a(activity.getClass().getCanonicalName()), p().g());
        this.d.put(activity, dmVar2);
        return dmVar2;
    }

    @WorkerThread
    public final dm B() {
        w();
        d();
        return this.a;
    }

    public final dm C() {
        b();
        return this.b;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @MainThread
    public final void a(Activity activity) {
        a(activity, d(activity), false);
        n e2 = e();
        e2.q().a((Runnable) new q(e2, e2.m().elapsedRealtime()));
    }

    @MainThread
    public final void a(Activity activity, Bundle bundle) {
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.firebase.analytics.screen_service");
            if (bundle2 != null) {
                this.d.put(activity, new dm(bundle2.getString(ResponseConstants.NAME), bundle2.getString("referrer_name"), bundle2.getLong("id")));
            }
        }
    }

    @MainThread
    public final void a(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        if (!v.a()) {
            r().i().a("setCurrentScreen must be called from the main thread");
        } else if (this.b == null) {
            r().i().a("setCurrentScreen cannot be called while no activity active");
        } else if (this.d.get(activity) == null) {
            r().i().a("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = a(activity.getClass().getCanonicalName());
            }
            boolean equals = this.b.b.equals(str2);
            boolean b2 = fg.b(this.b.a, str);
            if (equals && b2) {
                r().j().a("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                r().i().a("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                r().w().a("Setting current screen to name, class", str == null ? "null" : str, str2);
                dm dmVar = new dm(str, str2, p().g());
                this.d.put(activity, dmVar);
                a(activity, dmVar, true);
            } else {
                r().i().a("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    @WorkerThread
    public final void a(String str, dm dmVar) {
        d();
        synchronized (this) {
            if (this.f == null || this.f.equals(str) || dmVar != null) {
                this.f = str;
                this.e = dmVar;
            }
        }
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @MainThread
    public final void b(Activity activity) {
        dm d2 = d(activity);
        this.c = this.b;
        this.b = null;
        q().a((Runnable) new dp(this, d2));
    }

    @MainThread
    public final void b(Activity activity, Bundle bundle) {
        if (bundle != null) {
            dm dmVar = (dm) this.d.get(activity);
            if (dmVar != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong("id", dmVar.c);
                bundle2.putString(ResponseConstants.NAME, dmVar.a);
                bundle2.putString("referrer_name", dmVar.b);
                bundle.putBundle("com.google.firebase.analytics.screen_service", bundle2);
            }
        }
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    @MainThread
    public final void c(Activity activity) {
        this.d.remove(activity);
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
