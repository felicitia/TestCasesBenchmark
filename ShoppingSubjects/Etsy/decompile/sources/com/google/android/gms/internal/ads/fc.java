package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class fc implements fl {
    /* access modifiers changed from: private */
    public static List<Future<Void>> a = Collections.synchronizedList(new ArrayList());
    private static ScheduledExecutorService b = Executors.newSingleThreadScheduledExecutor();
    /* access modifiers changed from: private */
    public final aav c;
    private final LinkedHashMap<String, abd> d;
    private final List<String> e = new ArrayList();
    private final List<String> f = new ArrayList();
    private final Context g;
    private final fn h;
    @VisibleForTesting
    private boolean i;
    private final zzaiq j;
    private final fo k;
    /* access modifiers changed from: private */
    public final Object l = new Object();
    private HashSet<String> m = new HashSet<>();
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    public fc(Context context, zzang zzang, zzaiq zzaiq, String str, fn fnVar) {
        Preconditions.checkNotNull(zzaiq, "SafeBrowsing config is not present.");
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.g = context;
        this.d = new LinkedHashMap<>();
        this.h = fnVar;
        this.j = zzaiq;
        for (String lowerCase : this.j.zzcnh) {
            this.m.add(lowerCase.toLowerCase(Locale.ENGLISH));
        }
        this.m.remove("cookie".toLowerCase(Locale.ENGLISH));
        aav aav = new aav();
        aav.a = Integer.valueOf(8);
        aav.b = str;
        aav.c = str;
        aav.d = new aaw();
        aav.d.a = this.j.zzcnd;
        abe abe = new abe();
        abe.a = zzang.zzcw;
        abe.c = Boolean.valueOf(Wrappers.packageManager(this.g).isCallerInstantApp());
        long apkVersion = (long) GoogleApiAvailabilityLight.getInstance().getApkVersion(this.g);
        if (apkVersion > 0) {
            abe.b = Long.valueOf(apkVersion);
        }
        aav.h = abe;
        this.c = aav;
        this.k = new fo(this.g, this.j.zzcnk, this);
    }

    static final /* synthetic */ Void d(String str) {
        return null;
    }

    @Nullable
    private final abd e(String str) {
        abd abd;
        synchronized (this.l) {
            abd = (abd) this.d.get(str);
        }
        return abd;
    }

    @VisibleForTesting
    private final kt<Void> f() {
        kt<Void> a2;
        abd[] abdArr;
        if (!((this.i && this.j.zzcnj) || (this.p && this.j.zzcni) || (!this.i && this.j.zzcng))) {
            return ki.a(null);
        }
        synchronized (this.l) {
            this.c.e = new abd[this.d.size()];
            this.d.values().toArray(this.c.e);
            this.c.i = (String[]) this.e.toArray(new String[0]);
            this.c.j = (String[]) this.f.toArray(new String[0]);
            if (fk.a()) {
                String str = this.c.b;
                String str2 = this.c.f;
                StringBuilder sb = new StringBuilder(53 + String.valueOf(str).length() + String.valueOf(str2).length());
                sb.append("Sending SB report\n  url: ");
                sb.append(str);
                sb.append("\n  clickUrl: ");
                sb.append(str2);
                sb.append("\n  resources: \n");
                StringBuilder sb2 = new StringBuilder(sb.toString());
                for (abd abd : this.c.e) {
                    sb2.append("    [");
                    sb2.append(abd.e.length);
                    sb2.append("] ");
                    sb2.append(abd.b);
                }
                fk.a(sb2.toString());
            }
            kt a3 = new in(this.g).a(1, this.j.zzcne, null, aar.a((aar) this.c));
            if (fk.a()) {
                a3.a(new fh(this), hb.a);
            }
            a2 = ki.a(a3, fe.a, kz.b);
        }
        return a2;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ kt a(Map map) throws Exception {
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    JSONArray optJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                    if (optJSONArray != null) {
                        synchronized (this.l) {
                            int length = optJSONArray.length();
                            abd e2 = e(str);
                            if (e2 == null) {
                                String str2 = "Cannot find the corresponding resource object for ";
                                String valueOf = String.valueOf(str);
                                fk.a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                            } else {
                                e2.e = new String[length];
                                boolean z = false;
                                for (int i2 = 0; i2 < length; i2++) {
                                    e2.e[i2] = optJSONArray.getJSONObject(i2).getString("threat_type");
                                }
                                boolean z2 = this.i;
                                if (length > 0) {
                                    z = true;
                                }
                                this.i = z | z2;
                            }
                        }
                    }
                }
            } catch (JSONException e3) {
                String str3 = "Failed to get SafeBrowsing metadata";
                if (((Boolean) ajh.f().a(akl.cB)).booleanValue()) {
                    gv.a(str3, e3);
                }
                return ki.a((Throwable) new Exception("Safebrowsing report transmission failed."));
            }
        }
        if (this.i) {
            synchronized (this.l) {
                this.c.a = Integer.valueOf(9);
            }
        }
        return f();
    }

    public final zzaiq a() {
        return this.j;
    }

    public final void a(View view) {
        if (this.j.zzcnf && !this.o) {
            ao.e();
            Bitmap b2 = hd.b(view);
            if (b2 == null) {
                fk.a("Failed to capture the webview bitmap.");
                return;
            }
            this.o = true;
            hd.a((Runnable) new ff(this, b2));
        }
    }

    public final void a(String str) {
        synchronized (this.l) {
            this.c.f = str;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, int r9) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.l
            monitor-enter(r0)
            r1 = 3
            if (r9 != r1) goto L_0x000d
            r2 = 1
            r6.p = r2     // Catch:{ all -> 0x000a }
            goto L_0x000d
        L_0x000a:
            r7 = move-exception
            goto L_0x00ca
        L_0x000d:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.abd> r2 = r6.d     // Catch:{ all -> 0x000a }
            boolean r2 = r2.containsKey(r7)     // Catch:{ all -> 0x000a }
            if (r2 == 0) goto L_0x0027
            if (r9 != r1) goto L_0x0025
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.abd> r8 = r6.d     // Catch:{ all -> 0x000a }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ all -> 0x000a }
            com.google.android.gms.internal.ads.abd r7 = (com.google.android.gms.internal.ads.abd) r7     // Catch:{ all -> 0x000a }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x000a }
            r7.d = r8     // Catch:{ all -> 0x000a }
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return
        L_0x0027:
            com.google.android.gms.internal.ads.abd r1 = new com.google.android.gms.internal.ads.abd     // Catch:{ all -> 0x000a }
            r1.<init>()     // Catch:{ all -> 0x000a }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x000a }
            r1.d = r9     // Catch:{ all -> 0x000a }
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.abd> r9 = r6.d     // Catch:{ all -> 0x000a }
            int r9 = r9.size()     // Catch:{ all -> 0x000a }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x000a }
            r1.a = r9     // Catch:{ all -> 0x000a }
            r1.b = r7     // Catch:{ all -> 0x000a }
            com.google.android.gms.internal.ads.aay r9 = new com.google.android.gms.internal.ads.aay     // Catch:{ all -> 0x000a }
            r9.<init>()     // Catch:{ all -> 0x000a }
            r1.c = r9     // Catch:{ all -> 0x000a }
            java.util.HashSet<java.lang.String> r9 = r6.m     // Catch:{ all -> 0x000a }
            int r9 = r9.size()     // Catch:{ all -> 0x000a }
            if (r9 <= 0) goto L_0x00c3
            if (r8 == 0) goto L_0x00c3
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x000a }
            r9.<init>()     // Catch:{ all -> 0x000a }
            java.util.Set r8 = r8.entrySet()     // Catch:{ all -> 0x000a }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x000a }
        L_0x005e:
            boolean r2 = r8.hasNext()     // Catch:{ all -> 0x000a }
            if (r2 == 0) goto L_0x00b6
            java.lang.Object r2 = r8.next()     // Catch:{ all -> 0x000a }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x000a }
            java.lang.Object r3 = r2.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            if (r3 == 0) goto L_0x0077
            java.lang.Object r3 = r2.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            goto L_0x0079
        L_0x0077:
            java.lang.String r3 = ""
        L_0x0079:
            java.lang.Object r4 = r2.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            if (r4 == 0) goto L_0x0086
            java.lang.Object r2 = r2.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            goto L_0x0088
        L_0x0086:
            java.lang.String r2 = ""
        L_0x0088:
            java.util.Locale r4 = java.util.Locale.ENGLISH     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            java.lang.String r4 = r3.toLowerCase(r4)     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            java.util.HashSet<java.lang.String> r5 = r6.m     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            boolean r4 = r5.contains(r4)     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            if (r4 != 0) goto L_0x0097
            goto L_0x005e
        L_0x0097:
            com.google.android.gms.internal.ads.aax r4 = new com.google.android.gms.internal.ads.aax     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            r4.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            java.lang.String r5 = "UTF-8"
            byte[] r3 = r3.getBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            r4.a = r3     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            java.lang.String r3 = "UTF-8"
            byte[] r2 = r2.getBytes(r3)     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            r4.b = r2     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            r9.add(r4)     // Catch:{ UnsupportedEncodingException -> 0x00b0 }
            goto L_0x005e
        L_0x00b0:
            java.lang.String r2 = "Cannot convert string to bytes, skip header."
            com.google.android.gms.internal.ads.fk.a(r2)     // Catch:{ all -> 0x000a }
            goto L_0x005e
        L_0x00b6:
            int r8 = r9.size()     // Catch:{ all -> 0x000a }
            com.google.android.gms.internal.ads.aax[] r8 = new com.google.android.gms.internal.ads.aax[r8]     // Catch:{ all -> 0x000a }
            r9.toArray(r8)     // Catch:{ all -> 0x000a }
            com.google.android.gms.internal.ads.aay r9 = r1.c     // Catch:{ all -> 0x000a }
            r9.a = r8     // Catch:{ all -> 0x000a }
        L_0x00c3:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.abd> r8 = r6.d     // Catch:{ all -> 0x000a }
            r8.put(r7, r1)     // Catch:{ all -> 0x000a }
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return
        L_0x00ca:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.fc.a(java.lang.String, java.util.Map, int):void");
    }

    public final String[] a(String[] strArr) {
        return (String[]) this.k.a(strArr).toArray(new String[0]);
    }

    /* access modifiers changed from: 0000 */
    public final void b(String str) {
        synchronized (this.l) {
            this.e.add(str);
        }
    }

    public final boolean b() {
        return PlatformVersion.isAtLeastKitKat() && this.j.zzcnf && !this.o;
    }

    public final void c() {
        this.n = true;
    }

    /* access modifiers changed from: 0000 */
    public final void c(String str) {
        synchronized (this.l) {
            this.f.add(str);
        }
    }

    public final void d() {
        synchronized (this.l) {
            kt a2 = ki.a(this.h.a(this.g, this.d.keySet()), (kd<? super A, ? extends B>) new fd<Object,Object>(this), kz.b);
            kt a3 = ki.a(a2, 10, TimeUnit.SECONDS, b);
            ki.a(a2, (kf<V>) new fg<V>(this, a3), kz.b);
            a.add(a3);
        }
    }
}
