package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.etsy.android.lib.models.AppBuild;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
public final class gk {
    @VisibleForTesting
    int a = -1;
    @VisibleForTesting
    private long b = -1;
    @VisibleForTesting
    private long c = -1;
    @VisibleForTesting
    private int d = -1;
    @VisibleForTesting
    private long e = 0;
    private final Object f = new Object();
    @VisibleForTesting
    private final String g;
    @VisibleForTesting
    private int h = 0;
    @VisibleForTesting
    private int i = 0;

    public gk(String str) {
        this.g = str;
    }

    private static boolean a(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", AppBuild.ANDROID_PLATFORM);
        if (identifier != 0) {
            try {
                if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                    return true;
                }
                gv.d("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
                return false;
            } catch (NameNotFoundException unused) {
                gv.e("Fail to fetch AdActivity theme");
            }
        }
        gv.d("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
        return false;
    }

    public final Bundle a(Context context, String str) {
        Bundle bundle;
        synchronized (this.f) {
            bundle = new Bundle();
            bundle.putString("session_id", this.g);
            bundle.putLong("basets", this.c);
            bundle.putLong("currts", this.b);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.d);
            bundle.putInt("preqs_in_session", this.a);
            bundle.putLong("time_in_session", this.e);
            bundle.putInt("pclick", this.h);
            bundle.putInt("pimp", this.i);
            bundle.putBoolean("support_transparent_background", a(context));
        }
        return bundle;
    }

    public final void a() {
        synchronized (this.f) {
            this.h++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0090, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.gms.internal.ads.zzjj r11, long r12) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.f
            monitor-enter(r0)
            com.google.android.gms.internal.ads.gf r1 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ all -> 0x0091 }
            com.google.android.gms.internal.ads.gw r1 = r1.l()     // Catch:{ all -> 0x0091 }
            long r1 = r1.i()     // Catch:{ all -> 0x0091 }
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.ao.l()     // Catch:{ all -> 0x0091 }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x0091 }
            long r5 = r10.c     // Catch:{ all -> 0x0091 }
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x004b
            long r5 = r3 - r1
            com.google.android.gms.internal.ads.akb<java.lang.Long> r1 = com.google.android.gms.internal.ads.akl.aI     // Catch:{ all -> 0x0091 }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0091 }
            java.lang.Object r1 = r2.a(r1)     // Catch:{ all -> 0x0091 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ all -> 0x0091 }
            long r1 = r1.longValue()     // Catch:{ all -> 0x0091 }
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 <= 0) goto L_0x0039
            r1 = -1
            r10.a = r1     // Catch:{ all -> 0x0091 }
            goto L_0x0047
        L_0x0039:
            com.google.android.gms.internal.ads.gf r1 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ all -> 0x0091 }
            com.google.android.gms.internal.ads.gw r1 = r1.l()     // Catch:{ all -> 0x0091 }
            int r1 = r1.j()     // Catch:{ all -> 0x0091 }
            r10.a = r1     // Catch:{ all -> 0x0091 }
        L_0x0047:
            r10.c = r12     // Catch:{ all -> 0x0091 }
            long r12 = r10.c     // Catch:{ all -> 0x0091 }
        L_0x004b:
            r10.b = r12     // Catch:{ all -> 0x0091 }
            r12 = 1
            if (r11 == 0) goto L_0x0061
            android.os.Bundle r13 = r11.extras     // Catch:{ all -> 0x0091 }
            if (r13 == 0) goto L_0x0061
            android.os.Bundle r11 = r11.extras     // Catch:{ all -> 0x0091 }
            java.lang.String r13 = "gw"
            r1 = 2
            int r11 = r11.getInt(r13, r1)     // Catch:{ all -> 0x0091 }
            if (r11 != r12) goto L_0x0061
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            return
        L_0x0061:
            int r11 = r10.d     // Catch:{ all -> 0x0091 }
            int r11 = r11 + r12
            r10.d = r11     // Catch:{ all -> 0x0091 }
            int r11 = r10.a     // Catch:{ all -> 0x0091 }
            int r11 = r11 + r12
            r10.a = r11     // Catch:{ all -> 0x0091 }
            int r11 = r10.a     // Catch:{ all -> 0x0091 }
            if (r11 != 0) goto L_0x007f
            r11 = 0
            r10.e = r11     // Catch:{ all -> 0x0091 }
            com.google.android.gms.internal.ads.gf r11 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ all -> 0x0091 }
            com.google.android.gms.internal.ads.gw r11 = r11.l()     // Catch:{ all -> 0x0091 }
            r11.b(r3)     // Catch:{ all -> 0x0091 }
            goto L_0x008f
        L_0x007f:
            com.google.android.gms.internal.ads.gf r11 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ all -> 0x0091 }
            com.google.android.gms.internal.ads.gw r11 = r11.l()     // Catch:{ all -> 0x0091 }
            long r11 = r11.k()     // Catch:{ all -> 0x0091 }
            long r1 = r3 - r11
            r10.e = r1     // Catch:{ all -> 0x0091 }
        L_0x008f:
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            return
        L_0x0091:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.gk.a(com.google.android.gms.internal.ads.zzjj, long):void");
    }

    public final void b() {
        synchronized (this.f) {
            this.i++;
        }
    }
}
