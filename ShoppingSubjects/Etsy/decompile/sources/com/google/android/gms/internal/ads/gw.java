package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class gw {
    Editor a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    private kt<?> c;
    /* access modifiers changed from: private */
    public CopyOnWriteArraySet<ha> d = new CopyOnWriteArraySet<>();
    /* access modifiers changed from: private */
    public SharedPreferences e;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = true;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public String k = "";
    /* access modifiers changed from: private */
    public long l = 0;
    /* access modifiers changed from: private */
    public long m = 0;
    /* access modifiers changed from: private */
    public long n = 0;
    /* access modifiers changed from: private */
    public int o = -1;
    /* access modifiers changed from: private */
    public int p = 0;
    /* access modifiers changed from: private */
    public Set<String> q = Collections.emptySet();
    /* access modifiers changed from: private */
    public JSONObject r = new JSONObject();
    /* access modifiers changed from: private */
    public boolean s = true;
    /* access modifiers changed from: private */
    public boolean t = true;

    /* access modifiers changed from: private */
    public final void a(Bundle bundle) {
        new gy(this, bundle).c();
    }

    /* access modifiers changed from: private */
    @TargetApi(23)
    public static boolean n() {
        return PlatformVersion.isAtLeastM() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    private final void o() {
        if (this.c != null && !this.c.isDone()) {
            try {
                this.c.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                gv.c("Interrupted while waiting for preferences loaded.", e2);
            } catch (CancellationException | ExecutionException | TimeoutException e3) {
                gv.b("Fail to initialize AdSharedPreferenceManager.", e3);
            }
        }
    }

    /* access modifiers changed from: private */
    public final Bundle p() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.b) {
            bundle.putBoolean("use_https", this.g);
            bundle.putBoolean("content_url_opted_out", this.s);
            bundle.putBoolean("content_vertical_opted_out", this.t);
            bundle.putBoolean("auto_collect_location", this.j);
            bundle.putInt("version_code", this.p);
            bundle.putStringArray("never_pool_slots", (String[]) this.q.toArray(new String[this.q.size()]));
            bundle.putString("app_settings_json", this.k);
            bundle.putLong("app_settings_last_update_ms", this.l);
            bundle.putLong("app_last_background_time_ms", this.m);
            bundle.putInt("request_in_session_count", this.o);
            bundle.putLong("first_ad_req_time_ms", this.n);
            bundle.putString("native_advanced_settings", this.r.toString());
            if (this.h != null) {
                bundle.putString("content_url_hashes", this.h);
            }
            if (this.i != null) {
                bundle.putString("content_vertical_hashes", this.i);
            }
        }
        return bundle;
    }

    public final void a(int i2) {
        o();
        synchronized (this.b) {
            if (this.p != i2) {
                this.p = i2;
                if (this.a != null) {
                    this.a.putInt("version_code", i2);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("version_code", i2);
                a(bundle);
            }
        }
    }

    public final void a(long j2) {
        o();
        synchronized (this.b) {
            if (this.m != j2) {
                this.m = j2;
                if (this.a != null) {
                    this.a.putLong("app_last_background_time_ms", j2);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("app_last_background_time_ms", j2);
                a(bundle);
            }
        }
    }

    public final void a(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.c = (kt) new gx(this, context).c();
    }

    public final void a(ha haVar) {
        synchronized (this.b) {
            if (this.c != null && this.c.isDone()) {
                haVar.a(p());
            }
            this.d.add(haVar);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r4) {
        /*
            r3 = this;
            r3.o()
            java.lang.Object r0 = r3.b
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0034
            java.lang.String r1 = r3.h     // Catch:{ all -> 0x0032 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0034
        L_0x0011:
            r3.h = r4     // Catch:{ all -> 0x0032 }
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0023
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0032 }
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0032 }
            r1.apply()     // Catch:{ all -> 0x0032 }
        L_0x0023:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0032 }
            r3.a(r1)     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0032:
            r4 = move-exception
            goto L_0x0036
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0036:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.gw.a(java.lang.String):void");
    }

    public final void a(String str, String str2, boolean z) {
        o();
        synchronized (this.b) {
            JSONArray optJSONArray = this.r.optJSONArray(str);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
            }
            int length = optJSONArray.length();
            int i2 = 0;
            while (true) {
                if (i2 < optJSONArray.length()) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        if (!str2.equals(optJSONObject.optString("template_id"))) {
                            i2++;
                        } else if (!z || optJSONObject.optBoolean("uses_media_view", false) != z) {
                            length = i2;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("template_id", str2);
                jSONObject.put("uses_media_view", z);
                jSONObject.put("timestamp_ms", ao.l().currentTimeMillis());
                optJSONArray.put(length, jSONObject);
                this.r.put(str, optJSONArray);
            } catch (JSONException e2) {
                gv.c("Could not update native advanced settings", e2);
            }
            if (this.a != null) {
                this.a.putString("native_advanced_settings", this.r.toString());
                this.a.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", this.r.toString());
            a(bundle);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r4) {
        /*
            r3 = this;
            r3.o()
            java.lang.Object r0 = r3.b
            monitor-enter(r0)
            boolean r1 = r3.g     // Catch:{ all -> 0x0031 }
            if (r1 != r4) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x000c:
            r3.g = r4     // Catch:{ all -> 0x0031 }
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x001e
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "use_https"
            r1.putBoolean(r2, r4)     // Catch:{ all -> 0x0031 }
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0031 }
            r1.apply()     // Catch:{ all -> 0x0031 }
        L_0x001e:
            boolean r1 = r3.f     // Catch:{ all -> 0x0031 }
            if (r1 != 0) goto L_0x002f
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0031 }
            r1.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "use_https"
            r1.putBoolean(r2, r4)     // Catch:{ all -> 0x0031 }
            r3.a(r1)     // Catch:{ all -> 0x0031 }
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.gw.a(boolean):void");
    }

    public final boolean a() {
        boolean z;
        o();
        synchronized (this.b) {
            if (!this.g) {
                if (!this.f) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public final void b(int i2) {
        o();
        synchronized (this.b) {
            if (this.o != i2) {
                this.o = i2;
                if (this.a != null) {
                    this.a.putInt("request_in_session_count", i2);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("request_in_session_count", i2);
                a(bundle);
            }
        }
    }

    public final void b(long j2) {
        o();
        synchronized (this.b) {
            if (this.n != j2) {
                this.n = j2;
                if (this.a != null) {
                    this.a.putLong("first_ad_req_time_ms", j2);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("first_ad_req_time_ms", j2);
                a(bundle);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(java.lang.String r4) {
        /*
            r3 = this;
            r3.o()
            java.lang.Object r0 = r3.b
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0034
            java.lang.String r1 = r3.i     // Catch:{ all -> 0x0032 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0034
        L_0x0011:
            r3.i = r4     // Catch:{ all -> 0x0032 }
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0023
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0032 }
            android.content.SharedPreferences$Editor r1 = r3.a     // Catch:{ all -> 0x0032 }
            r1.apply()     // Catch:{ all -> 0x0032 }
        L_0x0023:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0032 }
            r3.a(r1)     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0032:
            r4 = move-exception
            goto L_0x0036
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0036:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.gw.b(java.lang.String):void");
    }

    public final void b(boolean z) {
        o();
        synchronized (this.b) {
            if (this.s != z) {
                this.s = z;
                if (this.a != null) {
                    this.a.putBoolean("content_url_opted_out", z);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.s);
                bundle.putBoolean("content_vertical_opted_out", this.t);
                a(bundle);
            }
        }
    }

    public final boolean b() {
        boolean z;
        o();
        synchronized (this.b) {
            z = this.s;
        }
        return z;
    }

    public final String c() {
        String str;
        o();
        synchronized (this.b) {
            str = this.h;
        }
        return str;
    }

    public final void c(String str) {
        o();
        synchronized (this.b) {
            if (!this.q.contains(str)) {
                this.q.add(str);
                if (this.a != null) {
                    this.a.putStringSet("never_pool_slots", this.q);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.q.toArray(new String[this.q.size()]));
                a(bundle);
            }
        }
    }

    public final void c(boolean z) {
        o();
        synchronized (this.b) {
            if (this.t != z) {
                this.t = z;
                if (this.a != null) {
                    this.a.putBoolean("content_vertical_opted_out", z);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.s);
                bundle.putBoolean("content_vertical_opted_out", this.t);
                a(bundle);
            }
        }
    }

    public final void d(String str) {
        o();
        synchronized (this.b) {
            if (this.q.contains(str)) {
                this.q.remove(str);
                if (this.a != null) {
                    this.a.putStringSet("never_pool_slots", this.q);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.q.toArray(new String[this.q.size()]));
                a(bundle);
            }
        }
    }

    public final void d(boolean z) {
        o();
        synchronized (this.b) {
            if (this.j != z) {
                this.j = z;
                if (this.a != null) {
                    this.a.putBoolean("auto_collect_location", z);
                    this.a.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("auto_collect_location", z);
                a(bundle);
            }
        }
    }

    public final boolean d() {
        boolean z;
        o();
        synchronized (this.b) {
            z = this.t;
        }
        return z;
    }

    public final String e() {
        String str;
        o();
        synchronized (this.b) {
            str = this.i;
        }
        return str;
    }

    public final boolean e(String str) {
        boolean contains;
        o();
        synchronized (this.b) {
            contains = this.q.contains(str);
        }
        return contains;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f(java.lang.String r6) {
        /*
            r5 = this;
            r5.o()
            java.lang.Object r0 = r5.b
            monitor-enter(r0)
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.ao.l()     // Catch:{ all -> 0x004a }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x004a }
            r5.l = r1     // Catch:{ all -> 0x004a }
            if (r6 == 0) goto L_0x0048
            java.lang.String r3 = r5.k     // Catch:{ all -> 0x004a }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x004a }
            if (r3 == 0) goto L_0x001b
            goto L_0x0048
        L_0x001b:
            r5.k = r6     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences$Editor r3 = r5.a     // Catch:{ all -> 0x004a }
            if (r3 == 0) goto L_0x0034
            android.content.SharedPreferences$Editor r3 = r5.a     // Catch:{ all -> 0x004a }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences$Editor r3 = r5.a     // Catch:{ all -> 0x004a }
            java.lang.String r4 = "app_settings_last_update_ms"
            r3.putLong(r4, r1)     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences$Editor r3 = r5.a     // Catch:{ all -> 0x004a }
            r3.apply()     // Catch:{ all -> 0x004a }
        L_0x0034:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x004a }
            r3.<init>()     // Catch:{ all -> 0x004a }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x004a }
            java.lang.String r6 = "app_settings_last_update_ms"
            r3.putLong(r6, r1)     // Catch:{ all -> 0x004a }
            r5.a(r3)     // Catch:{ all -> 0x004a }
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return
        L_0x004a:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.gw.f(java.lang.String):void");
    }

    public final boolean f() {
        boolean z;
        o();
        synchronized (this.b) {
            z = this.j;
        }
        return z;
    }

    public final int g() {
        int i2;
        o();
        synchronized (this.b) {
            i2 = this.p;
        }
        return i2;
    }

    public final ge h() {
        ge geVar;
        o();
        synchronized (this.b) {
            geVar = new ge(this.k, this.l);
        }
        return geVar;
    }

    public final long i() {
        long j2;
        o();
        synchronized (this.b) {
            j2 = this.m;
        }
        return j2;
    }

    public final int j() {
        int i2;
        o();
        synchronized (this.b) {
            i2 = this.o;
        }
        return i2;
    }

    public final long k() {
        long j2;
        o();
        synchronized (this.b) {
            j2 = this.n;
        }
        return j2;
    }

    public final JSONObject l() {
        JSONObject jSONObject;
        o();
        synchronized (this.b) {
            jSONObject = this.r;
        }
        return jSONObject;
    }

    public final void m() {
        o();
        synchronized (this.b) {
            this.r = new JSONObject();
            if (this.a != null) {
                this.a.remove("native_advanced_settings");
                this.a.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            a(bundle);
        }
    }
}
