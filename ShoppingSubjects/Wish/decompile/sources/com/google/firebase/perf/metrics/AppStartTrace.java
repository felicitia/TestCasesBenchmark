package com.google.firebase.perf.metrics;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzh;
import com.google.android.gms.internal.firebase-perf.zzx;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class AppStartTrace implements ActivityLifecycleCallbacks {
    private static final long zzdb = TimeUnit.MINUTES.toMicros(1);
    private static volatile AppStartTrace zzdc;
    private boolean mRegistered = false;
    private final zzx zzak;
    private Context zzdd;
    private WeakReference<Activity> zzde;
    private WeakReference<Activity> zzdf;
    private boolean zzdg = false;
    /* access modifiers changed from: private */
    public zzaa zzdh = null;
    private zzaa zzdi = null;
    private zzaa zzdj = null;
    /* access modifiers changed from: private */
    public boolean zzdk = false;
    private zzh zzq = null;

    public static class zza implements Runnable {
        private final AppStartTrace zzdl;

        public zza(AppStartTrace appStartTrace) {
            this.zzdl = appStartTrace;
        }

        public final void run() {
            if (this.zzdl.zzdh == null) {
                this.zzdl.zzdk = true;
            }
        }
    }

    @Keep
    public static void setLauncherActivityOnCreateTime(String str) {
    }

    @Keep
    public static void setLauncherActivityOnResumeTime(String str) {
    }

    @Keep
    public static void setLauncherActivityOnStartTime(String str) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public static AppStartTrace zzah() {
        if (zzdc != null) {
            return zzdc;
        }
        return zzb(null, new zzx());
    }

    private static AppStartTrace zzb(zzh zzh, zzx zzx) {
        if (zzdc == null) {
            synchronized (AppStartTrace.class) {
                if (zzdc == null) {
                    zzdc = new AppStartTrace(null, zzx);
                }
            }
        }
        return zzdc;
    }

    private AppStartTrace(zzh zzh, zzx zzx) {
        this.zzak = zzx;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzc(android.content.Context r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.mRegistered     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x001c }
            boolean r0 = r2 instanceof android.app.Application     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001a
            r0 = r2
            android.app.Application r0 = (android.app.Application) r0     // Catch:{ all -> 0x001c }
            r0.registerActivityLifecycleCallbacks(r1)     // Catch:{ all -> 0x001c }
            r0 = 1
            r1.mRegistered = r0     // Catch:{ all -> 0x001c }
            r1.zzdd = r2     // Catch:{ all -> 0x001c }
        L_0x001a:
            monitor-exit(r1)
            return
        L_0x001c:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.AppStartTrace.zzc(android.content.Context):void");
    }

    private final synchronized void zzai() {
        if (this.mRegistered) {
            ((Application) this.zzdd).unregisterActivityLifecycleCallbacks(this);
            this.mRegistered = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onActivityCreated(android.app.Activity r4, android.os.Bundle r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r5 = r3.zzdk     // Catch:{ all -> 0x002f }
            if (r5 != 0) goto L_0x002d
            com.google.android.gms.internal.firebase-perf.zzaa r5 = r3.zzdh     // Catch:{ all -> 0x002f }
            if (r5 == 0) goto L_0x000a
            goto L_0x002d
        L_0x000a:
            java.lang.ref.WeakReference r5 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x002f }
            r5.<init>(r4)     // Catch:{ all -> 0x002f }
            r3.zzde = r5     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.firebase-perf.zzaa r4 = new com.google.android.gms.internal.firebase-perf.zzaa     // Catch:{ all -> 0x002f }
            r4.<init>()     // Catch:{ all -> 0x002f }
            r3.zzdh = r4     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.firebase-perf.zzaa r4 = com.google.firebase.perf.provider.FirebasePerfProvider.zzaq()     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.firebase-perf.zzaa r5 = r3.zzdh     // Catch:{ all -> 0x002f }
            long r4 = r4.zza(r5)     // Catch:{ all -> 0x002f }
            long r0 = zzdb     // Catch:{ all -> 0x002f }
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x002b
            r4 = 1
            r3.zzdg = r4     // Catch:{ all -> 0x002f }
        L_0x002b:
            monitor-exit(r3)
            return
        L_0x002d:
            monitor-exit(r3)
            return
        L_0x002f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.AppStartTrace.onActivityCreated(android.app.Activity, android.os.Bundle):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onActivityStarted(android.app.Activity r1) {
        /*
            r0 = this;
            monitor-enter(r0)
            boolean r1 = r0.zzdk     // Catch:{ all -> 0x0019 }
            if (r1 != 0) goto L_0x0017
            com.google.android.gms.internal.firebase-perf.zzaa r1 = r0.zzdi     // Catch:{ all -> 0x0019 }
            if (r1 != 0) goto L_0x0017
            boolean r1 = r0.zzdg     // Catch:{ all -> 0x0019 }
            if (r1 == 0) goto L_0x000e
            goto L_0x0017
        L_0x000e:
            com.google.android.gms.internal.firebase-perf.zzaa r1 = new com.google.android.gms.internal.firebase-perf.zzaa     // Catch:{ all -> 0x0019 }
            r1.<init>()     // Catch:{ all -> 0x0019 }
            r0.zzdi = r1     // Catch:{ all -> 0x0019 }
            monitor-exit(r0)
            return
        L_0x0017:
            monitor-exit(r0)
            return
        L_0x0019:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.AppStartTrace.onActivityStarted(android.app.Activity):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0114, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0116, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onActivityResumed(android.app.Activity r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.zzdk     // Catch:{ all -> 0x0117 }
            if (r0 != 0) goto L_0x0115
            com.google.android.gms.internal.firebase-perf.zzaa r0 = r6.zzdj     // Catch:{ all -> 0x0117 }
            if (r0 != 0) goto L_0x0115
            boolean r0 = r6.zzdg     // Catch:{ all -> 0x0117 }
            if (r0 == 0) goto L_0x000f
            goto L_0x0115
        L_0x000f:
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0117 }
            r0.<init>(r7)     // Catch:{ all -> 0x0117 }
            r6.zzdf = r0     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r0 = new com.google.android.gms.internal.firebase-perf.zzaa     // Catch:{ all -> 0x0117 }
            r0.<init>()     // Catch:{ all -> 0x0117 }
            r6.zzdj = r0     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r0 = com.google.firebase.perf.provider.FirebasePerfProvider.zzaq()     // Catch:{ all -> 0x0117 }
            java.lang.String r1 = "FirebasePerformance"
            java.lang.Class r7 = r7.getClass()     // Catch:{ all -> 0x0117 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r2 = r6.zzdj     // Catch:{ all -> 0x0117 }
            long r2 = r0.zza(r2)     // Catch:{ all -> 0x0117 }
            java.lang.String r4 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x0117 }
            int r4 = r4.length()     // Catch:{ all -> 0x0117 }
            int r4 = r4 + 30
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0117 }
            r5.<init>(r4)     // Catch:{ all -> 0x0117 }
            java.lang.String r4 = "onResume "
            r5.append(r4)     // Catch:{ all -> 0x0117 }
            r5.append(r7)     // Catch:{ all -> 0x0117 }
            java.lang.String r7 = ":"
            r5.append(r7)     // Catch:{ all -> 0x0117 }
            r5.append(r2)     // Catch:{ all -> 0x0117 }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x0117 }
            android.util.Log.d(r1, r7)     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzat r7 = new com.google.android.gms.internal.firebase-perf.zzat     // Catch:{ all -> 0x0117 }
            r7.<init>()     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzz r1 = com.google.android.gms.internal.firebase-perf.zzz.APP_START_TRACE_NAME     // Catch:{ all -> 0x0117 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0117 }
            r7.name = r1     // Catch:{ all -> 0x0117 }
            long r1 = r0.zzar()     // Catch:{ all -> 0x0117 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0117 }
            r7.zzgk = r1     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r1 = r6.zzdj     // Catch:{ all -> 0x0117 }
            long r1 = r0.zza(r1)     // Catch:{ all -> 0x0117 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0117 }
            r7.zzgz = r1     // Catch:{ all -> 0x0117 }
            r1 = 3
            com.google.android.gms.internal.firebase-perf.zzat[] r2 = new com.google.android.gms.internal.firebase-perf.zzat[r1]     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzat r3 = new com.google.android.gms.internal.firebase-perf.zzat     // Catch:{ all -> 0x0117 }
            r3.<init>()     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzz r4 = com.google.android.gms.internal.firebase-perf.zzz.ON_CREATE_TRACE_NAME     // Catch:{ all -> 0x0117 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0117 }
            r3.name = r4     // Catch:{ all -> 0x0117 }
            long r4 = r0.zzar()     // Catch:{ all -> 0x0117 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0117 }
            r3.zzgk = r4     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r4 = r6.zzdh     // Catch:{ all -> 0x0117 }
            long r4 = r0.zza(r4)     // Catch:{ all -> 0x0117 }
            java.lang.Long r0 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0117 }
            r3.zzgz = r0     // Catch:{ all -> 0x0117 }
            r0 = 0
            r2[r0] = r3     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzat r0 = new com.google.android.gms.internal.firebase-perf.zzat     // Catch:{ all -> 0x0117 }
            r0.<init>()     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzz r3 = com.google.android.gms.internal.firebase-perf.zzz.ON_START_TRACE_NAME     // Catch:{ all -> 0x0117 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0117 }
            r0.name = r3     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r3 = r6.zzdh     // Catch:{ all -> 0x0117 }
            long r3 = r3.zzar()     // Catch:{ all -> 0x0117 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0117 }
            r0.zzgk = r3     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r3 = r6.zzdh     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r4 = r6.zzdi     // Catch:{ all -> 0x0117 }
            long r3 = r3.zza(r4)     // Catch:{ all -> 0x0117 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0117 }
            r0.zzgz = r3     // Catch:{ all -> 0x0117 }
            r3 = 1
            r2[r3] = r0     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzat r0 = new com.google.android.gms.internal.firebase-perf.zzat     // Catch:{ all -> 0x0117 }
            r0.<init>()     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzz r3 = com.google.android.gms.internal.firebase-perf.zzz.ON_RESUME_TRACE_NAME     // Catch:{ all -> 0x0117 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0117 }
            r0.name = r3     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r3 = r6.zzdi     // Catch:{ all -> 0x0117 }
            long r3 = r3.zzar()     // Catch:{ all -> 0x0117 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0117 }
            r0.zzgk = r3     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r3 = r6.zzdi     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzaa r4 = r6.zzdj     // Catch:{ all -> 0x0117 }
            long r3 = r3.zza(r4)     // Catch:{ all -> 0x0117 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0117 }
            r0.zzgz = r3     // Catch:{ all -> 0x0117 }
            r3 = 2
            r2[r3] = r0     // Catch:{ all -> 0x0117 }
            r7.zzhb = r2     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.firebase-perf.zzh r0 = r6.zzq     // Catch:{ all -> 0x0117 }
            if (r0 != 0) goto L_0x0103
            com.google.android.gms.internal.firebase-perf.zzh r0 = com.google.android.gms.internal.firebase-perf.zzh.zzo()     // Catch:{ all -> 0x0117 }
            r6.zzq = r0     // Catch:{ all -> 0x0117 }
        L_0x0103:
            com.google.android.gms.internal.firebase-perf.zzh r0 = r6.zzq     // Catch:{ all -> 0x0117 }
            if (r0 == 0) goto L_0x010c
            com.google.android.gms.internal.firebase-perf.zzh r0 = r6.zzq     // Catch:{ all -> 0x0117 }
            r0.zza(r7, r1)     // Catch:{ all -> 0x0117 }
        L_0x010c:
            boolean r7 = r6.mRegistered     // Catch:{ all -> 0x0117 }
            if (r7 == 0) goto L_0x0113
            r6.zzai()     // Catch:{ all -> 0x0117 }
        L_0x0113:
            monitor-exit(r6)
            return
        L_0x0115:
            monitor-exit(r6)
            return
        L_0x0117:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.AppStartTrace.onActivityResumed(android.app.Activity):void");
    }

    public synchronized void onActivityStopped(Activity activity) {
    }
}
