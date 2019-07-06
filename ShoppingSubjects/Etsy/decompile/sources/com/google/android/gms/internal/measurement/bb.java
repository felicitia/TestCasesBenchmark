package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

final class bb extends cp {
    @VisibleForTesting
    static final Pair<String, Long> a = new Pair<>("", Long.valueOf(0));
    public bf b;
    public final be c = new be(this, "last_upload", 0);
    public final be d = new be(this, "last_upload_attempt", 0);
    public final be e = new be(this, "backoff", 0);
    public final be f = new be(this, "last_delete_stale", 0);
    public final be g = new be(this, "midnight_offset", 0);
    public final be h = new be(this, "first_open_time", 0);
    public final be i = new be(this, "app_install_time", 0);
    public final bg j = new bg(this, "app_instance_id", null);
    public final be k = new be(this, "time_before_start", 10000);
    public final be l = new be(this, "session_timeout", 1800000);
    public final bd m = new bd(this, "start_new_session", true);
    public final be n = new be(this, "last_pause_time", 0);
    public final be o = new be(this, "time_active", 0);
    public boolean p;
    private SharedPreferences r;
    private String s;
    private boolean t;
    private long u;
    private String v;
    private long w;
    private final Object x = new Object();

    bb(bu buVar) {
        super(buVar);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final SharedPreferences x() {
        d();
        z();
        return this.r;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @NonNull
    public final Pair<String, Boolean> a(String str) {
        d();
        long elapsedRealtime = m().elapsedRealtime();
        if (this.s != null && elapsedRealtime < this.u) {
            return new Pair<>(this.s, Boolean.valueOf(this.t));
        }
        this.u = elapsedRealtime + t().a(str, ak.j);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(n());
            if (advertisingIdInfo != null) {
                this.s = advertisingIdInfo.getId();
                this.t = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.s == null) {
                this.s = "";
            }
        } catch (Exception e2) {
            r().v().a("Unable to get advertising id", e2);
            this.s = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.s, Boolean.valueOf(this.t));
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void a(boolean z) {
        d();
        r().w().a("Setting useService", Boolean.valueOf(z));
        Editor edit = x().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final String b(String str) {
        d();
        String str2 = (String) a(str).first;
        MessageDigest i2 = fg.i();
        if (i2 == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, i2.digest(str2.getBytes()))});
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void b(boolean z) {
        d();
        r().w().a("Setting measurementEnabled", Boolean.valueOf(z));
        Editor edit = x().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void c(String str) {
        d();
        Editor edit = x().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean c(boolean z) {
        d();
        return x().getBoolean("measurement_enabled", z);
    }

    /* access modifiers changed from: 0000 */
    public final void d(String str) {
        synchronized (this.x) {
            this.v = str;
            this.w = m().elapsedRealtime();
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void d(boolean z) {
        d();
        r().w().a("Updating deferred analytics collection", Boolean.valueOf(z));
        Editor edit = x().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        return true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void f() {
        this.r = n().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.p = this.r.getBoolean("has_been_opened", false);
        if (!this.p) {
            Editor edit = this.r.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        bf bfVar = new bf(this, "health_monitor", Math.max(0, ((Long) ak.k.b()).longValue()));
        this.b = bfVar;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final String g() {
        d();
        return x().getString("gmp_app_id", null);
    }

    /* access modifiers changed from: 0000 */
    public final String h() {
        synchronized (this.x) {
            if (Math.abs(m().elapsedRealtime() - this.w) >= 1000) {
                return null;
            }
            String str = this.v;
            return str;
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final Boolean i() {
        d();
        if (!x().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(x().getBoolean("use_service", false));
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void j() {
        d();
        r().w().a("Clearing collection preferences.");
        boolean contains = x().contains("measurement_enabled");
        boolean z = true;
        if (contains) {
            z = c(true);
        }
        Editor edit = x().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            b(z);
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final String k() {
        d();
        String string = x().getString("previous_os_version", null);
        l().z();
        String str = VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            Editor edit = x().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean v() {
        d();
        return x().getBoolean("deferred_analytics_collection", false);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean w() {
        return this.r.contains("deferred_analytics_collection");
    }
}
